package ru.job4j;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Dmitry Belokursky
 * @since 13.08.17.
 */
public class ExchangeTest {

    @Test
    public void changeTest() {
        Exchange ex = new Exchange();
        String result = ex.change(50);
        String expected = "50 монет номиналом 1 рубль\n" +
                "25 монет номиналом 2 рубля\n" +
                "10 монет номиналом 5 рублей\n" +
                "5 монет номиналом 10 рублей";
        assertThat(result, is(expected));
    }
}
