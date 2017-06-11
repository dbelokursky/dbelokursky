package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Class PaintTest для тестирования класса Paint.
* @author Dmitry Belokursky
* @since 0.1
*/
public class PaintTest {

	/**
	* Метод тестирует метод Paint.pyramid().
	*/
	@Test
	public void whenPaintPyramidWithHeightTwoThenStringWithTwoRows() {
		Paint pyramid = new Paint();
		String result = pyramid.pyramid(2);
		final String line = System.getProperty("line.separator");
		String expected = String.format(" ^%s^^^%<s", line);
		assertThat(result, is(expected));
	}

	/**
	* Метод тестирует метод Paint.pyramid().
	*/
	@Test
	public void whenPaintPyramidWithHeightThreeThenStringWithThreeRows() {
		Paint pyramid = new Paint();
		String result = pyramid.pyramid(3);
		final String line = System.getProperty("line.separator");
		String expected = String.format("  ^%s ^^^%<s^^^^^%<s", line);
		assertThat(result, is(expected));
	}
}