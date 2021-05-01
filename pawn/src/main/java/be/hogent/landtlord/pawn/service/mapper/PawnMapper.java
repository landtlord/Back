package be.hogent.landtlord.pawn.service.mapper;

import be.hogent.landtlord.pawn.business.PawnEntity;
import be.hogent.landtlord.pawn.service.dto.Coordinate;
import be.hogent.landtlord.pawn.service.dto.Pawn;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
public class PawnMapper {
    public Pawn toDto(PawnEntity pawnEntity) {
        Pawn pawn = new Pawn();

        Coordinate coordinate = new Coordinate();
        coordinate.setX(pawnEntity.getXCoordinate());
        coordinate.setY(pawnEntity.getYCoordinate());
        pawn.setCoordinate(coordinate);

        pawn.setColorId(pawnEntity.getColorId());
        pawn.setId(pawnEntity.getId());

        return pawn;
    }
}
