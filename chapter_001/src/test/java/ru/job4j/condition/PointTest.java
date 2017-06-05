package ru.job4j.condition;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Class PointTest для тестирования класса Point.
*/
public class PointTest {

	/**
	* Метод тестирует метод Point.is.
	*/
	@Test
	public void whenPointOnTheGraphThenTrue() {
		Point point = new Point(1, 2);
		boolean result = point.is(1, 1);
		boolean expected = true;
		assertThat(result, is(expected));
	}
}