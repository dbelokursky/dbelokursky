package ru.job4j.chess.figures;

import ru.job4j.chess.Cell;
import ru.job4j.chess.exceptions.ImpossibleMoveException;

public class King extends Figure {

    public King(Cell position) {
        super(position, "\u2654");
    }

    @Override
    public Cell[] way(Cell dest) throws ImpossibleMoveException {
        return new Cell[0];
    }

    @Override
    public Figure clone(Cell dest) {
        return new King(dest);
    }
}