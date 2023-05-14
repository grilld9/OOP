package ru.nsu.fit.maksimenkov.pizzeria;

import ru.nsu.fit.maksimenkov.pizzeria.bakers.BakerWorker;
import ru.nsu.fit.maksimenkov.pizzeria.couriers.CourierWorker;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Pizzeria {

    private final Long nBaker;
    private final List<Integer> trunkCaps;
    private final BlockingQueue<Order> mainQueue = new LinkedBlockingQueue<>(100);
    private final BlockingQueue<String> loggerQueue = new LinkedBlockingQueue<>();

    private final BlockingQueue<Order> warehouse;
    private Boolean isInitDone = false;
    private OrderGenerator orderGenerator = new OrderGenerator(mainQueue);

    public Pizzeria(Long nBaker, Long warehouseSize, List<Integer> trunkCaps) {
        this.nBaker = nBaker;
        this.trunkCaps = trunkCaps;
        warehouse = new LinkedBlockingQueue<>(Math.toIntExact(warehouseSize));
    }

    public Pizzeria(PizzeriaConfig pizzeriaConfig) {
        this.nBaker = pizzeriaConfig.getnBaker();
        this.trunkCaps = pizzeriaConfig.getTrunkCaps();
        this.warehouse = new LinkedBlockingQueue<>(Math.toIntExact(pizzeriaConfig.getWarehouse()));
    }
    private void init() {
        initLogger();
        initBakers();
        initCouriers();
        isInitDone = true;
    }

    private void initBakers() {
        for (int i = 0; i < nBaker; i++) {
            BakerWorker baker = new BakerWorker(mainQueue, warehouse, loggerQueue);
            baker.start();
        }
    }

    private void initCouriers() {
        for (Integer cap : trunkCaps) {
            CourierWorker courier = new CourierWorker(warehouse, cap, loggerQueue);
            courier.start();
        }
    }

    private void initLogger() {
        Logger logger = new Logger(loggerQueue);
        logger.start();
    }

    public void addNewOrder(Order order) throws InterruptedException {
        if (!isInitDone) {
            init();
        }
        mainQueue.put(order);
    }

    public void start() throws InterruptedException {
        if (!isInitDone) {
            init();
        }
        orderGenerator.start();
    }
}
