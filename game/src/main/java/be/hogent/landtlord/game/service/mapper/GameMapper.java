package be.hogent.landtlord.game.service.mapper;

import be.hogent.landtlord.game.business.GameEntity;
import be.hogent.landtlord.game.service.dto.Game;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GameMapper {

    Game toDto(GameEntity gameEntity);

    GameEntity toEntity(Game game);
}
