package ru.nsu.fit.maksimenkov.pizzeria;

import java.util.concurrent.BlockingQueue;

public class Logger extends Thread {

    private final BlockingQueue<String> loggerQueue;
    public Logger(BlockingQueue<String> loggerQueue) {
        this.loggerQueue = loggerQueue;
    }
    @Override
    public void run() {
        while(!isInterrupted()) {
            try {
                System.out.println(loggerQueue.take());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
