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
	* Метод тестирует операцию нахождения максимального числа.
	* @param first **первый аргумент**
	* @param second **второй аргумент**
	*/
	@Test
	public void whenFirstMoreThenReturnsFirst(int first, int second) {
		Max max = new Max();
		int result = max.max(2, 1);
		int expected = 2;
		assertThat(result, is(expected));
	}
}