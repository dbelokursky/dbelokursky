package ru.job4j.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.jws.Oneway;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.*;

/**
 * @author Dmitry Belokursky
 * @since 05.01.18.
 */
public class OrderBook {

    private Map<String, Order> orderBook;

    private List<Order> ordersList;

    private final String fileName;

    public OrderBook() {
        this.orderBook = new HashMap<>(1_000_000);
        this.ordersList = new ArrayList<>(800_000);
        this.fileName = "/mnt/terra1/Download/orders.xml";
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
                    orderBook.put(currentOrder.getOrderId(), currentOrder);
                }
                if (qName.equals("DeleteOrder")) {
                    orderBook.remove(attributes.getValue("orderId"));
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
        ordersList.addAll(orderBook.values());
        ordersList.sort(new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                int result = o1.getBook().compareTo(o2.getBook());
                if (result == 0) {
                    result = o1.getOperation().compareTo(o2.getOperation());
                }
                if (result == 0) {
                    result = o1.getPrice().compareTo(o2.getPrice());
                }
                return result;
            }
        });
    }

    public void groupByPrice() {
        if (ordersList.size() == 0) {
            getSortedOrdersList();
        }
        List<Order> tmp = new ArrayList<>(500_000);
        int total = 0;
        for (int i = 0; i < ordersList.size() - 1; i++) {
            Order currentOrder = ordersList.get(i);
            Order nextOrder = ordersList.get(i + 1);

            if (currentOrder.getPrice().equals(nextOrder.getPrice())) {
                total += currentOrder.getVolume();
            } else {
                tmp.add(currentOrder);
            }
        }
    }

    public void print() {
        if (ordersList.size() == 0) {
            getSortedOrdersList();
        }
        for (Order order : ordersList) {
            System.out.println(order);
        }
    }

    public int size() {
        return orderBook.size();
    }
}
