package ru.job4j.strategy;


import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author Dmitry Belokursky
 * @since 08.07.17.
 */
public class PaintTest {

    @Test
    public void whenDrawSquareThenSquare() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Paint paint = new Paint();
        paint.setShape(new Square());
        paint.draw();

        StringBuilder sb = new StringBuilder();
        String expected = sb.append("******\n")
                            .append("******\n")
                            .append("******\n")
                            .append("******\n")
                            .toString();
        assertThat(out.toString(), is(expected));
    }

    @Test
    public void whenDrawTriangleThenTriangle() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Paint paint = new Paint();
        paint.setShape(new Triangle());
        paint.draw();

        StringBuilder sb = new StringBuilder();
        String expected = sb.append("   *\n")
                            .append("  ***\n")
                            .append(" *****\n")
                            .append("*******\n")
                            .toString();
        assertThat(out.toString(), is(expected));
    }
}
