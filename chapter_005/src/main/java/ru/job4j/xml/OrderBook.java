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
        this.orders = new HashMap<>();
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

    public void print(String bookName) {

        List<Order> ask = orders.values().stream()
                .filter(Order -> Order.getBook().equals(bookName) && Order.getOperation().equals("SELL"))
                .collect(Collectors.groupingBy(Order::getPrice, Collectors.summingInt(Order::getVolume)))
                .entrySet()
                .stream()
                .map(e -> new Order(bookName, "SELL", e.getKey(), e.getValue(), ""))
                .sorted(Comparator.comparing(Order::getPrice))
                .collect(Collectors.toList());

        List<Order> bid = orders.values().stream()
                .filter(Order -> Order.getBook().equals(bookName) && Order.getOperation().equals("BUY"))
                .collect(Collectors.groupingBy(Order::getPrice, Collectors.summingInt(Order::getVolume)))
                .entrySet()
                .stream()
                .map(e -> new Order(bookName, "BUY", e.getKey(), e.getValue(), ""))
                .sorted(Comparator.comparing(Order::getPrice).reversed())
                .collect(Collectors.toList());

        Iterator<Order> askIt = ask.listIterator();
        Iterator<Order> bidIt = bid.listIterator();

        System.out.println(bookName);
        System.out.println("     ASK             BID");

        while (bidIt.hasNext()) {
            if (askIt.hasNext()) {
                System.out.println(bidIt.next() + "         " + askIt.next());
            } else {
                System.out.println(bidIt.next() + "         " + "-----------");
            }


        }

//        System.out.println(bookName);
//        System.out.println("     ASK             BID");
//        System.out.println("Volume@Price    Volume@Price");
//        ask.forEach(System.out::println);
//        bid.forEach(System.out::println);
    }
}
