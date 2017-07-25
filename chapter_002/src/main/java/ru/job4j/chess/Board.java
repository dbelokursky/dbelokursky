package ru.job4j.chess;

import ru.job4j.chess.exceptions.ImpossibleMoveException;
import ru.job4j.chess.exceptions.OccupiedWayException;
import ru.job4j.chess.exceptions.FigureNotFoundException;
import ru.job4j.chess.figures.*;

public class Board {

    private static final int BOARD_SIZE = 8;

    private static final int INITIAL_FIGURES_COUNT = 32;

    private int figuresCount;

    private int figureInd = 0;

    private Figure[] figures;

    private Cell[][] board;

    public Cell[][] getBoard() {
        return board;
    }

    Board() {
        this.figuresCount = INITIAL_FIGURES_COUNT;
        this.figures = new Figure[INITIAL_FIGURES_COUNT];
        this.board = new Cell[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = new Cell(i, j);
            }
        }
    }

    /**
     * Начальная расстановка Пешек.
     */
    private void setPawns() {
        int whitePawsRow = 6;//начальная позиция белых пешек(2-ой ряд)
        int blackPawRow = 1;//начальная позиция черных пешек(7-ой ряд)
        Cell tmpCell;
        for (int i = 0; i < BOARD_SIZE; i++) {
            tmpCell = new Cell(whitePawsRow, i);
            tmpCell.setFigure(new Pawn(tmpCell));
            board[whitePawsRow][i] = tmpCell;
            figures[figureInd++] = new Pawn(tmpCell);

            tmpCell = new Cell(blackPawRow, i);
            tmpCell.setFigure(new Pawn(tmpCell));
            board[blackPawRow][i] = tmpCell;
            figures[figureInd++] = new Pawn(tmpCell);
        }
    }

    /**
     * Начальная расстановка Слонов.
     */
    private void setBishops() {
        Cell tmpCell;
        int whiteBishopsRow = 7;
        int blackBishopsRow = 0;
        int fBishopsCol = 2;
        int sBishopsCol = 5;

        tmpCell = new Cell(whiteBishopsRow, fBishopsCol);
        tmpCell.setFigure(new Bishop(tmpCell));
        board[whiteBishopsRow][fBishopsCol] = tmpCell;
        figures[figureInd++] = new Bishop(tmpCell);

        tmpCell = new Cell(whiteBishopsRow, sBishopsCol);
        tmpCell.setFigure(new Bishop(tmpCell));
        board[whiteBishopsRow][sBishopsCol] = tmpCell;
        figures[figureInd++] = new Bishop(tmpCell);

        tmpCell = new Cell(blackBishopsRow, fBishopsCol);
        tmpCell.setFigure(new Bishop(tmpCell));
        board[blackBishopsRow][fBishopsCol] = tmpCell;
        figures[figureInd++] = new Bishop(tmpCell);

        tmpCell = new Cell(blackBishopsRow, sBishopsCol);
        tmpCell.setFigure(new Bishop(tmpCell));
        board[blackBishopsRow][sBishopsCol] = tmpCell;
        figures[figureInd++] = new Bishop(tmpCell);
    }

    /**
     * Начальная расстановка Королей.
     */
    private void setKings() {
        Cell tmpCell;
        int whiteKingRow = 7;
        int blackKingRow = 0;
        int kingCol = 3;

        tmpCell = new Cell(whiteKingRow, kingCol);
        tmpCell.setFigure(new King(tmpCell));
        board[whiteKingRow][kingCol] = tmpCell;
        figures[figureInd++] = new King(tmpCell);

        tmpCell = new Cell(blackKingRow, kingCol);
        tmpCell.setFigure(new King(tmpCell));
        board[blackKingRow][kingCol] = tmpCell;
        figures[figureInd++] = new King(tmpCell);
    }

    /**
     * Начальная расстановка Ферзей.
     */
    private void setQueens() {
        Cell tmpCell;
        int whiteQueenRow = 7;
        int blackQueenRow = 0;
        int queenCol = 4;

        tmpCell = new Cell(whiteQueenRow, queenCol);
        tmpCell.setFigure(new Queen(tmpCell));
        board[whiteQueenRow][queenCol] = tmpCell;
        figures[figureInd++] = new King(tmpCell);

        tmpCell = new Cell(blackQueenRow, queenCol);
        tmpCell.setFigure(new Queen(tmpCell));
        board[blackQueenRow][queenCol] = tmpCell;
        figures[figureInd++] = new King(tmpCell);
    }

    /**
     * Начальная расстановка Ладей.
     */
    private void setRooks() {
        Cell tmpCell;
        int whiteRooksRow = 7;
        int blackRooksRow = 0;
        int fRookCol = 0;
        int sRookCol = 7;

        tmpCell = new Cell(whiteRooksRow, fRookCol);
        tmpCell.setFigure(new Rook(tmpCell));
        board[whiteRooksRow][fRookCol] = tmpCell;
        figures[figureInd++] = new Rook(tmpCell);

        tmpCell = new Cell(whiteRooksRow, sRookCol);
        tmpCell.setFigure(new Rook(tmpCell));
        board[whiteRooksRow][sRookCol] = tmpCell;
        figures[figureInd++] = new Rook(tmpCell);

        tmpCell = new Cell(blackRooksRow, fRookCol);
        tmpCell.setFigure(new Rook(tmpCell));
        board[blackRooksRow][fRookCol] = tmpCell;
        figures[figureInd++] = new Rook(tmpCell);

        tmpCell = new Cell(blackRooksRow, sRookCol);
        tmpCell.setFigure(new Rook(tmpCell));
        board[blackRooksRow][sRookCol] = tmpCell;
        figures[figureInd++] = new Rook(tmpCell);
    }

    /**
     * Начаальная расстановка Коней.
     */
    private void setKnights() {
        Cell tmpCell;
        int whiteKnightsRow = 7;
        int blackKnightRow = 0;
        int fKnightCol = 1;
        int sKnightCol = 6;

        tmpCell = new Cell(whiteKnightsRow, fKnightCol);
        tmpCell.setFigure(new Knight(tmpCell));
        board[whiteKnightsRow][fKnightCol] = tmpCell;
        figures[figureInd++] = new Knight(tmpCell);

        tmpCell = new Cell(whiteKnightsRow, sKnightCol);
        tmpCell.setFigure(new Knight(tmpCell));
        board[whiteKnightsRow][sKnightCol] = tmpCell;
        figures[figureInd++] = new Knight(tmpCell);

        tmpCell = new Cell(blackKnightRow, fKnightCol);
        tmpCell.setFigure(new Knight(tmpCell));
        board[blackKnightRow][fKnightCol] = tmpCell;
        figures[figureInd++] = new Knight(tmpCell);

        tmpCell = new Cell(blackKnightRow, sKnightCol);
        tmpCell.setFigure(new Knight(tmpCell));
        board[blackKnightRow][sKnightCol] = tmpCell;
        figures[figureInd++] = new Knight(tmpCell);
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

    public void showBoard() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (!(board[i][j].getFigure() == null)) {
                    sb.append(board[i][j].getFigure().getTextRepresentation()).append(" ");
                } else {
                    sb.append("\u2B1A").append(" ");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public boolean move(Cell source, Cell dest) throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
        boolean result = false;
        Cell[] way = source.getFigure().way(dest);
        Figure destFigure = dest.getFigure();

        if (source.getFigure() == null) {
            throw new FigureNotFoundException("Фигура не найдена.");
        } else if (destFigure != null) {
            throw new OccupiedWayException("Клетка занята.");
        } else if (way.length == 0) {
            throw new ImpossibleMoveException("Ход невозможен.");
        } else if (way.length != 0) {
            for (Cell cell : way) {
                if (board[cell.getRowPosition()][cell.getColPosition()].getFigure() != null) {
                    throw new ImpossibleMoveException("Ход невозможен.(Фигура на пути.)");
                }
            }
            dest.setFigure(source.getFigure());
            dest.getFigure().setPosition(dest);
            source.setFigure(null);
            result = true;
            }
        return result;
        }
    }