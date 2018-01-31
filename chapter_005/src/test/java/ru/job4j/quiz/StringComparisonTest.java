package ru.job4j.quiz;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Dmitry Belokursky
 * @since 16.01.18.
 */
public class StringComparisonTest {

    @Test
    public void containsAllTrueTest() {
        StringComparison sc = new StringComparison();
        boolean expected = true;
        boolean result = sc.containsAll("cool", "looc");
        assertThat(result, is(expected));
    }

    @Test
    public void containsAllFalseTest() {
        StringComparison sc = new StringComparison();
        boolean expected = false;
        boolean result = sc.containsAll("cool", "look");
        assertThat(result, is(expected));
    }

    @Test
    public void containsAllLinearWithArrTrueTest() {
        StringComparison sc = new StringComparison();
        boolean expected = true;
        boolean result = sc.containsAllWithArr("cool", "looc");
        assertThat(result, is(expected));
    }

    @Test
    public void containsAllWithArrFalseTest() {
        StringComparison sc = new StringComparison();
        boolean expected = false;
        boolean result = sc.containsAllWithArr("cool", "look");
        assertThat(result, is(expected));
    }

    @Test
    public void containsAllWithMapTrueTest() {
        StringComparison sc = new StringComparison();
        boolean expected = true;
        boolean result = sc.containsAllWithMap("cool", "looc");
        assertThat(result, is(expected));
    }

    @Test
    public void containsAllWithMapFalseTest() {
        StringComparison sc = new StringComparison();
        boolean expected = false;
        boolean result = sc.containsAllWithMap("cool", "look");
        assertThat(result, is(expected));
    }
}