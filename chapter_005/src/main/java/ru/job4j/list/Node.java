package ru.job4j.list;

/**
 * @author Dmitry Belokursky
 * @since 30.10.17.
 */
public class Node<T> {

    T value;

    Node<T> next;

    public Node(T value) {
        this.value = value;
    }

    public boolean hashCycle(Node first) {
        Node<T> fCursor = first;
        Node<T> sCursor = first.next.next;
        boolean result = false;
        while (sCursor.next != null) {
            fCursor = fCursor.next;
            sCursor = sCursor.next.next;
            if (fCursor == sCursor) {
                result = true;
                break;
            }
        }
        return result;
    }
}
