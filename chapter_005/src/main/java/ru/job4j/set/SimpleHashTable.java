package ru.job4j.set;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author Dmitry Belokursky
 * @since 07.11.17.
 */


public class SimpleHashTable<E> {

    private static final int INITIAL_CAPACITY = 16;

    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private LinkedList<E>[] container;

    private int ind;

    private int size;

    private float threshold;

    public SimpleHashTable() {
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
        threshold = 1.0f * size / container.length;
        return threshold > DEFAULT_LOAD_FACTOR;
    }

    public boolean add(E e) {
        boolean result;
        if (shouldBeIncreased()) {
            increaseContainer();
        }
        ind = Math.abs(e.hashCode() % container.length);
        result = container[ind].add(e);
        size++;
        return result;
    }

    private void increaseContainer() {
        LinkedList<E>[] oldContainer = container;
        container = new LinkedList[container.length * 2];
        fillContainer();
        for (int i = 0; i < oldContainer.length; i++) {
            if (oldContainer[i].size() > 0) {
                ind = Math.abs(oldContainer[i].peek().hashCode() % container.length);
                container[ind] = oldContainer[i];
            }
        }
    }

    public boolean contains(E e) {
        return container[Math.abs(e.hashCode() % container.length)].contains(e);
    }

    public boolean remove(E e) {
        boolean result = false;
        ind = e.hashCode() % container.length;
        if (contains(e)) {
            result = container[ind].remove(e);
            size--;
        }
        return result;
    }

    @Override
    public String toString() {
        return "SimpleHashTable{"
                + "capacity=" + container.length
                + ", container=" + Arrays.toString(container)
                + ", size=" + size
                + ", threshold=" + threshold + '}';
    }
}