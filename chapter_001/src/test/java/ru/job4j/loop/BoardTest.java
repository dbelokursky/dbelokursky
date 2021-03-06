package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Class BoardTest для тестирования класса Board.
* @author Dmitry Belokursky
* @since 0.1
*/
public class BoardTest {

	/**
	* Метод тестирует метод Board.paimt().
	*/
	@Test
    public void whenPaintBoardWithWidthThreeAndHeightThreeThenStringWithThreeColsAndThreeRows() {
        Board board = new Board();
        String result = board.paint(3, 3);
        final String line = System.getProperty("line.separator");
        String expected = String.format("X X%s X %<sX X%<s", line);
        assertThat(result, is(expected));
 }

	/**
	* Метод тестирует метод Board.paimt().
	*/
    @Test
    public void whenPaintBoardWithWidthFiveAndHeightFourThenStringWithFiveColsAndFourRows() {
        Board board = new Board();
        String result = board.paint(5, 4);
        final String line = System.getProperty("line.separator");
        String expected = String.format("X X X%s X X %<sX X X%<s X X %<s", line);
        assertThat(result, is(expected));
    }

}