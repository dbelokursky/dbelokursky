package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Dmitry Belokursky
 * @since 07.10.17.
 */
public class ArrayIterator implements Iterator<Integer> {

    private final int[][] values;

    private final int size;

    private int yind = 0;

    private int xind = 0;

    private int cursor = 0;

    public ArrayIterator(int[][] values) {
        this.values = values;
        this.size = values[yind].length * 2;
    }

    @Override
    public boolean hasNext() {
        return cursor < size;
    }

    @Override
    public Integer next() {
        if (hasNext()) {
            int result = values[yind][xind];
            if (xind < values[yind].length - 1) {
                xind++;
            } else {
                if (yind < values.length - 1) {
                    yind++;
                    xind = 0;
                }
            }
            cursor++;
            return result;
        } else {
            throw new NoSuchElementException();
        }
    }
}
