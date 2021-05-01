package be.hogent.landtlord.color.business;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class ColorEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String color;
}
