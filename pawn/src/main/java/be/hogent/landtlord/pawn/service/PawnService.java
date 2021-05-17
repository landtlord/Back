package be.hogent.landtlord.pawn.service;

import be.hogent.landtlord.pawn.business.PawnEntity;
import be.hogent.landtlord.pawn.business.repo.PawnRepository;
import be.hogent.landtlord.pawn.service.dto.Coordinate;
import be.hogent.landtlord.pawn.service.dto.Pawn;
import be.hogent.landtlord.pawn.service.mapper.PawnMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PawnService {
    @Autowired
    private PawnRepository pawnRepository;

    @Autowired
    private PawnMapper pawnMapper;

    public Pawn getPawnById(Long id) {
        Optional<PawnEntity> pawnEntity = pawnRepository.findById(id);
        return pawnMapper.toDto(pawnEntity.orElse(null));
    }

    public Pawn movePawnTo(Long id, Coordinate coordinate) {
        Optional<PawnEntity> optionalPawnEntity = pawnRepository.findById(id);
        if (optionalPawnEntity.isEmpty()) {
            throw new IllegalArgumentException();
        }
        PawnEntity pawnEntity = optionalPawnEntity.get();
        pawnEntity.setXCoordinate(coordinate.getX());
        pawnEntity.setYCoordinate(coordinate.getY());
        pawnEntity = pawnRepository.save(pawnEntity);
        return pawnMapper.toDto(pawnEntity);
    }

    public Pawn movePawnToHome(Long id) {
        Optional<PawnEntity> optionalPawnEntity = pawnRepository.findById(id);
        if (optionalPawnEntity.isEmpty()) {
            throw new IllegalArgumentException();
        }
        PawnEntity pawnEntity = optionalPawnEntity.get();
        pawnEntity.setXCoordinate(pawnEntity.getXHomeCoordinate());
        pawnEntity.setYCoordinate(pawnEntity.getYHomeCoordinate());
        pawnEntity = pawnRepository.save(pawnEntity);
        return pawnMapper.toDto(pawnEntity);
    }


    public List<Pawn> getNewPawnsFor(Long colorId) {
        List<PawnEntity> pawns = new ArrayList<>();
        switch (colorId.intValue()) {
            case 1: //red
                pawns.add(new PawnEntity(colorId, 0, 0));
                pawns.add(new PawnEntity(colorId, 0, 1));
                pawns.add(new PawnEntity(colorId, 1, 0));
                pawns.add(new PawnEntity(colorId, 1, 1));
                break;
            case 2: //bleuEntity
                pawns.add(new PawnEntity(colorId, 9, 0));
                pawns.add(new PawnEntity(colorId, 9, 1));
                pawns.add(new PawnEntity(colorId, 10, 0));
                pawns.add(new PawnEntity(colorId, 10, 1));
                break;
            case 3: //greenEntity
                pawns.add(new PawnEntity(colorId, 9, 9));
                pawns.add(new PawnEntity(colorId, 9, 10));
                pawns.add(new PawnEntity(colorId, 10, 9));
                pawns.add(new PawnEntity(colorId, 10, 10));
                break;
            case 4: //yellowEntity
                pawns.add(new PawnEntity(colorId, 0, 9));
                pawns.add(new PawnEntity(colorId, 0, 10));
                pawns.add(new PawnEntity(colorId, 1, 9));
                pawns.add(new PawnEntity(colorId, 1, 10));
                break;
            default:
                break;
        }
        return pawns.stream()
                .map(pawnRepository::save)
                .map(pawnMapper::toDto)
                .collect(Collectors.toList());
    }
}
