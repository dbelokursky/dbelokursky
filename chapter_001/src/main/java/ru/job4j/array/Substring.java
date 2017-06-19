package ru.job4j.array;

/**
* Класс Substring для проверки является ли строка sub подстрокой origin.
* @author Dmitry Belokursky
* @since 0.1 19.06.2017
*/
public class Substring {

	/**
	* Метод проверяет является ли строка sub подстрокой origin.
	* @param origin **Строка в которой происходит поиск подстроки**
	* @param sub **подстрока**
	* @return **результат проверки true/fals**
	*/
	public boolean contains(String origin, String sub) {
		char[] originArr = origin.toCharArray();
		char[] subArr = sub.toCharArray();
		int counter = 0;
		for (int i = 0; i < originArr.length; i++) {
			if (subArr[counter] == originArr[i]) {
				if (subArr[counter] == originArr[i]) {
					counter++;
					if (counter == subArr.length - 1) {
						break;
					}
				}
			}
		}
		return (counter == subArr.length - 1);
	}
}