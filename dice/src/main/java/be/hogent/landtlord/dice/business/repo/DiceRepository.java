package be.hogent.landtlord.dice.business.repo;

import be.hogent.landtlord.dice.business.DiceEntity;
import org.springframework.data.repository.CrudRepository;

public interface DiceRepository extends CrudRepository<DiceEntity, Long> {
}
