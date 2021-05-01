package be.hogent.landtlord.player.business;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class PlayerEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Long colorId;
}
