package ru.job4j.chess;

import ru.job4j.chess.figures.Figure;

public class Cell {

    private int rowPosition;

    private int colPosition;

    private boolean occupied;

    private Figure figure;

    public void setFigure(Figure figure) {
        this.figure = figure;
    }

    public Figure getFigure() {
        return figure;
    }

    public Cell(int rowPosition, int colPosition) {
        this.rowPosition = rowPosition;
        this.colPosition = colPosition;
        occupied = false;
        figure = null;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public int getRowPosition() {
        return rowPosition;
    }

    public int getColPosition() {
        return colPosition;
    }

    public boolean isOccupied() {
        return occupied;
    }

    @Override
    public String toString() {
        String result = "0";
        if (this.occupied == true) {
            result = "X";
        }
        return result;
    }
}
