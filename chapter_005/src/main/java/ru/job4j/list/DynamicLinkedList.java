package ru.job4j.list;

import java.util.Iterator;

/**
 * @author Dmitry Belokursky
 * @since 22.10.17.
 */
public class DynamicLinkedList<E> implements Iterable<E> {

    private Node<E> first;

    public DynamicLinkedList() {
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    public void add(E item) {
        if (first == null) {
            first = new Node<E>(item, null);
        } else {
            Node last = first.next;
            while (last != null) {
                last = last.next;
            }
        }

    }

    E get(int index) {
        return null;
    }

    private static class Node<E> {

        E item;

        Node<E> next;

        public Node(E item, Node<E> next) {
            this.item = item;
            this.next = next;
        }
    }

}
