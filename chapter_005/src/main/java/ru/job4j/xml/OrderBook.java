package ru.job4j.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.*;

/**
 * @author Dmitry Belokursky
 * @since 05.01.18.
 */
public class OrderBook {

    private final String fileName;
    private Map<String, Order> orders;
    private List<Order> ordersList;

    public OrderBook() {
        this.orders = new HashMap<>(1_000_000);
        this.ordersList = new ArrayList<>(800_000);
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
                            attributes.getValue("price"),
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

    public void getSortedOrdersList() {
        ordersList.addAll(orders.values());

        ordersList.sort(Comparator.comparing(Order::getBook)
                  .thenComparing(Order::getOperation)
                  .thenComparing(Order::getPrice));
    }

    public void print() {
        orders.values().stream().forEach(System.out::println);
    }

    public int size() {
        return orders.size();
    }
}
