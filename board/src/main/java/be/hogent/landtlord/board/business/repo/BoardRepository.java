package be.hogent.landtlord.board.business.repo;

import be.hogent.landtlord.board.service.dto.Coordinate;
import org.springframework.stereotype.Repository;

import static be.hogent.landtlord.board.business.repo.Constants.COORDINATES;

@Repository
public class BoardRepository {
    public Coordinate findById(int id){
        return COORDINATES[id];
    }

    public int findIdOf(Coordinate coordinate) {
        for (int i = 0; i < COORDINATES.length; i++) {
            if(coordinate.equals(COORDINATES[i])){
                return i;
            }
        }
        return -1;
    }
}
