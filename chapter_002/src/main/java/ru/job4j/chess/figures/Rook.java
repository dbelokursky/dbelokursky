package ru.job4j.chess.figures;

import ru.job4j.chess.Cell;
import ru.job4j.chess.exceptions.ImpossibleMoveException;

public class Rook extends Figure {

    public Rook(Cell cell) {
        super(cell, "\u2656");
    }

    @Override
    public Cell[] way(Cell dest) throws ImpossibleMoveException {
        Cell[] result = new Cell[0];
        int figRowPos = this.position.getRowPosition();
        int figColPos = this.position.getColPosition();
        int desColPos = dest.getColPosition();
        int desRowPos = dest.getRowPosition();
        int resInd = 0;

        if ((figRowPos == desRowPos && figColPos != desColPos)) {
            int resultSize = Math.abs(figColPos - desColPos);
            result = new Cell[resultSize];
            if (figColPos > desColPos) {
                for (int i = desColPos; i < figColPos; i++) {
                    result[resInd++] = new Cell(figRowPos, i);
                }
            } else {
                for (int i = desColPos; i > figColPos; i--) {
                    result[resInd++] = new Cell(figRowPos, i);
                }
            }
        } else if ((figColPos == desColPos && figRowPos != desRowPos)) {
            int resultSize = Math.abs(figRowPos - desRowPos);
            result = new Cell[resultSize];
            if (figRowPos > desRowPos) {
                for (int i = desRowPos; i < figRowPos; i++) {
                    result[resInd++] = new Cell(i, figColPos);
                }
            } else {
                for (int i = desRowPos; i > figRowPos; i--) {
                    result[resInd++] = new Cell(i, figColPos);
                }
            }
        }
        return result;
    }

    @Override
    public Figure clone(Cell dest) {
        return new Rook(dest);
    }
}
