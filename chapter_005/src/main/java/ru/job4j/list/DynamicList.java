package ru.job4j.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Dmitry Belokursky
 * @since 22.10.17.
 */
public class DynamicList<E> implements Iterable<E> {

    private Object[] container;

    private int index;

    private int capacity;

    private int size;

    public DynamicList(int capacity) {
        if (capacity > 0 && capacity < Integer.MAX_VALUE) {
            this.container = new Object[capacity];
            this.capacity = capacity;
            this.size = 0;
        }
    }

    public DynamicList() {
        this.container = new Object[100];
        capacity = container.length;
        this.size = 0;
    }

    public void add(E value) {
        if (index < capacity) {
            container[index++] = value;
        } else {
            Object[] tmp = new Object[capacity * 2];
            System.arraycopy(container, 0, tmp, 0, capacity);
            capacity *= 2;
            container = tmp;
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
                boolean result = false;
                if (i >= 0 && i < size) {
                    result = true;
                }
                return result;
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
