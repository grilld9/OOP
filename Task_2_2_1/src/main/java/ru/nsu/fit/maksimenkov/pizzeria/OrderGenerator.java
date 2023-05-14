package ru.nsu.fit.maksimenkov.pizzeria;

import java.util.UUID;
import java.util.concurrent.BlockingQueue;

public class OrderGenerator extends Thread {

    BlockingQueue<Order> mainQueue;
    public OrderGenerator(BlockingQueue<Order> mainQueue) {
        this.mainQueue = mainQueue;
    }
    public void run() {
        while (!isInterrupted()) {
            try {
                mainQueue.put(new Order(UUID.randomUUID().toString()));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
