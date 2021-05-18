package be.hogent.landtlord.pawn.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pawn {
    private Long id;

    private Long colorId;

    private Coordinate Coordinate;
}
