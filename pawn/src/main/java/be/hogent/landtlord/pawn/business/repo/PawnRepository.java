package be.hogent.landtlord.pawn.business.repo;

import be.hogent.landtlord.pawn.business.PawnEntity;
import org.springframework.data.repository.CrudRepository;

public interface PawnRepository extends CrudRepository<PawnEntity, Long> {
}
