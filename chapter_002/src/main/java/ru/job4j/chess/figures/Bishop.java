package ru.job4j.chess.figures;

import ru.job4j.chess.Cell;
import ru.job4j.chess.exceptions.ImpossibleMoveException;

public class Bishop extends Figure {

    public Bishop(Cell cell) {
        super(cell);
        this.textRepresentation = "\u2657";
    }

    @Override
    public Cell[] way(Cell dest) throws ImpossibleMoveException {
        return new Cell[0];
    }
}