package ru.job4j.max;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Class MaxTest для тестирование класса Max.
* @author Dmitry Belokursky
* @since 0.1
*/
public class MaxTest {

	/**
	* Метод тестирует операцию нахождения наибольшего из двух чисел.
	*/
	@Test
	public void whenFirstMoreThenReturnsFirst() {
		Max max = new Max();
		int result = max.max(2, 1);
		int expected = 2;
		assertThat(result, is(expected));
	}

	/**
	* Метод тестирует операцию нахождения наибольшего из трех чисел.
	*/
	@Test
	public void maxTest() {
		Max max = new Max();
		int result = max.max(1, 2, 3);
		int expected = 3;
		assertThat(result, is(expected));
	}
}
