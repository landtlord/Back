package be.hogent.landtlord.board.service;

import be.hogent.landtlord.board.business.repo.BoardRepository;
import be.hogent.landtlord.board.service.dto.Coordinate;
import be.hogent.landtlord.board.service.dto.Dice;
import be.hogent.landtlord.board.service.dto.Move;
import be.hogent.landtlord.board.service.dto.Pawn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

import static be.hogent.landtlord.board.business.repo.Constants.*;

@Service
public class BoardService {
    public static final int NUMBER_OF_FIELDS_BETWEEN_START_FIELDS = 10;
    public static final int NUMBER_OF_FIELDS = 40;

    @Autowired
    private BoardRepository boardRepository;

    public Coordinate getNewCoordinate(Move move) {
        RestTemplate restTemplate = new RestTemplate();
        Dice dice = restTemplate.getForObject("http://localhost:8081/api/dices/" + move.getDiceId(), Dice.class);
        Pawn pawn = restTemplate.getForObject("http://localhost:8084/api/pawns/" + move.getPawnToMoveId(), Pawn.class);

        if (pawnIsOnShelter(pawn)) {
            throw new IllegalArgumentException();
        }

        if (pawnIsOnHomeField(pawn)) {
            return boardRepository.findById(getStartFieldId(pawn));
        }

        int coordinateId = boardRepository.findIdOf(pawn.getCoordinate());
        int newCoordinateId = (coordinateId + dice.getDice()) % NUMBER_OF_FIELDS;

        if (passesShelter(newCoordinateId, coordinateId, pawn)) {
            int placeInShelter = newCoordinateId - getStartFieldId(pawn);
            if (placeInShelter == 5) {
                placeInShelter = 1;
            }
            if (placeInShelter == 4) {
                placeInShelter = 2;
            }
            switch (pawn.getColorId().intValue()) {
                case 1:
                    return RED_SHELTER[placeInShelter];
                case 2:
                    return BLEU_SHELTER[placeInShelter];
                case 3:
                    return GREEN_SHELTER[placeInShelter];
                case 4:
                    return YELLOW_SHELTER[placeInShelter];
            }
        }

        return boardRepository.findById(newCoordinateId);
    }

    private boolean passesShelter(int newCoordinateId, int coordinateId, Pawn pawn) {
        if (pawn.getColorId() == 1) {
            return newCoordinateId < coordinateId;
        }

        int startFieldId = getStartFieldId(pawn);

        return coordinateId < startFieldId && newCoordinateId >= startFieldId;
    }

    private boolean pawnIsOnShelter(Pawn pawn) {
        boolean inRedShelter = Arrays.asList(RED_SHELTER).contains(pawn.getCoordinate());
        boolean inBlueShelter = Arrays.asList(BLEU_SHELTER).contains(pawn.getCoordinate());
        boolean inYellowShelter = Arrays.asList(YELLOW_SHELTER).contains(pawn.getCoordinate());
        boolean inGreenShelter = Arrays.asList(GREEN_SHELTER).contains(pawn.getCoordinate());
        return inBlueShelter || inYellowShelter || inGreenShelter || inRedShelter;
    }

    private boolean pawnIsOnHomeField(Pawn pawn) {
        return Arrays.asList(HOME_COORDINATES).contains(pawn.getCoordinate());
    }

    private int getStartFieldId(Pawn pawn) {
        return (pawn.getColorId().intValue() - 1) * NUMBER_OF_FIELDS_BETWEEN_START_FIELDS;
    }
}
