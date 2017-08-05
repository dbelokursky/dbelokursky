package ru.job4j.chess.figures;

import ru.job4j.chess.Cell;
import ru.job4j.chess.exceptions.ImpossibleMoveException;

public class Bishop extends Figure {

    public Bishop(Cell position) {
        super(position, "\u2657");
    }

    @Override
    public Cell[] way(Cell dest) throws ImpossibleMoveException {
        return new Cell[0];
    }

    @Override
    public Figure clone(Cell dest) {
        return new Bishop(dest);
    }
}