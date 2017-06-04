package ru.job4j;

/**
*Class Calculate решение задачи части 001 урок1.
*@author dbelokursky
*@since 0.1
*/
public class Calculate {

/**
*Метод для запуска приложения.
*@param args массив строк с параметрами запуска.
*/
	public static void main(String[] args) {
		System.out.println("Hello World");
}

/**
*Метод для тестирования.
*@param value строка для вывода в консоль.
*@return String value.
*/
public final String echo(final String value) {
	return String.format("$s $s $s", value, value, value);
}
}

