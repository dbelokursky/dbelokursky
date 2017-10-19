package ru.job4j.generic;

/**
 * @author Dmitry Belokursky
 * @since 19.10.17.
 */
public class SimpleArray<T> {

    private Object[] values;

    private int index;

    public SimpleArray(int capacity) {
        this.values = new Object[capacity];
        this.index = 0;
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

    public boolean delete(T element) {
        boolean result = false;
        for (int i = 0; i < values.length; i++) {
            if (values[i].equals(element)) {
                System.arraycopy(values, i + 1, values, i, values.length - i - 1);
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
}
