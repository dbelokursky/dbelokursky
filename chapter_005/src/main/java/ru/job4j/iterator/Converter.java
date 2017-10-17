package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Dmitry Belokursky
 * @since 14.10.17.
 */
public class Converter {

    private Iterator<Integer> currentIterator;

    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<Integer>() {
            @Override
            public boolean hasNext() {
                selectIterator();
                boolean result = false;
                if (currentIterator == null) {
                    return false;
                }
                if (currentIterator.hasNext()) {
                    return true;
                }
                if (it.hasNext()) {
                    currentIterator = it.next();
                    return currentIterator.hasNext();
                }
                return result;
            }

            @Override
            public Integer next() {
                selectIterator();
                if (currentIterator == null) {
                    throw new NoSuchElementException();
                }
                if (!currentIterator.hasNext() && it.hasNext()) {
                    currentIterator = it.next();
                }
                return currentIterator.next();
            }

            private void selectIterator() {
                if (currentIterator == null && it.hasNext()) {
                    currentIterator = it.next();
                }
            }
        };
    }
}
