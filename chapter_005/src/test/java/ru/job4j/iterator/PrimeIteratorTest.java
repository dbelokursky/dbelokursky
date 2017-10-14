package ru.job4j.iterator;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Dmitry Belokursky
 * @since 13.10.17.
 */
public class PrimeIteratorTest {

    PrimeIterator primeIterator = new PrimeIterator(new int[]{1, 2, 3, 4, 5, 6});

    @Test
    public void hasNext() {
        boolean result = primeIterator.hasNext();
        boolean expected = true;
        assertThat(result, is(expected));
    }

    @Test
    public void next() {
        primeIterator.next();
        int result = primeIterator.next();
        int expected = 3;
        assertThat(result, is(expected));
    }

}