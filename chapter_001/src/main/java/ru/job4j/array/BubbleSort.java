package ru.job4j.array;

/**
* Класс BubleSort для сортировки методом пузырька.
* @author Dmitry Belokursky
* @since 0.1 12.06.2017
*/
public class BubbleSort {

	/**
	* Метод сортирует массив int в порядке возрастания.
	* @param array **исходный массив**
	* @return array **отсортированный массив**
	*/
	public int[] sort(int[] array) {
		for (int j = array.length; j >= 0; j--) {
			for (int i = 0; i < j - 1; i++) {
				if (array[i] > array[i + 1]) {
					int tmp = array[i];
					array[i] = array[i + 1];
					array[i + 1] = tmp;
				}
			}
		}
		return array;
	}
}