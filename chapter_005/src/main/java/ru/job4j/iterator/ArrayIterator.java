package ru.job4j.iterator;

import java.util.Iterator;

/**
 * @author Dmitry Belokursky
 * @since 07.10.17.
 */
public class ArrayIterator implements Iterator<Integer> {

    private final int[][] values;

    private int indx = 0;

    private int jndx = 0;

    public ArrayIterator(int[][] values) {
        this.values = values;
    }

    @Override
    public boolean hasNext() {
        return indx < values.length && jndx < values[indx].length;
    }

    @Override
    public Integer next() {
        int result = 0;
        if (jndx < values[indx].length) {
            result = values[indx][jndx];
            jndx++;
        } else {
            jndx = 0;
            indx++;
            if (indx < values.length) {
                result = values[indx][jndx];
                jndx++;
            } else {
                throw new UnsupportedOperationException();
            }
        }
        return result;
    }
}
