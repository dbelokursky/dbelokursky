package ru.job4j.iterator;

import java.util.Iterator;

/**
 * @author Dmitry Belokursky
 * @since 09.10.17.
 */
public class EvenIterator implements Iterator<Integer> {

    private final int[] values;

    private int index = 0;

    public EvenIterator(int[] values) {
        this.values = values;
    }

    @Override
    public boolean hasNext() {
        boolean result = false;
        for (int i = index; i < values.length; i++) {
            if (values[i] % 2 == 0) {
                result = true;
            }
        }
        return result;
    }

    @Override
    public Integer next() {
        int result = 0;
        for (int i = index; i < values.length; i++) {
            if (values[i] % 2 == 0) {
                result = values[i];
                index++;
                break;
            } else {
                throw new UnsupportedOperationException();
            }
        }
        return result;
    }
}
