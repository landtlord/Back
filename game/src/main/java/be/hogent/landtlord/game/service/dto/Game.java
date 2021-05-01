package be.hogent.landtlord.game.service.dto;

import lombok.Data;

import java.util.List;

@Data
public class Game {
    private Long id;

    private List<Long> pawnIds;

    private List<Long> playerIds;

    private List<Pawn> pawns;

    private List<Player> players;
}
