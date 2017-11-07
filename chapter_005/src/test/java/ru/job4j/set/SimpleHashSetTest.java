package ru.job4j.set;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Dmitry Belokursky
 * @since 07.11.17.
 */
public class SimpleHashSetTest {

    SimpleHashSet<String> simpleHashSet = new SimpleHashSet<>();

    @Before
    public void before() {
        simpleHashSet.add("first");
        simpleHashSet.add("second");
        simpleHashSet.add("third");
    }

    @Test
    public void add() throws Exception {
        boolean result = simpleHashSet.contains("second");
        boolean expected = true;
        assertThat(result, is(expected));
    }

    @Test
    public void contains() throws Exception {
        boolean result = simpleHashSet.contains("first");
        boolean expected = true;
        assertThat(result, is(expected));
    }

    @Test
    public void remove() throws Exception {
        simpleHashSet.remove("first");
        boolean result = simpleHashSet.contains("first");
        boolean expected = false;
        assertThat(result, is(expected));
    }
}