package ru.job4j.chess;

public class Cell {

    private int rowPosition;

    private int colPosition;

    public Cell(int rowPosition, int colPosition) {
        this.rowPosition = rowPosition;
        this.colPosition = colPosition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Cell cell = (Cell) o;
        if (getRowPosition() != cell.getRowPosition()) {
            return false;
        }
        return getColPosition() == cell.getColPosition();
    }

    @Override
    public int hashCode() {
        int result = getRowPosition();
        result = 31 * result + getColPosition();
        return result;
    }

    public int getRowPosition() {
        return rowPosition;
    }

    public int getColPosition() {
        return colPosition;
    }
}
