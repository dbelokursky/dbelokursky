package ru.job4j.condition;

/**
* Class Triangle для вычисления площади треугольника.
* @author Dmitriy Belokurskiy
* @since 0.1
*/
public class Triangle {

	/**
	* Поле хранит координату первой вершины треугольника.
	*/
	private Point a;

	/**
	* Поле хранит координату второй вершины треугольника.
	*/
	private Point b;

	/**
	* Поле хранит координату третей вершины треугольника.
	*/
	private Point c;

	/**
	* Конструктор.
	* @param a **координаты точки а**
	* @param b **координаты точки b**
	* @param c **координаты точки c**
	*/
	public Triangle(Point a, Point b, Point c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}
	/**
	* Метод вычисляет площадь прямоугольника на плоскости.
	* Формула: s = 1/2*((x1 - x3)*(y1 - y3) - (x2 - x3)*(y1 - y3))
	* @return result **площадь треугольника**
	*/
	public double area() {
		double result;
		double x1 = this.a.getX();
		System.out.println(x1);
		double x2 = this.b.getX();
		double x3 = this.c.getX();
		double y1 = this.a.getY();
		double y2 = this.b.getY();
		double y3 = this.c.getY();
		result = 0.5 * ((x1 - x3) * (y2 - y3) - (x2 - x3) * (y1 - y3));
		return result;
	}
}