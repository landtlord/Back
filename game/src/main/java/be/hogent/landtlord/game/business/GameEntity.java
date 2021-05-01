package be.hogent.landtlord.game.business;

import lombok.Data;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@Entity
@Data
public class GameEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ElementCollection
    private List<Long> pawnIds;

    @ElementCollection
    private List<Long> playerIds;
}
