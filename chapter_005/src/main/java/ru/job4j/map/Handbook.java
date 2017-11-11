package ru.job4j.map;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * @author Dmitry Belokursky
 * @since 11.11.17.
 */
public class Handbook<K, V> {

    private final int INITIAL_CAPACITY;

    private int size;

    private int capacity;

    private float threshold;

    private float loadFactor;

    private Node<K, V>[] container;

    public Handbook(int INITIAL_CAPACITY) {
        this.capacity = this.INITIAL_CAPACITY = INITIAL_CAPACITY;
        this.size = 0;
        this.container = new Node[INITIAL_CAPACITY];
        this.loadFactor = 0.75f;
    }

    public Handbook() {
        this.capacity = this.INITIAL_CAPACITY = 16;
        this.size = 0;
        this.container = new Node[INITIAL_CAPACITY];
        this.loadFactor = 0.75f;
    }

    public boolean insert(K key, V value) {
        boolean result = false;
        Node<K, V> newNode = new Node<>(key, value);
        if (!shouldBeIncreased()) {
            if (container[newNode.key.hashCode() % capacity] == null) {
                container[newNode.key.hashCode() % capacity] = newNode;
                size++;
                result = true;
            }
        } else {
            Node<K, V>[] oldContainer = container;
            container = new Node[capacity *= 2];
            for (int i = 0; i < oldContainer.length; i++) {
                if (oldContainer[i] != null) {
                    container[oldContainer[i].key.hashCode() % capacity] = oldContainer[i];
                }
            }
            container[newNode.key.hashCode() % capacity] = newNode;
            size++;
            result = true;
        }
        return result;
    }

    private boolean shouldBeIncreased() {
        threshold = 1.0f * size / capacity;
        return threshold > loadFactor;
    }

    public V get(K key) {
        if (container[key.hashCode() % capacity] != null) {
            return container[key.hashCode() % capacity].value;
        } else {
            throw new NoSuchElementException();
        }

    }

    public boolean delete(K key) {
        if (container[key.hashCode() % capacity] != null) {
            container[key.hashCode() % capacity] = null;
            return true;
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public String toString() {
        return "Handbook{" +
                "size=" + size +
                ", capacity=" + capacity +
                ", threshold=" + threshold +
                ", container=" + Arrays.toString(container) +
                "}";
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
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Node<?, ?> node = (Node<?, ?>) o;

            if (!key.equals(node.key)) return false;
            return value.equals(node.value);
        }

        @Override
        public int hashCode() {
            return key.hashCode();
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", value=" + value +
                    "}\n";
        }
    }
}
