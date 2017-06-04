package ru.job4j.calculator;

/**
* Class Calculator простейший калькулятор.
* Реализовано сложение, вычитание, умножение и деление.
* @author dbelokursky
* @since 0.1
*/
public class Calculator {
	/**
	* Поле хранящие результат вычислений.
	*/
	private double result;

	/**
	* Метод производит суммирование двух чисел.
	* @param first **первое слогаемое**
	* @param second **второе слогаемое**
	*/
	public void add(double first, double second) {
		this.result = first + second;
	}

	/**
	* Метод производит вычитание двух чисел.
	* @param first **уменшаемое**
	* @param second **вычитаемое**
	*/
	public void substruct(double first, double second) {
		this.result = first - second;
	}

	/**
	* Метод производит деление двух чисел.
	* @param first **делимое**
	* @param second **делитель**
	*/
	public void div(double first, double second) {
		this.result = first / second;
	}

	/**
	* Метод производит умножение двух чисел.
	* @param first **множимое**
	* @param second **множитель**
	*/
	public void multiply(double first, double second) {
		this.result = first * second;
	}

	/**
	* Метод возвращает значение поля result.
	* @return result **значени поля result**
	*/
	public double getResult() {
		return this.result;
	}
}