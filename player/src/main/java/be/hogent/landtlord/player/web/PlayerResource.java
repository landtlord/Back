package be.hogent.landtlord.player.web;

import be.hogent.landtlord.player.service.PlayerService;
import be.hogent.landtlord.player.service.dto.Player;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class PlayerResource {

    @Autowired
    private PlayerService playerService;

    @GetMapping("players/{id}")
    public ResponseEntity<Player> getPlayer(@PathVariable Long id){
        return ResponseEntity.ok(playerService.getPlayer(id));
    }

    @PostMapping("players/")
    public ResponseEntity<Player> savePlayer(@RequestBody Player player){
        return ResponseEntity.ok(playerService.savePlayer(player));
    }
}
