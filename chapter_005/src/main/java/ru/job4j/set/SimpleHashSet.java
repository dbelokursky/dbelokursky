package ru.job4j.set;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Dmitry Belokursky
 * @since 07.11.17.
 */
public class SimpleHashSet<E> {

    private Map<Integer, E> container;

    public SimpleHashSet() {
        this.container = new HashMap<>(16);
    }

    boolean add(E e) {
        boolean result = false;
        if (!contains(e)) {
            container.put(e.hashCode(), e);
            result = true;
        }
        return result;
    }

    boolean contains(E e) {
        return container.containsValue(e);
    }

    boolean remove(E e) {
        return container.remove(e.hashCode(), e);
    }

    @Override
    public String toString() {
        return "SimpleHashSet{" +
                "container=" + container +
                '}';
    }
}
