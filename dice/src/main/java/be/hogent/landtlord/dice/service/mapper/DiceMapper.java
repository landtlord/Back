package be.hogent.landtlord.dice.service.mapper;

import be.hogent.landtlord.dice.business.DiceEntity;
import be.hogent.landtlord.dice.service.dto.Dice;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DiceMapper {

   Dice toDto(DiceEntity diceEntity);
}
