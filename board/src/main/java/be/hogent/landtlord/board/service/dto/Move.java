package be.hogent.landtlord.board.service.dto;

import lombok.Data;

@Data
public class Move {
    private Long diceId;

    private Long pawnToMoveId;

    private Long playerId;
}
