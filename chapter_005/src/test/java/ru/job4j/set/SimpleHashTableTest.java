package ru.job4j.set;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Dmitry Belokursky
 * @since 07.11.17.
 */
public class SimpleHashTableTest {

    SimpleHashTable<String> simpleHashTable = new SimpleHashTable<>();

    @Before
    public void before() {
        simpleHashTable.add("first");
        simpleHashTable.add("second");
        simpleHashTable.add("third");
    }

    @Test
    public void add() throws Exception {
        boolean result = simpleHashTable.contains("second");
        boolean expected = true;
        assertThat(result, is(expected));
    }

    @Test
    public void contains() throws Exception {
        boolean result = simpleHashTable.contains("first");
        boolean expected = true;
        assertThat(result, is(expected));
    }

    @Test
    public void remove() throws Exception {
        simpleHashTable.remove("first");
        boolean result = simpleHashTable.contains("first");
        boolean expected = false;
        assertThat(result, is(expected));
    }
}