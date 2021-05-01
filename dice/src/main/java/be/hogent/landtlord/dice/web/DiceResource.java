package be.hogent.landtlord.dice.web;

import be.hogent.landtlord.dice.service.DiceService;
import be.hogent.landtlord.dice.service.dto.Dice;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class DiceResource {
    @Autowired
    private DiceService diceService;

    @GetMapping("dices/{id}")
    public ResponseEntity<Dice> getDices(@PathVariable Long id){
        return ResponseEntity.ok(diceService.getDices(id));
    }


    @GetMapping("dices/new/")
    public ResponseEntity<Dice> getNewThrow(){
        return ResponseEntity.ok(diceService.getNewThrow());
    }
}
