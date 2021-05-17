package be.hogent.landtlord.board.business.repo;

import be.hogent.landtlord.board.service.dto.Coordinate;

public class Constants {
    public static Coordinate[] COORDINATES = {
            new Coordinate(0, 4),
            new Coordinate(1, 4),
            new Coordinate(2, 4),
            new Coordinate(3, 4),
            new Coordinate(4, 4),
            new Coordinate(4, 3),
            new Coordinate(4, 2),
            new Coordinate(4, 1),
            new Coordinate(4, 0),
            new Coordinate(5, 0),
            new Coordinate(6, 0),
            new Coordinate(6, 1),
            new Coordinate(6, 2),
            new Coordinate(6, 3),
            new Coordinate(6, 4),
            new Coordinate(7, 4),
            new Coordinate(8, 4),
            new Coordinate(9, 4),
            new Coordinate(10, 4),
            new Coordinate(10, 5),
            new Coordinate(10, 6),
            new Coordinate(9, 6),
            new Coordinate(8, 6),
            new Coordinate(7, 6),
            new Coordinate(6, 6),
            new Coordinate(6, 7),
            new Coordinate(6, 8),
            new Coordinate(6, 9),
            new Coordinate(6, 10),
            new Coordinate(5, 10),
            new Coordinate(4, 10),
            new Coordinate(4, 9),
            new Coordinate(4, 8),
            new Coordinate(4, 7),
            new Coordinate(4, 6),
            new Coordinate(3, 6),
            new Coordinate(2, 6),
            new Coordinate(1, 6),
            new Coordinate(0, 6),
            new Coordinate(0, 5)
    };

    public static Coordinate[] HOME_COORDINATES = {
            new Coordinate(0, 0),
            new Coordinate(0, 1),
            new Coordinate(1, 0),
            new Coordinate(1, 1),
            new Coordinate(9, 0),
            new Coordinate(9, 1),
            new Coordinate(10, 0),
            new Coordinate(10, 1),
            new Coordinate(9, 9),
            new Coordinate(9, 10),
            new Coordinate(10, 9),
            new Coordinate(10, 10),
            new Coordinate(0, 9),
            new Coordinate(0, 10),
            new Coordinate(1, 9),
            new Coordinate(1, 10)
    };

    public static Coordinate[] RED_SHELTER = {
            new Coordinate(1, 5),
            new Coordinate(2, 5),
            new Coordinate(3, 5),
            new Coordinate(4, 5)
    };

    public static Coordinate[] BLEU_SHELTER = {
            new Coordinate(5, 1),
            new Coordinate(5, 2),
            new Coordinate(5, 3),
            new Coordinate(5, 4)
    };

    public static Coordinate[] GREEN_SHELTER = {
            new Coordinate(9, 5),
            new Coordinate(8, 5),
            new Coordinate(7, 5),
            new Coordinate(6, 5)
    };

    public static Coordinate[] YELLOW_SHELTER = {
            new Coordinate(5, 9),
            new Coordinate(5, 8),
            new Coordinate(5, 7),
            new Coordinate(5, 6)
    };
}
