package ru.job4j.list;

/**
 * @author Dmitry Belokursky
 * @since 29.10.17.
 */
public class SimpleStack<E> {

    private SinglyLinkedList<E> container = new SinglyLinkedList<>();

    public E poll() {
        return container.removeLast();
    }

    public void push(E value) {
        container.add(value);
    }
}
