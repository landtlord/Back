package be.hogent.landtlord.board.service;

import be.hogent.landtlord.board.business.repo.BoardRepository;
import be.hogent.landtlord.board.service.dto.Coordinate;
import be.hogent.landtlord.board.service.dto.Dice;
import be.hogent.landtlord.board.service.dto.Move;
import be.hogent.landtlord.board.service.dto.Pawn;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class BoardServiceTest {
    @InjectMocks
    private BoardService boardService;

    @Mock
    private RestTemplate restTemplate;

    @Spy
    private BoardRepository boardRepository;

    @Test
    void givenBluePawnOnBoardAndDice3_whenGetNewCoordinateIsInvoked_thenTheCorrectCoordinateIsReturned() {
        Dice dice = new Dice();
        dice.setDice(3);
        Pawn pawn = new Pawn();
        pawn.setColorId(2L);
        pawn.setCoordinate(new Coordinate(9, 6));
        Move move = getMove();
        initiateMock(dice, pawn);

        Coordinate actualCoordinate = boardService.getNewCoordinate(move);
        Coordinate expectedCoordinate = new Coordinate(6, 6);

        assertThat(actualCoordinate).isEqualTo(expectedCoordinate);
    }
    @Test
    void givenBluePawnOnBoardAndDice3AndPassesShelter_whenGetNewCoordinateIsInvoked_thenTheCorrectCoordinateIsReturned() {
        Dice dice = new Dice();
        dice.setDice(3);
        Pawn pawn = new Pawn();
        pawn.setColorId(2L);
        pawn.setCoordinate(new Coordinate(5, 0));
        Move move = getMove();
        initiateMock(dice, pawn);

        Coordinate actualCoordinate = boardService.getNewCoordinate(move);
        Coordinate expectedCoordinate = new Coordinate(5, 3);

        assertThat(actualCoordinate).isEqualTo(expectedCoordinate);
    }

    @Test
    void givenPawnInShelter_whenGetNewCoordinateIsInvoked_thenExceptionIsThrown() {
        Dice dice = new Dice();
        dice.setDice(1);
        Pawn pawn = new Pawn();
        pawn.setCoordinate(new Coordinate(5, 2));
        Move move = getMove();
        initiateMock(dice, pawn);

        assertThatIllegalArgumentException().isThrownBy(() -> boardService.getNewCoordinate(move));
    }

    @Test
    void givenBluePawnInHomeAndDiceNot6_whenGetNewCoordinateIsInvoked_thenExceptionIsThrown() {
        Dice dice = new Dice();
        dice.setDice(1);
        Pawn pawn = new Pawn();
        pawn.setColorId(2L);
        pawn.setCoordinate(new Coordinate(9, 1));
        Move move = getMove();
        initiateMock(dice, pawn);

        assertThatIllegalArgumentException().isThrownBy(() -> boardService.getNewCoordinate(move));
    }

    @Test
    void givenBluePawnInHomeAndDice6_whenGetNewCoordinateIsInvoked_thenBleuStartCoordinateIsReturned() {
        Dice dice = new Dice();
        dice.setDice(6);
        Pawn pawn = new Pawn();
        pawn.setColorId(2L);
        pawn.setCoordinate(new Coordinate(9, 1));
        Move move = getMove();
        initiateMock(dice, pawn);

        Coordinate actualCoordinate = boardService.getNewCoordinate(move);
        Coordinate expectedCoordinate = new Coordinate(6, 0);

        assertThat(actualCoordinate).isEqualTo(expectedCoordinate);
    }

    private Move getMove() {
        Move move = new Move();
        move.setDiceId(1L);
        move.setPawnToMoveId(1L);

        return move;
    }

    private void initiateMock(Dice dice, Pawn pawn) {
        doReturn(dice).when(restTemplate).getForObject("http://localhost:8081/api/dices/1", Dice.class);
        doReturn(pawn).when(restTemplate).getForObject("http://localhost:8084/api/pawns/1", Pawn.class);
    }
}
