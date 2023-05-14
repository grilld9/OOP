package ru.nsu.fit.maksimenkov.pizzeria.bakers;

import ru.nsu.fit.maksimenkov.pizzeria.Consumer;
import ru.nsu.fit.maksimenkov.pizzeria.Order;
import ru.nsu.fit.maksimenkov.pizzeria.Producer;
import ru.nsu.fit.maksimenkov.pizzeria.Worker;


import java.util.concurrent.BlockingQueue;


public class BakerWorker extends Worker implements Consumer, Producer {

    private final BlockingQueue<Order> mainQueue;
    private final BlockingQueue<String> loggerQueue;
    private final BlockingQueue<Order> warehouse;

    public BakerWorker(BlockingQueue<Order> mainQueue, BlockingQueue<Order> warehouse,
        BlockingQueue<String> loggerQueue) {
        this.mainQueue = mainQueue;
        this.warehouse = warehouse;
        this.loggerQueue = loggerQueue;
    }

    @Override
    public void doWork() {
        try {
            Order order = takeOrder();
            putOrder(order);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        while (!this.isInterrupted()) {
            doWork();
        }
    }

    @Override
    public Order takeOrder() throws InterruptedException {
        Order nextOrder = mainQueue.take();
        loggerQueue.put(nextOrder.getOrderNumber() + ", cooking started");
        sleep(1000 * 5);
        loggerQueue.put(nextOrder.getOrderNumber() + ", cooked");
        return nextOrder;
    }

    @Override
    public void putOrder(Order order) throws InterruptedException {
        warehouse.put(order);
        loggerQueue.put(order.getOrderNumber() + ", stored into warehouse");
    }
}
