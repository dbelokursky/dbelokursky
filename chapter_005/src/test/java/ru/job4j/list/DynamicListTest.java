package ru.job4j.list;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Dmitry Belokursky
 * @since 22.10.17.
 */
public class DynamicListTest {

    @Test
    public void addTest() throws Exception {
        DynamicList<Integer> dl = new DynamicList<>(10);
        dl.add(1);
        int result = dl.get(0);
        int expected = 1;
        assertThat(result, is(expected));

    }

    @Test
    public void addAutogrowTest() {
        DynamicList<Integer> dl = new DynamicList<>(10);
        for (int i = 0; i < 15; i++) {
            dl.add(i);
        }
        int result = dl.get(13);
        int expected = 13;
        assertThat(result, is(expected));
    }

    @Test
    public void get() throws Exception {
        DynamicList<Integer> dl = new DynamicList<>(10);
        dl.add(3);
        int result = dl.get(0);
        int expected = 3;
        assertThat(result, is(expected));
    }

    @Test
    public void iteratorHasNextTest() throws Exception {
        DynamicList<Integer> dl = new DynamicList<>(10);
        dl.add(1);
        Iterator<Integer> it = dl.iterator();
        boolean result = it.hasNext();
        boolean expected = true;
        assertThat(result, is(expected));
    }

    @Test
    public void iteratorNextTest() {
        DynamicList<Integer> dl = new DynamicList<>(10);
        dl.add(1);
        Iterator<Integer> it = dl.iterator();
        int result = it.next();
        int expected = 1;
        assertThat(result, is(expected));

    }
}