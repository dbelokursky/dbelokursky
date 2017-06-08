package ru.job4j.max;

/**
* Class Max для решения задачи нахождения максимума из двух чисел.
* @author Dmitry Belokursky
* @since 0.1
*/
public class Max {

	/**
	* Метод сравнивает два числа и возвращает большее.
	* @param first **первое число**
	* @param second **второе число**
	* @return max **максимальное число**
	*/
	public int max(int first, int second) {
		return (first > second) ? first : second;
	}

	/**
	* Метод сравнивает три числа и возвращает большее.
	* @param first **первое число**
	* @param second **второе число**
	* @param third **третье число**
	* @return max **максимальное число**
	*/
	public int max(int first, int second, int third) {
		return max(max(first, second), third);
	}
}