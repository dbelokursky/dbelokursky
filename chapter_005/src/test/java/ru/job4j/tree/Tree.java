package ru.job4j.tree;

import java.util.Iterator;
import java.util.List;

/**
 * @author Dmitry Belokursky
 * @since 13.11.17.
 */
public class Tree<E extends Comparable<E>> implements SimpleTree<E> {

    @Override
    public boolean add(E parent, E child) {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    class Node<E> {

        List<Node<E>> children;

        E value;
    }
}
