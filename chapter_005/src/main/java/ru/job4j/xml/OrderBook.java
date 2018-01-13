package ru.job4j.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Dmitry Belokursky
 * @since 05.01.18.
 */
public class OrderBook {

    private final String fileName;
    private Map<String, Order> orders;

    public OrderBook() {
        this.orders = new HashMap<>(1_000_000);
        this.fileName = "orders.xml";
    }

    public boolean parseFile() {
        boolean result = false;
        DefaultHandler handler = new DefaultHandler() {
            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                if (qName.equals("AddOrder")) {
                    Order currentOrder = new Order(
                            attributes.getValue("book"),
                            attributes.getValue("operation"),
                            Double.parseDouble(attributes.getValue("price")),
                            Integer.parseInt(attributes.getValue("volume")),
                            attributes.getValue("orderId"));
                    orders.put(currentOrder.getOrderId(), currentOrder);
                }
                if (qName.equals("DeleteOrder")) {
                    orders.remove(attributes.getValue("orderId"));
                }
            }
        };
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(fileName, handler);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void print() {
        System.out.println("ASK");
        System.out.println("Volume@Price");
        orders.values().stream()
                .filter(Order -> Order.getBook().equals("book-1") && Order.getOperation().equals("SELL"))
                .collect(Collectors.groupingBy(Order::getPrice, Collectors.summingInt(Order::getVolume)))
                .entrySet()
                .stream()
                .map(e -> new Order("", "", e.getKey(), e.getValue(), ""))
                .sorted((o1, o2) -> -o1.getPrice().compareTo(o2.getPrice()))
                .collect(Collectors.toList())
                .forEach(System.out::println);

        System.out.println("BID");
        System.out.println("Volume@Price");
        orders.values().stream()
                .filter(Order -> Order.getBook().equals("book-1") && Order.getOperation().equals("BUY"))
                .collect(Collectors.groupingBy(Order::getPrice, Collectors.summingInt(Order::getVolume)))
                .entrySet()
                .stream()
                .map(e -> new Order("", "", e.getKey(), e.getValue(), ""))
                .sorted((o1, o2) -> -o1.getPrice().compareTo(o2.getPrice()))
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

    public int size() {
        return orders.size();
    }
}
