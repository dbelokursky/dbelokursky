package ru.job4j.set;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Dmitry Belokursky
 * @since 02.11.17.
 */
public class SimpleLinkedSetTest {

    @Test
    public void add() throws Exception {
        SimpleLinkedSet<Integer> simpleLinkedSet = new SimpleLinkedSet<>();
        simpleLinkedSet.add(1);
        simpleLinkedSet.add(2);

        int result = simpleLinkedSet.next();
        int expected = 1;

        assertThat(result, is(expected));
    }

    @Test
    public void hasNext() throws Exception {
        SimpleLinkedSet<Integer> simpleLinkedSet = new SimpleLinkedSet<>();
        simpleLinkedSet.add(1);

        boolean result = simpleLinkedSet.hasNext();
        boolean expected = true;

        assertThat(result, is(expected));
    }

    @Test
    public void next() throws Exception {
        SimpleLinkedSet<Integer> simpleLinkedSet = new SimpleLinkedSet<>();
        simpleLinkedSet.add(1);
        simpleLinkedSet.add(2);
        simpleLinkedSet.next();

        int result = simpleLinkedSet.next();
        int expected = 2;

        assertThat(result, is(expected));
    }
}