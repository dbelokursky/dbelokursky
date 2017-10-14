package ru.job4j.iterator;

import java.util.Iterator;

/**
 * @author Dmitry Belokursky
 * @since 14.10.17.
 */
public class Converter {

    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<Integer>() {
            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override
            public Integer next() {
                return it.next().next();
            }
        };

    }
}
