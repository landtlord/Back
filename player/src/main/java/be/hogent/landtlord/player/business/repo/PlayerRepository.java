package be.hogent.landtlord.player.business.repo;

import be.hogent.landtlord.player.business.PlayerEntity;
import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<PlayerEntity, Long > {
}
