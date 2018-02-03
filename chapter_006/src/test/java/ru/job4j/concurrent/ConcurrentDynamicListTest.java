package ru.job4j.concurrent;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Dmitry Belokursky
 * @since 03.02.18.
 */
public class ConcurrentDynamicListTest {

    @Test
    public void addTest() throws Exception {
        ConcurrentDynamicList<Integer> dl = new ConcurrentDynamicList<>();
        dl.add(1);
        int result = dl.get(0);
        int expected = 1;
        assertThat(result, is(expected));

    }

    @Test
    public void addAutogrowTest() {
        ConcurrentDynamicList<Integer> dl = new ConcurrentDynamicList<>();
        for (int i = 0; i < 15; i++) {
            dl.add(i);
        }
        int result = dl.get(13);
        int expected = 13;
        assertThat(result, is(expected));
    }

    @Test
    public void get() throws Exception {
        ConcurrentDynamicList<Integer> dl = new ConcurrentDynamicList<>();
        dl.add(3);
        int result = dl.get(0);
        int expected = 3;
        assertThat(result, is(expected));
    }

    @Test
    public void iteratorHasNextTest() throws Exception {
        ConcurrentDynamicList<Integer> dl = new ConcurrentDynamicList<>();
        dl.add(1);
        Iterator<Integer> it = dl.iterator();
        boolean result = it.hasNext();
        boolean expected = true;
        assertThat(result, is(expected));
    }

    @Test
    public void iteratorNextTest() {
        ConcurrentDynamicList<Integer> dl = new ConcurrentDynamicList<>();
        dl.add(1);
        Iterator<Integer> it = dl.iterator();
        int result = it.next();
        int expected = 1;
        assertThat(result, is(expected));
    }
}