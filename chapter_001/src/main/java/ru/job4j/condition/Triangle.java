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
	* @return result **площадь треугольника. -1 если через заданные вершины треугольник построить нельзя**
	*/
	public double area() {
		double result;
		double x1 = this.a.getX();
		double x2 = this.b.getX();
		double x3 = this.c.getX();
		double y1 = this.a.getY();
		double y2 = this.b.getY();
		double y3 = this.c.getY();
		double ab = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
		double bc = Math.sqrt((x3 - x2) * (x3 - x2) + (y3 - y2) * (y3 - y2));
		double ac = Math.sqrt((x3 - x1) * (x3 - x1) + (y3 - y1) * (y3 - y1));

        if ((ab + bc) > ac && (bc + ac) > ab && (ab + ac) > ab) {
        	result = Math.abs(0.5 * ((x1 - x3) * (y2 - y3) - (x2 - x3) * (y1 - y3)));
        } else {
        	result = -1;
        }
		return result;
	}
}