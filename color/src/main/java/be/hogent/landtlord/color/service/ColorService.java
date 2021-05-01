package be.hogent.landtlord.color.service;

import be.hogent.landtlord.color.business.ColorEntity;
import be.hogent.landtlord.color.business.repo.ColorRepository;
import be.hogent.landtlord.color.service.dto.Color;
import be.hogent.landtlord.color.service.mapper.ColorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ColorService {
    @Autowired
    private ColorRepository colorRepository;

    @Autowired
    private ColorMapper colorMapper;

    public Color getById(Long id){
        Optional<ColorEntity> byId = colorRepository.findById(id);
        return colorMapper.toDto(byId.orElse(null));
    }
}
