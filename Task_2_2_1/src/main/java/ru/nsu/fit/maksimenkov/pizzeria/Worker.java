package ru.nsu.fit.maksimenkov.pizzeria;

public interface Worker {

    Order takeOrder() throws InterruptedException;

    void putOrder(Order order) throws InterruptedException;
}
