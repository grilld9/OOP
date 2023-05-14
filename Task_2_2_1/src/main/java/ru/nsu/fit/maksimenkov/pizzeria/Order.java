package ru.nsu.fit.maksimenkov.pizzeria;

public class Order {

    public String getOrderNumber() {
        return orderNumber;
    }

    private final String orderNumber;

    public Order(String orderNumber) {
        this.orderNumber = orderNumber;
    }
}
