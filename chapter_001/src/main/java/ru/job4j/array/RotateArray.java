package ru.job4j.array;

/**
* Класс Turn поворачивает массив по часовой стрелке на 90 градусов.
* @author Dmitry Belokusky
* @since 0.1 11.06.2017
*/
public class RotateArray {

	/**
	* Метод переворачивает массив.
	* @param array **входной двумерный массив int**
	* @return array **перевернутый на 90 градусов массив**
	*/
	public int[][] rotate(int[][] array) {
		int size = array.length;
		int[][] tmpArray = new int[size][size];
		int tmp = size - 1;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				tmpArray[j][tmp] = array[i][j];
			}
			tmp--;
		}
		return tmpArray;
	}
}