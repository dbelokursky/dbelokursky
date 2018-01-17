package ru.job4j.xml;

import org.junit.Test;

import javax.xml.bind.SchemaOutputResolver;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.Assert.*;

/**
 * @author Dmitry Belokursky
 * @since 05.01.18.
 */
public class OrderBookTest {

    @Test
    public void parseFileTest() {
        OrderBook orderBook = new OrderBook();
        orderBook.parseFile();
        orderBook.print("book-2");
    }
}