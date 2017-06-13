package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Класс BubbleSortTest для BubbleSort.
* @author Dmitry Belokursky
* @since 0.1 12.06.2017
*/
public class BubbleSortTest {

	/**
	* Метод для тестирования BubbleSort.sort().
	*/
	@Test
	public void whenSortArrayWithTenElementsThanSortedArray() {
		int[] array = {3, 9, 8, 7, 6, 5, 4, 10, 2, 1};
		BubbleSort bs = new BubbleSort();
		int[] result = bs.sort(array);
		int[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		assertThat(result, is(expected));
	}
}