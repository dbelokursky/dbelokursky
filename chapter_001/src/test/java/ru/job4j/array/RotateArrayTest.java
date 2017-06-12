package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Класс RotateArrayTest для тестирования класса RotateArray.
* @author Dmitry Belokursky
* @since 0.1 11.06.2017
*/
public class RotateArrayTest {

	/**
	* Класс RotateArrayTest для тестирования метода RotateArray.rotate().
	*/
    @Test
    public void whenRotateTwoRowTwoColArrayThenRotatedArray() {
        RotateArray ra = new RotateArray();
        int[][] array = {{1, 2},
        				 {3, 4}};
        int[][] expected = {{3, 1},
    						{4, 2}};
        int[][] result = ra.rotate(array);
        assertThat(result, is(expected));
    }

	/**
	* Класс RotateArrayTest для тестирования метода RotateArray.rotate().
	*/
    @Test
    public void whenRotateThreeRowThreeColArrayThenRotatedArray() {
        RotateArray ra = new RotateArray();
        int[][] array = {{1, 2, 3},
        				 {4, 5, 6},
        				 {7, 8, 9}};
        int[][] expected = {{7, 4, 1},
        				    {8, 5, 2},
        				    {9, 6, 3}};
        int[][] result = ra.rotate(array);
        assertThat(result, is(expected));
    }
}