package ru.job4j.collections;

/**
 * @author Dmitry Belokursky
 * @since 29.08.17.
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ConvertListTest {

    @Test
    public void toListTest() {
        int[][] array = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        ConvertList cl = new ConvertList();
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        List<Integer> result = cl.toList(array);
        assertThat(result, is(expected));
    }

    @Test
    public void toArrayTest() {
        ConvertList cl = new ConvertList();
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        int[][] expected = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int rows = 3;
        int[][] result = cl.toArray(list, rows);
        assertThat(result, is(expected));
    }

    @Test
    public void toArrayTestWithNull() {
        ConvertList cl = new ConvertList();
        List<Integer> list = Arrays.asList(1, 2, null, 4);
        int[][] expected = new int[][]{{1, 2}, {0, 4}};
        int rows = 2;
        int[][] result = cl.toArray(list, rows);
        assertThat(result, is(expected));
    }

    @Test
    public void convertTest() {
        ConvertList cl = new ConvertList();
        List<int[]> list = new ArrayList<>();
        list.add(new int[]{1, 2});
        list.add(new int[]{3, 4, 5, 6});
        List<Integer> result = cl.convert(list);
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5, 6);
        assertThat(result, is(expected));
    }
}
