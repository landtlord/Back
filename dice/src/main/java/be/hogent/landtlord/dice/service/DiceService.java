package be.hogent.landtlord.dice.service;

import be.hogent.landtlord.dice.business.DiceEntity;
import be.hogent.landtlord.dice.business.repo.DiceRepository;
import be.hogent.landtlord.dice.service.dto.Dice;
import be.hogent.landtlord.dice.service.mapper.DiceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class DiceService {

    private DiceRepository diceRepository;
    private DiceMapper diceMapper;
    private Random random;

    public DiceService(@Autowired DiceRepository diceRepository, @Autowired DiceMapper diceMapper) {
        this.diceRepository = diceRepository;
        this.diceMapper = diceMapper;
        this.random = new Random();
    }

    public Dice getNewThrow() {
        DiceEntity diceEntity = getRandomDiceEntity();
        diceEntity = diceRepository.save(diceEntity);

        return diceMapper.toDto(diceEntity);
    }

    private DiceEntity getRandomDiceEntity() {
        DiceEntity diceEnity = new DiceEntity();
        diceEnity.setDice(random.nextInt(6) + 1);
        return diceEnity;
    }

    public Dice getDices(Long id) {
        Optional<DiceEntity> diceEntity = diceRepository.findById(id);
        return diceMapper.toDto(diceEntity.orElse(null));
    }
}
