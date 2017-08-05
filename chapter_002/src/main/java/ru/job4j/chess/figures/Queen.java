package ru.job4j.chess.figures;

import ru.job4j.chess.Cell;
import ru.job4j.chess.exceptions.ImpossibleMoveException;

public class Queen extends Figure {

    public Queen(Cell cell) {
        super(cell, "\u2655");
    }

    @Override
    public Cell[] way(Cell dest) throws ImpossibleMoveException {
        return new Cell[0];
    }

    @Override
    public Figure clone(Cell dest) {
        return new Queen(dest);
    }
}
