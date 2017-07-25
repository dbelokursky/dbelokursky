package ru.job4j.chess.exceptions;

/**
 * @author Dmitry Belokursky
 * @since 22.07.17.
 */
public class FigureNotFoundException extends Exception {

    public FigureNotFoundException(String msg) {
        super(msg);
    }
}