package be.hogent.landtlord.pawn.web;

import be.hogent.landtlord.pawn.service.PawnService;
import be.hogent.landtlord.pawn.service.dto.Coordinate;
import be.hogent.landtlord.pawn.service.dto.Pawn;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class PawnResource {
    @Autowired
    private PawnService pawnService;

    @GetMapping("pawns/{id}")
    public ResponseEntity<Pawn> getPawnById(@PathVariable Long id){
        return ResponseEntity.ok(pawnService.getPawnById(id));
    }

    @PostMapping("pawns/{id}")
    public ResponseEntity<Pawn> movePawnTo(@PathVariable Long id, @RequestBody Coordinate coordinate){
        return ResponseEntity.ok(pawnService.movePawnTo(id, coordinate));
    }

    @GetMapping("pawns/home/{id}")
    public ResponseEntity<Pawn> movePawnToHome(@PathVariable Long id){
        return ResponseEntity.ok(pawnService.movePawnToHome(id));
    }

    @GetMapping("pawns/new/{colorId}")
    public ResponseEntity<List<Pawn>> getNewPawnsFor(@PathVariable Long colorId){
        return ResponseEntity.ok(pawnService.getNewPawnsFor(colorId));
    }
}
