package ru.job4j.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Dmitry Belokursky
 * @since 22.10.17.
 */
public class SinglyLinkedList<E> implements Iterable<E> {

    private Node<E> head;

    private Node<E> tail;

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
        Node<E> newNode = new Node<>(item, head, null);
        if (tail == null && head != null) {
            tail = head;
        }
        if (head != null) {
            head.prev = newNode;
        }
        head = newNode;
        size++;
    }

    public void add(E item) {
        addLast(item);
    }

    public void addLast(E item) {
        Node<E> newItem = new Node<>(item, null, null);
        Node<E> tmp = head;
        if (head == null) {
            addFirst(item);
        } else {
            while (tmp.next != null) {
                tmp = tmp.next;
            }
            tmp.next = newItem;
            tail = tmp.next;
            tail.prev = tmp;
            size++;
        }
    }

    public E get(int index) {
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

    public E removeLast() {
        Node<E> removedNode;
        if (size > 1) {
            removedNode = tail;
            tail = tail.prev;
            tail.next = null;
            size--;
        } else {
            removedNode = head;
            head = null;
            tail = null;
        }
        return removedNode.item;
    }

    public E removeFirst() {
        Node<E> removedNode;
        if (size > 1) {
            removedNode = head;
            head = head.next;
            head.prev = null;
            size--;
        } else {
            removedNode = head;
            head = null;
            tail = null;
        }
        return removedNode.item;
    }

    @Override
    public String toString() {
        return "SinglyLinkedList{"
                + "head=" + head
                + ", tail=" + tail
                + ", size=" + size + '}';
    }

    private static class Node<E> {

        E item;

        Node<E> next;

        Node<E> prev;

        public Node(E item, Node<E> next, Node<E> prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }

        @Override
        public String toString() {
            return "Node{"
                    + "item=" + item
                    + ", next=" + next
                    + ", prev=" + '}';
        }
    }
}