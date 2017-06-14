package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Класс ArrayDuplicateTest для тестирования класса ArrayDuplicate.
* @author Dmitry Belokursky
* @since 0.1 12.06.2017
*/
public class ArrayDuplicateTest {

	/**
	* Метод для тестирования ArrayDuplicate.remove().
	*/
	@Test
	public void whenArrayWithDuplicatesThanArrayWithoutDuplicates() {
		ArrayDuplicate ad = new ArrayDuplicate();
		String[] array = {"Привет", "Мир", "Привет", "Супер", "Мир", "Привет", "Мир"};
		String[] result = ad.remove(array);
		String[] expected = {"Привет", "Супер", "Мир"};
		assertThat(result, is(expected));
	}

}