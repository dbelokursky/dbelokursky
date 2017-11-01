package ru.job4j.set;

import java.util.Iterator;

/**
 * @author Dmitry Belokursky
 * @since 31.10.17.
 */
public class SimpleSet<E> implements Iterator<E> {

    private E[] container;

    public void add(E e) {

    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public E next() {
        return null;
    }
}
