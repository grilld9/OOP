package ru.nsu.fit.maksimenkov.pizzeria.couriers;


import ru.nsu.fit.maksimenkov.pizzeria.Order;
import ru.nsu.fit.maksimenkov.pizzeria.Worker;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class CourierWorker extends Thread implements Worker {
    private final BlockingQueue<Order> warehouse;
    private final BlockingQueue<String> loggerQueue;
    private final List<Order> ordersInTrunk;
    Integer trunkCap;

    public CourierWorker(BlockingQueue<Order> warehouse, Integer trunkCap, BlockingQueue<String> loggerQueue) {
        this.warehouse = warehouse;
        this.loggerQueue = loggerQueue;
        this.trunkCap = trunkCap;
        ordersInTrunk = new ArrayList<>(trunkCap);
    }

    @Override
    public void run() {
        while (!this.isInterrupted()) {
            try {
                Order order = takeOrder();
                while (order != null) {
                    order = takeOrder();
                }
                while (!ordersInTrunk.isEmpty()) {
                    putOrder(ordersInTrunk.get(0));
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public Order takeOrder() throws InterruptedException {
        if (ordersInTrunk.isEmpty()) {
            Order order = warehouse.take();
            ordersInTrunk.add(order);
            loggerQueue.put(order.getOrderNumber() + ", taken by courier");
            return order;
        }
        if (ordersInTrunk.size() < trunkCap) {
            Order order = warehouse.poll();
            if (order != null) {
                ordersInTrunk.add(order);
                loggerQueue.put(order.getOrderNumber() + ", taken by courier");
                return order;
            }
        }
        return null;
    }

    @Override
    public void putOrder(Order order) throws InterruptedException {
        sleep(1000 * 5);
        ordersInTrunk.remove(order);
        loggerQueue.put(order.getOrderNumber() + ", delivered");
    }
}
