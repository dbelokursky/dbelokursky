package ru.job4j.tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author Dmitry Belokursky
 * @since 25.11.17.
 */
public class SimpleBST<E extends Comparable<E>> implements Iterator {

    private Node<E> root;

    private List<Node<E>> allNodes;

    private int cursor;

    public SimpleBST() {
        cursor = 0;
        this.allNodes = new ArrayList<>();
    }

    public boolean add(E value) {
        boolean result = false;
        Node<E> newNode = new Node<>(value);
        if (root == null) {
            root = newNode;
        } else {
            Node<E> current = root;
            Node<E> parent;
            while (true) {
                parent = current;
                if (value.compareTo(current.value) <= 0) {
                    current = current.left;
                    if (current == null) {
                        parent.left = newNode;
                        return true;
                    }
                } else {
                    current = current.right;
                    if (current == null) {
                        parent.right = newNode;
                        return true;
                    }
                }
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return "SimpleBST{" + "root=" + root + '}';
    }

    private void addTreeNodesToList(Node<E> currentNode) {
        if (currentNode != null) {
            addTreeNodesToList(currentNode.left);
            addTreeNodesToList(currentNode.right);
            allNodes.add(currentNode);
        }
    }

    @Override
    public boolean hasNext() {
        if (allNodes.size() == 0) {
            addTreeNodesToList(root);
        }
        return cursor < allNodes.size();
    }

    @Override
    public Node<E> next() {
        if (hasNext()) {
            return allNodes.get(cursor++);
        } else {
            throw new NoSuchElementException();
        }
    }

    private class Node<E> {

        E value;

        Node<E> left;

        Node<E> right;

        public Node(E value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{"
                    + "value=" + value
                    + ", left=" + left
                    + ", right=" + right + '}';
        }
    }
}
