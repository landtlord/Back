package be.hogent.landtlord.pawn.service.mapper;

import be.hogent.landtlord.pawn.business.PawnEntity;
import be.hogent.landtlord.pawn.service.dto.Pawn;
import org.mapstruct.Mapper;

@Mapper
public interface PawnMapper {
    Pawn toDto(PawnEntity pawnEntity);
}
