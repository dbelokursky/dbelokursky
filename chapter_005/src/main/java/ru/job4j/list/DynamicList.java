package ru.job4j.list;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Dmitry Belokursky
 * @since 22.10.17.
 */
public class DynamicList<E> implements Iterable<E> {

    private Object[] container;

    private int index;

    private int size;

    public DynamicList(int capacity) {
        if (capacity > 0 && capacity < Integer.MAX_VALUE) {
            this.container = new Object[capacity];
            this.size = 0;
        }
    }

    public DynamicList() {
        this.container = new Object[100];
        this.size = 0;
    }

    public void add(E value) {
        if (index < container.length) {
            container[index++] = value;
        } else {
            container = Arrays.copyOf(container, container.length * 2);
            container[index++] = value;
        }
        size++;
    }

    public E get(int index) {
        if (index >= 0 && index <= size) {
            return (E) container[index];
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int i = 0;

            @Override
            public boolean hasNext() {
                return  i >= 0 && i < size;
            }

            @Override
            public E next() {
                if (hasNext()) {
                    return (E) container[i++];
                } else {
                    throw new NoSuchElementException();
                }
            }
        };
    }
}
