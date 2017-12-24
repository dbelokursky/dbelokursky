package ru.job4j.tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author Dmitry Belokursky
 * @since 13.11.17.
 */
public class Tree<E extends Comparable<E>> implements SimpleTree<E> {

    private Node<E> root;

    private List<Node<E>> allNodes;

    private int index;

    private boolean isFound;

    private Node<E> foundNode;


    public Tree() {
        this.allNodes = new ArrayList<>();
        this.isFound = false;
        this.index = 0;
    }

//    private boolean isBinary(Node<E> currentRoot) {
//        boolean result = false;
//        if (currentRoot == null || currentRoot.children.size() == 0) {
//            return result;
//        }
//        ret
//    }

    private boolean addTreeNodesToList(Node<E> currentRoot) {
        boolean result = false;
        if (currentRoot == null || currentRoot.children.size() == 0) {
            allNodes.add(0, root);
            result = true;
            return result;
        }
        for (Node<E> node : currentRoot.children) {
            allNodes.add(node);
            currentRoot = node;
            result = true;
        }
        addTreeNodesToList(currentRoot);
        return result;
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
        return new Iterator<E>() {
            @Override
            public boolean hasNext() {
                if (allNodes.size() == 0) {
                    addTreeNodesToList(root);
                }
                return index < allNodes.size();
            }

            @Override
            public E next() {
                if (hasNext()) {
                    return allNodes.get(index++).value;
                } else {
                    throw new NoSuchElementException();
                }
            }
        };
    }

    @Override
    public String toString() {
        return "Tree{"
                + "root=" + root
                + ", allNodes=" + allNodes
                + '}';
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
            return "Node{" + "value=" + value + '}';
        }
    }
}
