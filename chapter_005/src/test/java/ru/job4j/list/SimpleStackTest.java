package ru.job4j.list;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Dmitry Belokursky
 * @since 29.10.17.
 */
public class SimpleStackTest {

    @Test
    public void pollTest() throws Exception {
        SimpleStack<Integer> ss = new SimpleStack<>();
        ss.push(1);
        ss.push(2);
        ss.push(3);
        Integer result = ss.poll();
        Integer expected = 3;
        assertThat(result, is(expected));
    }

    @Test
    public void pushTest() throws Exception {
        SimpleStack<Integer> ss = new SimpleStack<>();
        ss.push(1);
        Integer result = ss.poll();
        Integer expected = 1;
        assertThat(result, is(expected));
    }
}