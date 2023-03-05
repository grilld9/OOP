package ru.nsu.fit.maksimenkov.simplenumbers.selfthreadpool;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.*;


public class ThreadPool implements Runnable {
  private final Queue<Callable<Boolean>> mainQueue = new LinkedList<>();
  private List<TaskWorker> workers = new ArrayList<>();
  private List<Boolean> results = new ArrayList<>();

  private Thread taskManager;

  public ThreadPool(int nThreads) {
    taskManager = new Thread(this);
    for (int i = 0; i < nThreads; i++) {
      TaskWorker tw = new TaskWorker();
      workers.add(tw);
    }
  }


  private synchronized void addResult(Boolean result) {
    results.add(result);
  }
  private boolean allThreadsWaiting() {
    for (TaskWorker worker : workers) {
      if (!worker.getState().equals(Thread.State.WAITING)) {
        return false;
      }
    }
    return true;
  }
  public List<Boolean> invokeAll(List<Callable<Boolean>> tasks) {
    mainQueue.addAll(tasks);
    taskManager.start();
    for (TaskWorker worker : workers) {
      worker.start();
    }
    while(taskManager.isAlive());
    return results;
  }
  @Override
  public void run() {
    while (true) {
      for (TaskWorker worker : workers) {
        if (mainQueue.isEmpty()) {
          while(!allThreadsWaiting());
          return;
        }
        else {
          worker.workerQueue.offer(mainQueue.poll());
        }
      }
    }
  }

  private class TaskWorker extends Thread {

    private BlockingQueue<Callable<Boolean>> workerQueue = new LinkedBlockingQueue<>();
    @Override
    public void run() {
      while (true) {
        try {
          Callable<Boolean> nextTask = workerQueue.take();
          addResult(nextTask.call());
        } catch (Exception e) {
          throw new RuntimeException(e);
        }
      }
    }
  }
}
