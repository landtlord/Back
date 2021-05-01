package be.hogent.landtlord.game.business.repo;

import be.hogent.landtlord.game.business.GameEntity;
import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<GameEntity, Long> {
}
