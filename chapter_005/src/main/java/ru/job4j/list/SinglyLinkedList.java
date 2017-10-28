package ru.job4j.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Dmitry Belokursky
 * @since 22.10.17.
 */
public class SinglyLinkedList<E> implements Iterable<E> {

    private Node<E> head;

    private int size;

    public SinglyLinkedList() {
        head = null;
    }

    boolean isEmpty() {
        return head == null;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private Node<E> nextNode = head;

            @Override
            public boolean hasNext() {
                return nextNode != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E result = nextNode.item;
                nextNode = nextNode.next;
                return result;
            }
        };
    }

    public void addFirst(E item) {
        head = new Node<E>(item, head);
        size++;
    }

    public void add(E item) {
        addLast(item);
    }

    public void addLast(E item) {
        Node<E> newItem = new Node<>(item, null);
        Node<E> tmp = head;
        if (head == null) {
            addFirst(item);
        } else {
            while (tmp.next != null) {
                tmp = tmp.next;
            }
            tmp.next = newItem;
            size++;
        }
    }

    E get(int index) {
        Node<E> current = head;
        E result = null;
        if (index == 0) {
            result = current.item;
        } else if (index > 0 && index < size) {
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            result = current.item;
        }
        return result;
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