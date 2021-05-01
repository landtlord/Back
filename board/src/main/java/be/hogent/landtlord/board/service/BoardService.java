package be.hogent.landtlord.board.service;

import be.hogent.landtlord.board.service.dto.Coordinate;
import be.hogent.landtlord.board.service.dto.Dice;
import be.hogent.landtlord.board.service.dto.Move;
import be.hogent.landtlord.board.service.dto.Pawn;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BoardService {
    public Coordinate getNewCoordinate(Move move) {
        RestTemplate restTemplate = new RestTemplate();
        Dice dice = restTemplate.getForObject("http://localhost:8081/api/dices/" + move.getDiceId(), Dice.class);
        Pawn pawn = restTemplate.getForObject("http://localhost:8081/api/pawns/" + move.getPawnToMoveId(), Pawn.class);



        return new Coordinate();
    }
}
