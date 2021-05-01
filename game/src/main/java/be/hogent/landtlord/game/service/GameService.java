package be.hogent.landtlord.game.service;

import be.hogent.landtlord.game.business.GameEntity;
import be.hogent.landtlord.game.business.repo.GameRepository;
import be.hogent.landtlord.game.service.dto.*;
import be.hogent.landtlord.game.service.mapper.GameMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private GameMapper gameMapper;

    public Game getById(Long id) {
        GameEntity gameEntity = getGameEntity(id);
        Game game = gameMapper.toDto(gameEntity);
        enrich(game);
        return game;
    }

    public Game startNewGame(List<Long> playerIds) {
        GameEntity gameEntity = new GameEntity();
        gameEntity.setPlayerIds(playerIds);

        List<Player> players = getPlayers(playerIds);
        List<Long> colorIds = getColorIds(players);
        List<Pawn> pawns = getPawnsFor(colorIds);
        List<Long> pawnIds = getPawnIds(pawns);

        gameEntity.setPawnIds(pawnIds);

        GameEntity entity = gameRepository.save(gameEntity);
        Game game = gameMapper.toDto(entity);

        game.setPlayers(players);
        game.setPawns(pawns);

        return game;
    }

    public Game postMove(Long id, Move move) {
        GameEntity gameEntity = getGameEntity(id);

        Coordinate newCoordinate = getNewCoordinate(move);

        movePawnOnNewCoordinateToHome(gameEntity, newCoordinate);

        movePawnToNewCoordinate(move.getPawnToMoveId(), newCoordinate);

        return getById(id);
    }

    private void movePawnOnNewCoordinateToHome(GameEntity gameEntity, Coordinate newCoordinate) {
        gameEntity.getPawnIds().stream()
                .map(this::fetchPawnById)
                .filter(pawn -> pawn.getCoordinate().equals(newCoordinate))
                .forEach(this::movePawnHome);
    }

    private void movePawnToNewCoordinate(Long id, Coordinate newCoordinate) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject("http://localhost:8084/api/pawns/" + id, newCoordinate, Pawn.class);
    }

    private Coordinate getNewCoordinate(Move move) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject("http://localhost:8082/api/boards/", move, Coordinate.class);
    }

    private GameEntity getGameEntity(Long id) {
        Optional<GameEntity> optionalGameEntity = gameRepository.findById(id);
        if (optionalGameEntity.isEmpty()) {
            throw new IllegalArgumentException();
        }
        return optionalGameEntity.get();
    }

    private void enrich(Game game) {
        List<Pawn> pawns = game.getPawnIds().stream()
                .map(this::fetchPawnById)
                .collect(Collectors.toList());

        List<Player> players = getPlayers(game.getPlayerIds());

        game.setPawns(pawns);
        game.setPlayers(players);
    }

    private void movePawnHome(Pawn pawn) {
        new RestTemplate().getForObject("http://localhost:8084/api/pawns/home/" + pawn.getId(), Pawn.class);
    }

    private Pawn fetchPawnById(Long id) {
        return new RestTemplate().getForObject("http://localhost:8084/api/pawns/" + id, Pawn.class);
    }

    private Player fetchPlayerById(Long id) {
        return new RestTemplate().getForObject("http://localhost:8083/api/players/" + id, Player.class);
    }

    private List<Long> getPawnIds(List<Pawn> pawns) {
        return pawns.stream()
                .map(Pawn::getId)
                .collect(Collectors.toList());
    }

    private List<Long> getColorIds(List<Player> players) {
        return players.stream()
                .map(Player::getColorId)
                .collect(Collectors.toList());
    }

    private List<Player> getPlayers(List<Long> playerIds) {
        return playerIds.stream()
                .map(this::fetchPlayerById)
                .collect(Collectors.toList());
    }

    private List<Pawn> getPawnsFor(List<Long> colorIds) {
        List<Pawn> pawns = new ArrayList<>();
        for (Long colorId : colorIds) {
            RestTemplate restTemplate = new RestTemplate();
            Pawn[] pawnsForColor = restTemplate.getForObject("http://localhost:8084/api/pawns/new/" + colorId, Pawn[].class);
            pawns.addAll(Arrays.asList(pawnsForColor));
        }
        return pawns;
    }
}
