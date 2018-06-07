package ru.job4j;

/**
 * @author Dmitry Belokursky
 * @since 21.08.17.
 */

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ExchangeTest {

    @Test
    public void getExchangeWaysTest() {
        Exchange ex = new Exchange();
        String expected = "1 1 1 1 1 1 1 1 1 1 "
                + System.getProperty("line.separator")
                + "1 1 1 1 1 5 "
                + System.getProperty("line.separator")
                + "5 5 "
                + System.getProperty("line.separator")
                + "10 "
                + System.getProperty("line.separator");
        String result = ex.getExchangeWays(10, new int[]{1, 5, 10});
        assertThat(result, is(expected));
    }
}
