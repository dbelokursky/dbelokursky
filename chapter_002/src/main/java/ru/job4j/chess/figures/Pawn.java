package ru.job4j.chess.figures;

import ru.job4j.chess.Cell;
import ru.job4j.chess.exceptions.ImpossibleMoveException;

public class Pawn extends Figure {

    public Pawn(Cell position) {
        super(position);
        this.textRepresentation = "\u2659";
    }

    @Override
    public Cell[] way(Cell dest) throws ImpossibleMoveException {
        Cell[] result = new Cell[0];
        int figRowPos = this.position.getRowPosition();
        int desRowPos = dest.getRowPosition();
        int figColPos = this.position.getColPosition();
        int desColPos = dest.getColPosition();

        if ((dest.getFigure() == null) && (figRowPos - 1 == desRowPos) && (figColPos == desColPos)) {
            result = new Cell[1];
            result[0] = dest;
        } else {
            throw new ImpossibleMoveException("Ход невозможен.");
        }
        return result;
    }
}