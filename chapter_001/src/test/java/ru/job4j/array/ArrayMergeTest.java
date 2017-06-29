package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class ArrayMergeTest для тестирования ArrayMerge.
 */
public class ArrayMergeTest {

    /**
     * Метод тестирует метод ArrayMerge.merge(). С двумя отсортированными массивами.
     */
    @Test
    public void whenTwoSortedArraysThanOneSortedArray() {
        ArrayMerge arrayMerge = new ArrayMerge();
        int[] fArr = {1, 2, 3};
        int[] sArr = {2, 4, 5};
        int[] result = arrayMerge.merge(fArr, sArr);
        int[] expected = {1, 2, 2, 3, 4, 5};
        assertThat(result, is(expected));
    }

    /**
     * Метод тестирует метод ArrayMerge.merge(). Первый массив пустой.
     */
    @Test
    public void whenFirstArrayEmptyThanSecondArray() {
        ArrayMerge arrayMerge = new ArrayMerge();
        int[] fArr = {};
        int[] sArr = {2, 4, 5};
        int[] result = arrayMerge.merge(fArr, sArr);
        int[] expected = {2, 4, 5};
        assertThat(result, is(expected));
    }

    /**
     * Метод тестирует метод ArrayMerge.merge(). Второй массив пустой.
     */
    @Test
    public void whenSecondArrayEmptyThanFirstArray() {
        ArrayMerge arrayMerge = new ArrayMerge();
        int[] fArr = {1, 2, 5};
        int[] sArr = {};
        int[] result = arrayMerge.merge(fArr, sArr);
        int[] expected = {1, 2, 5};
        assertThat(result, is(expected));
    }
}
