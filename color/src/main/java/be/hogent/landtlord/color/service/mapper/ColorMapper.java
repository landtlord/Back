package be.hogent.landtlord.color.service.mapper;

import be.hogent.landtlord.color.business.ColorEntity;
import be.hogent.landtlord.color.service.dto.Color;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ColorMapper {
    Color toDto(ColorEntity colorEntity);

    ColorEntity toEntity(Color colorDto);
}
