package ru.job4j.chess;

public class Cell {

    private int rowPosition;

    private int colPosition;

    public Cell(int rowPosition, int colPosition) {
        this.rowPosition = rowPosition;
        this.colPosition = colPosition;
    }

    public int getRowPosition() {
        return rowPosition;
    }

    public int getColPosition() {
        return colPosition;
    }
}
