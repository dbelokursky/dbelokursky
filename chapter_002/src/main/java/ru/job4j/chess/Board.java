package ru.job4j.chess;

import ru.job4j.chess.exceptions.FigureNotFoundException;
import ru.job4j.chess.exceptions.ImpossibleMoveException;
import ru.job4j.chess.exceptions.OccupiedWayException;
import ru.job4j.chess.figures.*;

public class Board {

    private static final int BOARD_SIZE = 8;

    private static final int INITIAL_FIGURES_COUNT = 32;

    private int figureInd = 0;

    private Figure[] figures;

    Board() {
        this.figures = new Figure[INITIAL_FIGURES_COUNT];
    }

    /**
     * Начальная расстановка Пешек.
     */
    private void setPawns() {
        int whitePawsRow = 6; //начальная позиция белых пешек(2-ой ряд)
        int blackPawRow = 1; //начальная позиция черных пешек(7-ой ряд)
        for (int i = 0; i < BOARD_SIZE; i++) {
            figures[figureInd++] = new Pawn(new Cell(whitePawsRow, i));
            figures[figureInd++] = new Pawn(new Cell(blackPawRow, i));
        }
    }

    /**
     * Начальная расстановка Слонов.
     */
    private void setBishops() {
        int whiteBishopsRow = 7;
        int blackBishopsRow = 0;
        int fBishopsCol = 2;
        int sBishopsCol = 5;

        figures[figureInd++] = new Bishop(new Cell(whiteBishopsRow, fBishopsCol));
        figures[figureInd++] = new Bishop(new Cell(whiteBishopsRow, sBishopsCol));
        figures[figureInd++] = new Bishop(new Cell(blackBishopsRow, fBishopsCol));
        figures[figureInd++] = new Bishop(new Cell(blackBishopsRow, sBishopsCol));
    }

    /**
     * Начальная расстановка Королей.
     */
    private void setKings() {
        int whiteKingRow = 7;
        int blackKingRow = 0;
        int kingCol = 3;

        figures[figureInd++] = new King(new Cell(whiteKingRow, kingCol));
        figures[figureInd++] = new King(new Cell(blackKingRow, kingCol));
    }

    /**
     * Начальная расстановка Ферзей.
     */
    private void setQueens() {
        int whiteQueenRow = 7;
        int blackQueenRow = 0;
        int queenCol = 4;

        figures[figureInd++] = new King(new Cell(whiteQueenRow, queenCol));
        figures[figureInd++] = new King(new Cell(blackQueenRow, queenCol));
    }

    /**
     * Начальная расстановка Ладей.
     */
    private void setRooks() {
        int whiteRooksRow = 7;
        int blackRooksRow = 0;
        int fRookCol = 0;
        int sRookCol = 7;

        figures[figureInd++] = new Rook(new Cell(whiteRooksRow, fRookCol));
        figures[figureInd++] = new Rook(new Cell(whiteRooksRow, sRookCol));
        figures[figureInd++] = new Rook(new Cell(blackRooksRow, fRookCol));
        figures[figureInd++] = new Rook(new Cell(blackRooksRow, sRookCol));
    }

    /**
     * Начаальная расстановка Коней.
     */
    private void setKnights() {
        int whiteKnightsRow = 7;
        int blackKnightRow = 0;
        int fKnightCol = 1;
        int sKnightCol = 6;

        figures[figureInd++] = new Knight(new Cell(whiteKnightsRow, fKnightCol));
        figures[figureInd++] = new Knight(new Cell(whiteKnightsRow, sKnightCol));
        figures[figureInd++] = new Knight(new Cell(blackKnightRow, fKnightCol));
        figures[figureInd++] = new Knight(new Cell(blackKnightRow, sKnightCol));
    }

    /**
     * Начальная расстановка фигур.
     */
    public void setFigures() {
        setBishops();
        setKings();
        setKnights();
        setPawns();
        setQueens();
        setRooks();
    }

    public boolean move(Cell source, Cell dest) throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
        boolean result = false;
        Figure sourceFigure = null;
        Figure destFigure = null;

        for (Figure figure : figures) {
            if (figure.position.equals(source)) {
                sourceFigure = figure;
            }
            if (figure.position.equals(dest)) {
                destFigure = figure;
            }
        }

        Cell[] way = sourceFigure.way(dest);

        if (sourceFigure == null) {
            throw new FigureNotFoundException("Фигура не найдена.");
        } else if (destFigure != null) {
            throw new OccupiedWayException("Клетка занята.");
        } else if (way.length == 0) {
            throw new ImpossibleMoveException("Ход невозможен.");
        } else if (way.length != 0) {
            for (Cell cell : way) {
                for (Figure figure : figures) {
                    if (cell.equals(figure.position)) {
                        throw new ImpossibleMoveException("Ход невозможен.(Фигура на пути.)");
                    }
                }
            }
            Figure clonedFigure = sourceFigure.clone(dest);
            for (int i = 0; i < figures.length; i++) {
                if (figures[i].equals(sourceFigure)) {
                    figures[i] = clonedFigure;
                }
            }
            result = true;
            }
        return result;
        }
    }