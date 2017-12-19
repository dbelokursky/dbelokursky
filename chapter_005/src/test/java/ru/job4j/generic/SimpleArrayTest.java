package ru.job4j.generic;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Dmitry Belokursky
 * @since 19.10.17.
 */
public class SimpleArrayTest {

    SimpleArray<Integer> sa = new SimpleArray<>(10);

    @Test
    public void add() throws Exception {
        sa.add(1);
        int result = sa.get(0);
        int expected = 1;
        assertThat(result, is(expected));
    }

    @Test
    public void update() throws Exception {
        sa.add(1);
        sa.update(1, 11);
        int result = sa.get(0);
        int expected = 11;
        assertThat(result, is(expected));
    }

//    @Test
//    public void delete() throws Exception {
//        sa.add(1);
//        boolean result = sa.delete("1");
//        boolean expected = true;
//        assertThat(result, is(expected));
//    }

    @Test
    public void get() throws Exception {
        sa.add(2);
        int result = sa.get(0);
        int expected = 2;
        assertThat(result, is(expected));
    }
}