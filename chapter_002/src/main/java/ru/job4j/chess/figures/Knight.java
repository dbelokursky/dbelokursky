package ru.job4j.chess.figures;

import ru.job4j.chess.Cell;
import ru.job4j.chess.exceptions.ImpossibleMoveException;

public class Knight extends Figure {
    public Knight(Cell cell) {
        super(cell);
        this.textRepresentation = "\u2658";
    }

    @Override
    public Cell[] way(Cell dest) throws ImpossibleMoveException {
        return new Cell[0];
    }
}
