package be.hogent.landtlord.board.service.dto;

import lombok.Data;

@Data
public class Pawn {
    private Long id;

    private Coordinate coordinate;

    private Long colorId;
}
