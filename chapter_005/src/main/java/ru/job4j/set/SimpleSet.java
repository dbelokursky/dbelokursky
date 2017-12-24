package ru.job4j.set;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Dmitry Belokursky
 * @since 31.10.17.
 */
public class SimpleSet<E> implements Iterator<E> {

    private Object[] container;

    private int index;

    private int size;

    private int itCursor;

    public SimpleSet() {
        this.container = new Object[10];
        this.index = 0;
        this.size = 0;
        this.itCursor = 0;
    }

    public void add(E e) {
        if (!contains(e)) {
            ensureCapacity();
            container[index++] = e;
            size++;
        }
    }

    private void ensureCapacity() {
        if (!(index < container.length)) {
            container = Arrays.copyOf(container, container.length * 2);
        }
    }

    private boolean contains(E e) {
        boolean result = false;
        for (Object o : container) {
            if (o != null && o.equals(e)) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return "SimpleSet{"
                + "container="
                + Arrays.toString(container)
                + ", index=" + index + '}';
    }

    @Override
    public boolean hasNext() {
        return itCursor < size;
    }

    @Override
    public E next() {
        if (hasNext()) {
            return (E) container[itCursor++];
        } else {
            throw new NoSuchElementException();
        }
    }
}
