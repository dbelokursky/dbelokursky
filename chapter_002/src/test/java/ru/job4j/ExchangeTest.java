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
        String expected = "1 1 1 1 1 1 1 1 1 1 \n"
                        + "1 1 1 1 1 5 \n"
                        + "5 5 \n"
                        + "10 \n";
        String result = ex.getExchangeWays(10, new int[]{1, 5, 10});
        assertThat(result, is(expected));
    }
}
