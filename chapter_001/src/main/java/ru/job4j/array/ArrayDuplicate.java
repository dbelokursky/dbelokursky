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
        int lastElementInd = array.length - 1;
        int border = array.length;
        for (int i = 0; i < border; i++) {
            for (int j = 0; j < border; j++) {
                if (array[i].equals(array[j]) && (i != j) && (counter == 0)) {
                    String tmp = array[lastElementInd];
                    array[lastElementInd] = array[j];
                    array[j] = tmp;
                    border--;
                    counter++;
                } else if (array[i].equals(array[j]) && (i != j) && (counter != 0)) {
                    String tmp = array[lastElementInd - counter];
                    array[lastElementInd - counter] = array[j];
                    array[j] = tmp;
                    border--;
                    counter++;
                }
            }
        }
        return Arrays.copyOf(array, array.length - counter);
    }
}