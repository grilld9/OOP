package ru.nsu.fit.maksimenkov.pizzeria;

public interface Producer {
    void putOrder(Order order) throws InterruptedException;
}
