package ru.job4j.map;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Dmitry Belokursky
 * @since 11.11.17.
 */
public class HandbookTest {

    private Handbook<String, Integer> hb = new Handbook<>();

    @Before
    public void setUp() throws Exception {
        hb.insert("one", 1);
        hb.insert("two", 2);
        hb.insert("three", 3);
    }

    @Test
    public void insertTest() throws Exception {
        hb.insert("NEW", 999);
        int result = hb.get("NEW");
        int expected = 999;
        assertThat(result, is(expected));
    }

    @Test
    public void getTest() throws Exception {
        int result = hb.get("one");
        int expected = 1;
        assertThat(result, is(expected));
    }

    @Test(expected = NoSuchElementException.class)
    public void deleteTest() throws Exception {
        hb.delete("one");
        hb.get("one");
    }
}