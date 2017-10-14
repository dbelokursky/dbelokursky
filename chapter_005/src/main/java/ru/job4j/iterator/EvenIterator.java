package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Dmitry Belokursky
 * @since 09.10.17.
 */
public class EvenIterator implements Iterator<Integer> {

    private final int[] values;

    private int index = 0;

    private int nextEvenElem;

    public EvenIterator(int[] values) {
        this.values = values;
    }

    @Override
    public boolean hasNext() {
        boolean result = false;
        for (int i = index; i < values.length; i++) {
            if (values[i] % 2 == 0) {
                result = true;
                index = i;
                break;
            }
        }
        return result;
    }

    @Override
    public Integer next() {
        int result = 0;
        if (hasNext()) {
            result = values[index++];
            return result;
        } else {
            throw new NoSuchElementException();
        }
    }
}
