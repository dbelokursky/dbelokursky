package ru.job4j.list;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Dmitry Belokursky
 * @since 30.10.17.
 */
public class NodeTest {

    @Test
    public void hashCycleTestWithCycle() throws Exception {
        Node first = new Node(1);
        Node two = new Node(2);
        Node third = new Node(3);
        Node four = new Node(4);

        first.next = two;
        two.next = third;
        third.next = four;
        four.next = first;

        boolean result = first.hashCycle(first);
        boolean expected = true;
        assertThat(result, is(expected));
    }

    @Test
    public void hashCycleTestWithoutCycle() {
        Node<Integer> n1 = new Node<>(5);
        Node<Integer> n2 = new Node<>(3);
        Node<Integer> n3 = new Node<>(4);
        Node<Integer> n4 = new Node<>(1);
        Node<Integer> n5 = new Node<>(2);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;

        boolean result = n1.hashCycle(n1);
        boolean expected = false;
        assertThat(result, is(expected));
    }
}
