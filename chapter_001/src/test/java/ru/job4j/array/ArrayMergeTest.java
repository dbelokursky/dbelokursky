package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class ArrayMergeTest для тестирования ArrayMerge.
 */
public class ArrayMergeTest {

    /**
     * Метод тестирует метод ArrayMerge.merge().
     */
    @Test
    public void mergeTest() {
        ArrayMerge arrayMerge = new ArrayMerge();
        int[] fArr = {1, 2, 3};
        int[] sArr = {2, 4, 5};
        int[] result = arrayMerge.merge(fArr, sArr);
        int[] expected = {1, 2, 2, 3, 4, 5};
        assertThat(result, is(expected));
    }
}
