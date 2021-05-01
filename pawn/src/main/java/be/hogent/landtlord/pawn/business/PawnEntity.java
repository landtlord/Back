package be.hogent.landtlord.pawn.business;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class PawnEntity {

    @Id
    @GeneratedValue
    private Long id;

    private Long colorId;

    private int xCoordinate;
    private int yCoordinate;

    private int xHomeCoordinate;
    private int yHomeCoordinate;

    public PawnEntity(Long colorId, int xCoordinate, int yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.xHomeCoordinate = xCoordinate;
        this.yHomeCoordinate = yCoordinate;
        this.colorId = colorId;
    }
}
