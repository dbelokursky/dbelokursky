package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Class FactorialTest для тестирования класса Factorial.
*/
public class FactorialTest {
	/**
	* Метод тестирует метод Factorial.calc(). Расчет факториала 5.
	*/
    @Test
    public void whenCalculateFactorialForFiveThenOneHundreedTwenty() {
        Factorial factorial = new Factorial();
        int result = factorial.calc(5);
        int expected = 120;
        assertThat(result, is(expected));
    }

	/**
	* Метод тестирует метод Factorial.calc(). Расчет факториала 0.
	*/
    @Test
    public void whenCalculateFactorialForZeroThenOne() {
    	Factorial factorial = new Factorial();
        int result = factorial.calc(0);
        int expected = 1;
        assertThat(result, is(expected));
    }
}