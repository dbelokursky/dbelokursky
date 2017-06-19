package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Класс Substring тестирования класса Substring.
* @author Dmitry Belokursky
* @since 0.1 19.06.2017
*/
public class SubstringTest {

	/**
	* Метод тестирует метод Substring.contains().
	*/
	@Test
	public void whenWhenStringCointainsSubstringThanTrue() {
		Substring ss = new Substring();
		String origin = "палиндром";
		String sub = "лин";
		boolean expected = true;
		boolean result = ss.contains(origin, sub);
		assertThat(result, is(expected));
	}
}