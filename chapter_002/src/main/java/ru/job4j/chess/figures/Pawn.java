package ru.job4j.chess.figures;

import ru.job4j.chess.Cell;
import ru.job4j.chess.exceptions.ImpossibleMoveException;

public class Pawn extends Figure {

    public Pawn(Cell position) {
        super(position, "\u2659");
    }

    @Override
    public Cell[] way(Cell dest) throws ImpossibleMoveException {
        Cell[] result = new Cell[0];
        int figRowPos = this.position.getRowPosition();
        int desRowPos = dest.getRowPosition();
        int figColPos = this.position.getColPosition();
        int desColPos = dest.getColPosition();

        if ((--figRowPos == desRowPos) && (figColPos == desColPos)) {
            result = new Cell[1];
            result[0] = dest;
        } else {
            throw new ImpossibleMoveException("Ход невозможен.");
        }
        return result;
    }

    @Override
    public Figure clone(Cell dest) {
        return new Pawn(dest);
    }
}