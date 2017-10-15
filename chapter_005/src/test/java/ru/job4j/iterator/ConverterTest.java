package ru.job4j.iterator;

import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Dmitry Belokursky
 * @since 14.10.17.
 */
public class ConverterTest {

    @Test
    public void convertTest() {
        Iterator<Iterator<Integer>> it = Arrays.asList(
                Arrays.asList(1, 2, 3).iterator(),
                Arrays.asList(4, 5, 6).iterator(),
                Arrays.asList(7, 8, 9).iterator()
        ).iterator();
        Iterator<Integer> convert = new Converter().convert(it);
        convert.next();
        convert.next();
        int result = convert.next();
        assertThat(result, is(3));
    }

}