package ru.job4j.list;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Dmitry Belokursky
 * @since 29.10.17.
 */
public class SimpleQueueTest {

    @Test
    public void poolTest() throws Exception {
        SimpleQueue<Integer> sq = new SimpleQueue<>();
        sq.push(0);
        sq.push(1);
        sq.push(2);
        sq.push(3);
        sq.push(4);
        Integer result = sq.pool();
        Integer expected = 0;
        assertThat(result, is(expected));
    }

    @Test
    public void pushTest() throws Exception {
        SimpleQueue<Integer> sq = new SimpleQueue<>();
        sq.push(0);
        Integer result = sq.pool();
        Integer expected = 0;
        assertThat(result, is(expected));
    }
}