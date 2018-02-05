package ru.job4j.concurrent;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Dmitry Belokursky
 * @since 03.02.18.
 */
@ThreadSafe
public class ConcurrentDynamicList<E> implements Iterable<E> {

    private final int defaultCapacity = 10;

    @GuardedBy("this")
    private Object[] container;

    @GuardedBy("this")
    private int index;

    @GuardedBy("this")
    private int size;

    @GuardedBy("this")
    private int modCount;

    public ConcurrentDynamicList(int capacity) {
        if (capacity > 0 && capacity < Integer.MAX_VALUE) {
            this.container = new Object[capacity];
            this.size = 0;
            modCount = 0;
        }
    }

    public ConcurrentDynamicList() {
        this.container = new Object[defaultCapacity];
        this.size = 0;
        modCount = 0;
    }

    public synchronized void add(E value) {
        if (index < container.length) {
            container[index++] = value;
        } else {
            container = Arrays.copyOf(container, container.length * 2);
            container[index++] = value;
        }
        modCount++;
        size++;
    }

    public synchronized E get(int index) {
        if (index >= 0 && index <= size) {
            return (E) container[index];
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private final int expectedModCount = modCount;
            int i = 0;

            @Override
            public boolean hasNext() {
                return i >= 0 && i < size;
            }

            @Override
            public E next() {
                checkForCommodification();
                if (hasNext()) {
                    return (E) container[i++];
                } else {
                    throw new NoSuchElementException();
                }
            }

            private void checkForCommodification() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
            }
        };
    }
}
