package ru.job4j.calculator;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Class CalculatorTest для тестирование класса Calculator.
* @author dbelokursky
* @since 0.1
*/
public class CalculatorTest {

	/**
	* Метод тестирует операцию сложения.
	*/
	@Test
	public void whenAddOnePlusOneThenTwo() {
		Calculator calc = new Calculator();
		calc.result = 0;
		calc.add(1D, 1D);
		double result = calc.getResult();
		double expected = 2D;
		assertThat(result, is(expected));
	}

	/**
	* Метод тестирует операцию вычитания.
	*/
	@Test
	public void whenSubstructTwoMinusOneThenOne() {
		Calculator calc = new Calculator();
		calc.result = 0;
		calc.add(2D, 1D);
		double result = calc.getResult();
		double expected = 1D;
		assertThat(result, is(expected));
	}

	/**
	* Метод тестирует операцию деления.
	*/
	@Test
	public void whenDivOneDivideOneThenOne() {
		Calculator calc = new Calculator();
		calc.result = 0;
		calc.add(1D, 1D);
		double result = calc.getResult();
		double expected = 1D;
		assertThat(result, is(expected));
	}

	/**
	* Метод тестирует операцию умножения.
	*/
	@Test
	public void whenMultiplyOneMultiplylOneThenOne() {
		Calculator calc = new Calculator();
		calc.result = 0;
		calc.add(1D, 1D);
		double result = calc.getResult();
		double expected = 1D;
		assertThat(result, is(expected));
	}
}