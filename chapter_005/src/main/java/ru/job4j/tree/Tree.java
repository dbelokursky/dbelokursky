package ru.job4j.tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Dmitry Belokursky
 * @since 13.11.17.
 */
public class Tree<E extends Comparable<E>> implements SimpleTree<E> {

    private Node<E> root;

    private List<E> allNodes;

    private boolean isFound;

    private Node<E> foundNode;


    public Tree() {
        this.allNodes = new ArrayList<>();
        this.isFound = false;
    }

    private Node<E> findNode(Node<E> currentRoot, E parent) {
        if (currentRoot == null || currentRoot.children.size() == 0) {
            return null;
        }
        if (currentRoot.children.size() > 0) {
            if (isFound) {
                return foundNode;
            }
            for (Node<E> current : currentRoot.children) {
                if (current.value.compareTo(parent) == 0) {
                    foundNode = current;
                    isFound = true;
                    return foundNode;
                }
                currentRoot = current;
            }
            findNode(currentRoot, parent);
        }
        return null;
    }

    public boolean addFirst(E value) {
        boolean result = false;
        if (root == null) {
            root = new Node<>(value);
            result = true;
        }
        return result;
    }

    @Override
    public boolean add(E parent, E child) {
        boolean result = false;
        Node<E> newNode = new Node<>(child);
        if (root != null) {
            if (root.value.compareTo(parent) == 0) {
                root.children.add(newNode);
                result = true;
            } else {
                Node<E> parentNode = findNode(root, parent);
                if (parentNode != null) {
                    parentNode.children.add(newNode);
                    result = true;
                }
            }
        }
        return result;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public String toString() {
        return "Tree{" +
                "root=" + root +
                ", allNodes=" + allNodes +
                '}';
    }

    class Node<E> {

        E value;

        List<Node<E>> children;

        public Node(E value) {
            this.children = new ArrayList<>();
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", children=" + children +
                    '}';
        }
    }
}
