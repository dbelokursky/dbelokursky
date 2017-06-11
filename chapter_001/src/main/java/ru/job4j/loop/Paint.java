package ru.job4j.loop;

/**
* Class Paint для построения шахматной пирамиды.
* @author Dmitry Belokursky
* @since 0.1
*/
public class Paint {

	/**
	* Метод строит пирамиду.
	* @param h **высота пирамиды**
	* @return **строку содержащую построенную пирамиду**
	*/
	public String pyramid(int h) {
        StringBuilder sb = new StringBuilder();
        int cntr = 1;
        for (int i = 1; i <= h; i++) {
            String row = new String(new char[cntr]).replace("\0", "^");
            String offset = new String(new char[h - i]).replace("\0", " ");
            sb.append(offset).append(row).append(System.getProperty("line.separator"));
            cntr += 2;
        }
        return sb.toString();
    }
}