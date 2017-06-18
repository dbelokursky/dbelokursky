package ru.job4j.array;

import java.util.Arrays;

/**
* Класс ArrayDuplicate для удаление дубликатов из массива строк.
* @author Dmitry Belokursky
* @since 0.1 12.06.2017
*/
public class ArrayDuplicate {

	/**
	* Метод удаляет дубликаты из массива String.
	* @param array **массив строк**
	* @return array **массив строк без дубликатов**
	*/
	public String[] remove(String[] array) {
        int counter = 0;
        int border = array.length;
        for (int i = 0; i < border; i++) {
            for (int j = 0; j < border; j++) {
                if (array[i].equals(array[j]) && (i != j)) {
                    String tmp = array[border - 1];
                    array[border - 1] = array[j];
                    array[j] = tmp;
                    border--;
                    counter++;
                }
            }
        }
        return Arrays.copyOf(array, array.length - counter);
    }
}