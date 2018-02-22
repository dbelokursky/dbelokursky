package ru.job4j.concurrent;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.NotThreadSafe;
import net.jcip.annotations.ThreadSafe;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Dmitry Belokursky
 * @since 03.02.18.
 */
@ThreadSafe
public class ConcurrentSinglyLinkedList<E> implements Iterable<E> {

    @GuardedBy("this")
    private Node<E> head;

    @GuardedBy("this")
    private Node<E> tail;

    @GuardedBy("this")
    private int size;

    @GuardedBy("this")
    private int modCount;

    public ConcurrentSinglyLinkedList() {
        head = null;
    }

    boolean isEmpty() {
        return head == null;
    }

    public synchronized void addFirst(E item) {
        Node<E> newNode = new Node<>(item, head, null);
        if (tail == null && head != null) {
            tail = head;
        }
        if (head != null) {
            head.prev = newNode;
        }
        head = newNode;
        modCount++;
        size++;
    }

    public void add(E item) {
        addLast(item);
    }

    public synchronized void addLast(E item) {
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
            modCount++;
            size++;
        }
    }

    public synchronized E get(int index) {
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

    public synchronized E removeLast() {
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
        modCount--;
        return removedNode.item;
    }

    public synchronized E removeFirst() {
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
        modCount--;
        return removedNode.item;
    }

    @Override
    public synchronized String toString() {
        return "SinglyLinkedList{"
                + "head=" + head
                + ", tail=" + tail
                + ", size=" + size + '}';
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private final int expectedModCount = modCount;

            private Node<E> nextNode = head;

            @Override
            public boolean hasNext() {
                return nextNode != null;
            }

            @Override
            public E next() {
                checkForCommodification();
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E result = nextNode.item;
                nextNode = nextNode.next;
                return result;
            }

            private void checkForCommodification() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
            }
        };
    }

    @NotThreadSafe
    private static class Node<E> {

        final private E item;

        private Node<E> next;

        private Node<E> prev;

        public Node(E item, Node<E> next, Node<E> prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }

        @Override
        public synchronized String toString() {
            return "Node{"
                    + "item=" + item
                    + ", next=" + next
                    + ", prev=" + '}';
        }
    }
}
