package ru.job4j.loop;

/**
* Class Triangle для вычисления площади треугольника.
* @author Dmitriy Belokurskiy
* @since 0.1 07.06.2017
*/
public class Factorial {

	/**
	* Метод сравнивает два числа и возвращает большее.
	* @param n **число для расчета**
	* @return result **факториал числа n**
	*/
	public int calc(int n) {
		int result = 1;
		if (n == 0) {
			result = 1;
		} else {
			for (int i = 1; i <= n; i++) {
				result *= i;
		}
		}
		return result;
	}
}