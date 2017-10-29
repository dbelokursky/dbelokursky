package ru.job4j.list;

/**
 * @author Dmitry Belokursky
 * @since 29.10.17.
 */
public class SimpleQueue<E> {

    private SinglyLinkedList<E> container = new SinglyLinkedList<>();

    public E pool() {
        return container.removeFirst();
    }

    public void push(E value) {
        container.add(value);
    }
}