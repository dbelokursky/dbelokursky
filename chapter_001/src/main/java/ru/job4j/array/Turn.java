package ru.job4j.array;

/**
* Класс Turn переварачивает массив.
* @author Dmitry Belokusky
* @since 0.1 11.06.2017
*/
public class Turn {

	/**
	* Метод переворачивает массив.
	* @param array **входной массив int**
	* @return array **массив в обратном порядке**
	*/
	public int[] back(int[] array) {
		int lastElement = array.length - 1;
		for (int i = 0; i != lastElement; i++) {
			int tmp = array[i];
			array[i] = array[lastElement];
			array[lastElement] = tmp;
			lastElement--;
		}
		return array;
	}
}