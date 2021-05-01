package be.hogent.landtlord.player.service.mapper;

import be.hogent.landtlord.player.business.PlayerEntity;
import be.hogent.landtlord.player.service.dto.Player;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlayerMapper {
    Player toDto(PlayerEntity playerEntity);

    PlayerEntity toEntity(Player player);
}
