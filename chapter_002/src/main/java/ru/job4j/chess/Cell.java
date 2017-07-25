package ru.job4j.chess;

import ru.job4j.chess.figures.Figure;

public class Cell {

    private int rowPosition;

    private int colPosition;

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
        figure = null;
    }

    public int getRowPosition() {
        return rowPosition;
    }

    public int getColPosition() {
        return colPosition;
    }
}
