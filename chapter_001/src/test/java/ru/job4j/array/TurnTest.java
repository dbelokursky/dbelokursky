package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Класс TurnTest для тестирования класса Turn.
* @author Dmitry Belokursky
* @since 0.1 11.06.2017
*/
public class TurnTest {

	/**
	* Класс TurnTest для тестирования метода Turn.back().
	*/
	@Test
	public void backTest() {
		Turn turn = new Turn();
		int[] input = {3, 2, 1};
		int[] expected = {1, 2, 3};
		int[] result = turn.back(input);
		assertThat(result, is(expected));
	}
}