package ru.job4j.generic;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Dmitry Belokursky
 * @since 19.10.17.
 */
public class SimpleArray<T> implements Iterator<T> {

    private Object[] values;

    private int index;

    private int capacity;

    public SimpleArray(int capacity) {
        this.values = new Object[capacity];
        this.index = 0;
        this.capacity = capacity;
    }

    public SimpleArray() {
        this.values = new Object[10];
        this.capacity = 10;
    }

    public void add(T element) {
        if (index < values.length) {
            this.values[index++] = element;
        }
    }

    public boolean update(T replaceableEl, T replacingEl) {
        boolean result = false;
        for (int i = 0; i < values.length; i++) {
            if (values[i].equals(replaceableEl)) {
                values[i] = replacingEl;
                result = true;
                break;
            }
        }
        return result;
    }

    public boolean delete(String id) {
        boolean result = false;
        for (int i = 0; i < values.length; i++) {
            String string = ((Base) values[i]).getId();
            if (string.equals(id)) {
                System.arraycopy(values, i + 1, values, i, index--);
                result = true;
                break;
            }
        }
        return result;
    }

    public T get(int index) {
        if (index >= 0 && index < values.length) {
            return (T) values[index];
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public int length() {
        return capacity;
    }

    @Override
    public boolean hasNext() {
        boolean result = false;
        if (index > 0 && index < capacity) {
            result = true;
        }
        return result;
    }

    @Override
    public T next() {
        if (hasNext()) {
            return (T) values[index++];
        } else {
            throw new NoSuchElementException();
        }
    }
}
