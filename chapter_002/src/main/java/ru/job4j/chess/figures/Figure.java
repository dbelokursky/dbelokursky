package ru.job4j.chess.figures;

import ru.job4j.chess.Cell;
import ru.job4j.chess.exceptions.ImpossibleMoveException;

public abstract class Figure {

    public final String textRepresentation;
    final Cell position;

    Figure(Cell position, String textRepresentation) {
        this.position = position;
        this.textRepresentation = textRepresentation;
    }

    /**
     * Проверяет возможность перемещения фигуры на указанную клетку. В случае если
     * такое перемещение возможно возвращает массив клеток который должна пройти фигура.
     * @param dest ячейка в которую нужно переместить фигуру.
     * @return массив ячеек. которые должна пройти фигура.
     * @throws ImpossibleMoveException
     */
    public abstract Cell[] way(Cell dest) throws ImpossibleMoveException;

    public abstract Figure clone(Cell dest);
}