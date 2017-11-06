package ru.job4j.set;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * @author Dmitry Belokursky
 * @since 02.11.17.
 */
public class SimpleLinkedSet<E> implements Iterator<E> {

    private LinkedList<E> container;

    private Iterator<E> it;

    public SimpleLinkedSet() {
        this.container = new LinkedList<>();
    }

    public void add(E e) {
        if (!contains(e)) {
            container.add(e);
        }
    }

    private boolean contains(E e) {
        boolean result = false;
        for (Object o : container) {
            if (e != null && e.equals(o)) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public boolean hasNext() {
        if (this.it == null) {
            this.it = container.iterator();
        }
        return it.hasNext();
    }

    @Override
    public E next() {
        if (hasNext()) {
            return it.next();
        } else {
            throw new NoSuchElementException();
        }
    }
}
