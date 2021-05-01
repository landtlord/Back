package be.hogent.landtlord.pawn.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Pawn {
    private Long id;

    private Long colorId;

    private Coordinate Coordinate;
}
