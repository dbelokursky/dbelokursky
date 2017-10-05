package ru.job4j.directory;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Dmitry Belokursky
 * @since 05.10.17.
 */
public class UnitsSortingTest {

    private UnitsSorting us = new UnitsSorting();

    private String[] unitCodes = {"K1\\SK1",
            "K1\\SK2",
            "K1\\SK1\\SSK1",
            "K1\\SK1\\SSK2",
            "K2",
            "K2\\SK1",
            "K2\\SK1\\SSK1",
            "K2\\SK1\\SSK2"};

    @Test
    public void sortAscendingTest() {
        String[] expected = {"K1",
                "K1\\SK1",
                "K1\\SK1\\SSK1",
                "K1\\SK1\\SSK2",
                "K1\\SK2",
                "K2",
                "K2\\SK1",
                "K2\\SK1\\SSK1",
                "K2\\SK1\\SSK2"};
        String[] result = us.sortAscending(unitCodes);
        assertThat(result, is(expected));
    }

    @Test
    public void sortDescending() {
        String[] expected = {"K2",
                "K2\\SK1",
                "K2\\SK1\\SSK2",
                "K2\\SK1\\SSK1",
                "K1",
                "K1\\SK2",
                "K1\\SK1",
                "K1\\SK1\\SSK2",
                "K1\\SK1\\SSK1"};
        String[] result = us.sortDescending(unitCodes);
        assertThat(result, is(expected));
    }
}