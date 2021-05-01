package be.hogent.landtlord.player.service;

import be.hogent.landtlord.player.business.PlayerEntity;
import be.hogent.landtlord.player.business.repo.PlayerRepository;
import be.hogent.landtlord.player.service.dto.Player;
import be.hogent.landtlord.player.service.mapper.PlayerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private PlayerMapper playerMapper;

    public Player getPlayer(Long id){
        Optional<PlayerEntity> playerEntity = playerRepository.findById(id);
        return playerMapper.toDto(playerEntity.orElse(null));
    }

    public Player savePlayer(Player player){
        PlayerEntity playerEntity = playerMapper.toEntity(player);
        playerEntity = playerRepository.save(playerEntity);
        return playerMapper.toDto(playerEntity);
    }
}
