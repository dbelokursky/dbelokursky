package ru.job4j.loop;

/**
* Class Board для построения шахматной доски.
* @author Dmitry Belokursky
* @since 0.1
*/
public class Board {

	/**
	* Метод строит шахматную доску.
	* @param width **ширина доски**
	* @param height **высота доски**
	* @return **строку содержащую построенную доску**
	*/
	public String paint(int width, int height) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if ((i + j) % 2 == 0) {
					sb.append("X");
				} else {
					sb.append(" ");
				}
			}
			sb.append(System.getProperty("line.separator"));
		}
		return sb.toString();

	}
}