package ru.job4j.chess.figures;

import ru.job4j.chess.Cell;
import ru.job4j.chess.exceptions.ImpossibleMoveException;

public class Pawn extends Figure {

    public Pawn(Cell position) {
        super(position);
        this.name = "\u2659";
    }

    @Override
    public Cell[] way(Cell dest) throws ImpossibleMoveException {
        Cell[] result = new Cell[0];
        if ((dest.getFigure() == null) && (this.position.getRowPosition() - 1 == dest.getRowPosition())
                && (this.position.getColPosition() == dest.getColPosition())) {
            result = new Cell[1];
            result[0] = dest;
        } else {
            throw new ImpossibleMoveException("Ход невозможен.");
        }
        return result;
    }

}
