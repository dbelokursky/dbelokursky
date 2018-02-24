package ru.job4j.bomberman;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Dmitry Belokursky
 * @since 22.02.18.
 */
public class Field {

    private final int size = 4;

    private final ReentrantLock[][] field;

    public Field() {
        this.field = new ReentrantLock[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                field[i][j] = new ReentrantLock();
            }
        }
    }

    public ReentrantLock[][] getField() {
        return field;
    }
}
