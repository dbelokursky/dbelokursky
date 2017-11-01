package ru.job4j.set;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Dmitry Belokursky
 * @since 01.11.17.
 */
public class SimpleSetTest {

    @Test
    public void add() throws Exception {
        SimpleSet<Integer> ss = new SimpleSet();
        ss.add(0);
        ss.add(1);
        ss.add(2);
        ss.add(3);
        ss.add(4);
        ss.add(5);
        ss.add(6);
        ss.add(7);
        ss.add(8);
        ss.add(9);
        ss.add(10);
        ss.add(11);
        ss.add(3);
        ss.add(3);
    }

    @Test
    public void hasNext() throws Exception {
        SimpleSet<Integer> ss = new SimpleSet();
        ss.add(0);
        ss.add(1);
        ss.add(2);
        boolean result = ss.hasNext();
        boolean expected = true;
        assertThat(result, is(expected));
    }

    @Test
    public void next() throws Exception {
        SimpleSet<Integer> ss = new SimpleSet();
        ss.add(0);
        ss.add(1);
        ss.add(2);
        int result = ss.next();
        int expected = 0;
        assertThat(result, is(expected));
    }
}