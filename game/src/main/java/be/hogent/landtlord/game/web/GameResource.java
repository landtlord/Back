package be.hogent.landtlord.game.web;

import be.hogent.landtlord.game.service.GameService;
import be.hogent.landtlord.game.service.dto.Game;
import be.hogent.landtlord.game.service.dto.Move;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class GameResource {
    @Autowired
    private GameService gameService;

    @GetMapping("games/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable Long id) {
        return ResponseEntity.ok(gameService.getById(id));
    }

    @PostMapping("games/new")
    public ResponseEntity<Game> getNewGame(@RequestBody List<Long> playerIds) {
        return ResponseEntity.ok(gameService.startNewGame(playerIds));
    }

    @PostMapping("games/move/{id}")
    public ResponseEntity<Game> postMove(@PathVariable Long id, @RequestBody Move move) {
        return ResponseEntity.ok(gameService.postMove(id, move));
    }
}
