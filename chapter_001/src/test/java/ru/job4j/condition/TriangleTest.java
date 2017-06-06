package ru.job4j.condition;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.number.IsCloseTo.closeTo;

/**
* Class Triangle для вычисления площади треугольника.
* @author Dmitriy Belokurskiy
* @since 0.1
*/
public class TriangleTest {

	/**
	* Тестирует метод Triangle.area
	* x1 = 2, x2 = 1, x3 = -6, y1 = -3, y2 = 1, y3 = 5. S = 12
	*/
	@Test
	public void areaTest() {
		Point a = new Point(2, -3);
		Point b = new Point(1, 1);
		Point c = new Point(-6, 5);
		Triangle triangle = new Triangle(a, b, c);
		double result = triangle.area();
		double expected = 12.0;
		assertThat(result, closeTo(expected, 0.01));
	}
}
