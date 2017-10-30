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
    public void hashCycleTest() throws Exception {
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

}