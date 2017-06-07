package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Class CounterTest для тестирования класса Counter.
*/
public class CounterTest {

	/**
	* Метод тестирует метод Counter.add().
	*/
	@Test
	public void addTest() {
		Counter counter = new Counter();
		int expected = 30;
		int result = counter.add(1, 10);
		assertThat(result, is(expected));
	}
}