package ru.nsu.fit.maksimenkov.simplenumbers;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;

public class ThreadPool implements Executor, Runnable {
  private final Queue<Runnable> workQueue = new ConcurrentLinkedQueue<>();

  private List<TaskWorker> workers = new ArrayList<>();
  private volatile boolean isRunning = true;

  public ThreadPool(int nThreads) {
    for (int i = 0; i < nThreads - 1; i++) {
      TaskWorker tw = new TaskWorker();
      workers.add(tw);
      tw.start();
    }
  }

  @Override
  public void execute(Runnable command) {
    if (isRunning) {
      workQueue.offer(command);
    }
  }

  public void shutdown() {
    isRunning = false;
  }

  @Override
  public void run() {
    TaskWorker tw = new TaskWorker();
    while (!workQueue.isEmpty()) {
      for (TaskWorker worker : workers) {
        if (!workQueue.isEmpty()) {
          worker.bq.add(workQueue.poll());
        }
      }
    }
  }

  private class TaskWorker extends Thread {

    private BlockingQueue<Runnable> bq = new ArrayBlockingQueue<>(1000);
    @Override
    public void run() {
      while (Thread.currentThread().isInterrupted()) {
        Runnable nextTask = workQueue.poll();
        if (nextTask != null) {
          nextTask.run();
        }
      }
    }
  }
}
