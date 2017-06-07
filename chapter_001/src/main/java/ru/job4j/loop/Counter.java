package ru.job4j.loop;

/**
* Class Counter для решения задачи нахождения суммы четных чисел в диапазоне start..finish.
* @author Dmitry Belokursky
* @since 0.1
*/
public class Counter {

	/**
	* Метод находит сумму четных чисел в диапазоне start..finish.
	* @param start **начало диапазона**
	* @param finish **конец диапазона**
	* @return result **сумма четных чисел**
	*/
	public int add(int start, int finish) {
		int result = 0;
		for (int i = start; i <= finish; i++) {
			if (i % 2 == 0) {
				result += i;
			}
		}
		return result;
	}
}