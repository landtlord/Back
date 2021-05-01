package be.hogent.landtlord.dice.business;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class DiceEntity {
    @Id
    @GeneratedValue
    private Long id;

    private int dice;
}
