package ru.job4j.xml;

import java.util.Objects;

/**
 * @author Dmitry Belokursky
 * @since 08.01.18.
 */
public class Order {

    private final String book;

    private final String operation;

    private final Double price;

    private final Integer volume;

    private final String orderId;

    public Order(String book, String operation, double price, Integer volume, String orderId) {
        this.book = book;
        this.operation = operation;
        this.price = price;
        this.volume = volume;
        this.orderId = orderId;
    }

    public String getBook() {
        return book;
    }

    public String getOperation() {
        return operation;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getVolume() {
        return volume;
    }

    public String getOrderId() {
        return orderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Order order = (Order) o;
        return Objects.equals(getBook(), order.getBook())
                && Objects.equals(getOperation(), order.getOperation())
                && Objects.equals(getPrice(), order.getPrice())
                && Objects.equals(getVolume(), order.getVolume())
                && Objects.equals(getOrderId(), order.getOrderId());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getBook(), getOperation(), getPrice(), getVolume(), getOrderId());
    }

    @Override
    public String toString() {
        return  volume + "@" + price;
    }
}
