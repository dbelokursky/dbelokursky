package ru.job4j.iterator;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Dmitry Belokursky
 * @since 07.10.17.
 */
public class ArrayIteratorTest {

    ArrayIterator arrayIterator = new ArrayIterator(new int[][]{{1, 2, 3}, {4, 5, 6}});

    @Test
    public void hasNextTest() {
        arrayIterator.next();
        arrayIterator.next();
        arrayIterator.next();
        boolean result = arrayIterator.hasNext();
        boolean expected = true;
        assertThat(result, is(expected));
    }

    @Test
    public void nextTest() {
        arrayIterator.next();
        arrayIterator.next();
        arrayIterator.next();
        arrayIterator.next();
        arrayIterator.next();
        int result = arrayIterator.next();
        int expected = 6;
        assertThat(result, is(expected));
    }
}
