package ru.job4j.condition;

/**
* Class Point для определения положения точки.
* @author Dmitry Belokursky
* @since 0.1
*/
public class Point {

	/**
	* Поле хранит координату на оси абцисс.
	*/
	private int x;

	/**
	* Поле хранит координату на оси ординат.
	*/
	private int y;

	/**
	* Конструктор.
	* @param x **координата оси абцисс**
	* @param y **координата оси ординат**
	*/
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	* Метод возвращает координату на оси абцисс.
	* @return **координата оси абцисс**
	*/
	public int getX() {
		return this.x;
	}

	/**
	* Метод возвращает координату на оси ординат.
	* @return **координата оси ординат**
	*/
	public int getY() {
		return this.y;
	}

	/**
	* Метод проверяет принадлежит ли точка графику функции y(x) = a * x + b.
	* @param a **значение a**
	* @param b **значение b**
	* @return **Результат проверки.**
	*/
	public boolean is(int a, int b) {
		return (this.y == a * this.x + b);
	}
}