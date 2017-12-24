package ru.job4j.map;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * @author Dmitry Belokursky
 * @since 11.11.17.
 */
public class Handbook<K, V> {

    private static final int INITIAL_CAPACITY = 16;

    private int size;

    private float threshold;

    private float loadFactor;

    private Node<K, V>[] container;

    public Handbook() {
        this.size = 0;
        this.container = new Node[INITIAL_CAPACITY];
        this.loadFactor = 0.75f;
    }

    public boolean insert(K key, V value) {
        boolean result = false;
        int ind = key.hashCode() % container.length;
        Node<K, V> newNode = new Node<>(key, value);
        if (shouldBeIncreased()) {
            increaseContainer();
        }
        if (container[ind] == null) {
            container[ind] = newNode;
            size++;
            result = true;
        }
        return result;
    }

    private void increaseContainer() {
        Node<K, V>[] oldContainer = container;
        container = new Node[container.length * 2];
        for (int i = 0; i < oldContainer.length; i++) {
            if (oldContainer[i] != null) {
                container[oldContainer[i].key.hashCode() % container.length] = oldContainer[i];
            }
        }
    }

    private boolean shouldBeIncreased() {
        threshold = 1.0f * size / container.length;
        return threshold > loadFactor;
    }

    public V get(K key) {
        int ind = key.hashCode() % container.length;
        if (container[ind] != null) {
            return container[ind].value;
        } else {
            throw new NoSuchElementException();
        }

    }

    public boolean delete(K key) {
        int ind = key.hashCode() % container.length;
        if (container[ind] != null) {
            container[ind] = null;
            size--;
            return true;
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public String toString() {
        return "Handbook{"
                + "size=" + size
                + ", capacity=" + container.length
                + ", threshold=" + threshold
                + ", container=" + Arrays.toString(container) + "}";
    }

    static class Node<K, V> {

        private K key;

        private V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Node<?, ?> node = (Node<?, ?>) o;
            if (!key.equals(node.key)) {
                return false;
            }
            return value.equals(node.value);
        }

        @Override
        public int hashCode() {
            return key.hashCode();
        }

        @Override
        public String toString() {
            return "Node{"
                    + "key=" + key
                    + ", value=" + value + "}\n";
        }
    }
}