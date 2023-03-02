package ru.nsu.fit.maksimenkov.simplenumbers.selfthreadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;


public class ThreadPool implements Runnable {
  private final Queue<Callable<Boolean>> mainQueue = new ConcurrentLinkedQueue<>();

  private List<TaskWorker> workers = new ArrayList<>();
  public volatile List<Boolean> results = new ArrayList<>();
  private volatile boolean isTMRunning = true;

  public ThreadPool(int nThreads) {
    for (int i = 0; i < nThreads; i++) {
      TaskWorker tw = new TaskWorker();
      workers.add(tw);
      tw.start();
    }
  }


  private boolean noThreadsAlive() {
    for (TaskWorker worker : workers) {
      if (worker.isAlive()) {
        return false;
      }
    }
    return true;
  }
  public List<Boolean> invokeAll(List<Callable<Boolean>> tasks) {
    mainQueue.addAll(tasks);
    new Thread(this).start();
    while (!noThreadsAlive());
    return results;
  }
  @Override
  public void run() {
    isTMRunning = true;
    while (!mainQueue.isEmpty()) {
      for (TaskWorker worker : workers) {
        if (!mainQueue.isEmpty()) {
          if (worker.workerQueue.remainingCapacity() > 0) {
            worker.workerQueue.offer(mainQueue.poll());
          }
        }
      }
    }
    isTMRunning = false;
  }

  private class TaskWorker extends Thread {

    private BlockingQueue<Callable<Boolean>> workerQueue = new ArrayBlockingQueue<>(1000);
    @Override
    public void run() {
      while (!workerQueue.isEmpty() || isTMRunning) {
        Callable<Boolean> nextTask = workerQueue.poll();
        if (nextTask != null) {
          try {
            results.add(nextTask.call());
          } catch (Exception e) {
            throw new RuntimeException(e);
          }
        }
      }
    }
  }
}
