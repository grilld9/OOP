package ru.nsu.fit.maksimenkov.pizzeria;

public interface Consumer {
    Order takeOrder() throws InterruptedException;
}
