package be.hogent.landtlord.board.web;

import be.hogent.landtlord.board.service.BoardService;
import be.hogent.landtlord.board.service.dto.Coordinate;
import be.hogent.landtlord.board.service.dto.Move;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class BoardResource {
    @Autowired
    private BoardService boardService;

    @PostMapping("Boards/")
    public ResponseEntity<Coordinate> getNewCoordinate(@RequestBody Move move){
        return ResponseEntity.ok(boardService.getNewCoordinate(move));
    }
}
