package ru.job4j.set;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author Dmitry Belokursky
 * @since 07.11.17.
 */


public class SimpleHashTable<E> {

    private final int INITIAL_CAPACITY;

    private final float DEFAULT_LOAD_FACTOR = 0.75f;

    private int capacity;

    private LinkedList<E>[] container;

    private int ind;

    private int size;

    private float threshold;

    public SimpleHashTable() {
        this.INITIAL_CAPACITY = 16;
        this.capacity = INITIAL_CAPACITY;
        this.container = new LinkedList[INITIAL_CAPACITY];
        this.size = 0;
        fillContainer();
    }

    private void fillContainer() {
        for (int i = 0; i < container.length; i++) {
            container[i] = new LinkedList<>();
        }
    }

    private boolean shouldBeIncreased() {
        threshold = 1.0f * size / capacity;
        return threshold > DEFAULT_LOAD_FACTOR;
    }

    public boolean add(E e) {
        boolean result = false;
        if (!shouldBeIncreased()) {
            ind = Math.abs(e.hashCode() % capacity);
            result = container[ind].add(e);
            size++;
        } else {
            LinkedList<E>[] oldContainer = container;
            container = new LinkedList[capacity *= 2];
            fillContainer();
            for (int i = 0; i < oldContainer.length; i++) {
                if (oldContainer[i].size() > 0) {
                    container[Math.abs(oldContainer[i].peek().hashCode() % capacity)] = oldContainer[i];
                }
            }
            container[e.hashCode() % capacity].add(e);
            size++;
        }

        return result;
    }

    public boolean contains(E e) {
        boolean result = false;
        for (int i = 0; i < capacity; i++) {
            if (container[i].contains(e)) {
                result = true;
                break;
            }
        }
        return result;
    }

    public boolean remove(E e) {
        boolean result = false;
        ind = e.hashCode() % capacity;
        if (contains(e)) {
            container[ind].remove(e);
            result = true;
            size--;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SimpleHashTable{" +
                "capacity=" + capacity +
                ", container=" + Arrays.toString(container) +
                ", size=" + size +
                ", threshold=" + threshold +
                '}';
    }
}