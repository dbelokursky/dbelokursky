package ru.job4j.iterator;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Dmitry Belokursky
 * @since 09.10.17.
 */
public class EvenIteratorTest {

    EvenIterator evenIterator = new EvenIterator(new int[]{4, 2, 1, 1});

    @Test
    public void hasNextTest() throws Exception {
        boolean result = evenIterator.hasNext();
        boolean expected = true;
        assertThat(result, is(expected));
    }

    @Test
    public void nextTest() throws Exception {
        int result = evenIterator.next();
        int expected = 4;
        assertThat(result, is(expected));
    }
}