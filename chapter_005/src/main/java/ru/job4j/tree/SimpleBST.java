package ru.job4j.tree;

/**
 * @author Dmitry Belokursky
 * @since 25.11.17.
 */
public class SimpleBST<E extends Comparable<E>> {

    private Node<E> root;

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
        return "SimpleBST{" +
                "root=" + root +
                '}';
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
            return "Node{" +
                    "value=" + value +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }
}
