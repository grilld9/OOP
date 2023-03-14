package ru.nsu.fit.maksimenkov.simplenumbers.selfthreadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ThreadPool implements Executor {
  private final BlockingQueue<Runnable> mainQueue = new LinkedBlockingQueue<>();
  private final List<TaskWorker> workers = new ArrayList<>();
  private boolean isInitDone = false;

  public ThreadPool(int nThreads) {
    for (int i = 0; i < nThreads; i++) {
      TaskWorker taskWorker = new TaskWorker();
      workers.add(taskWorker);
    }
  }

  private void init() {
    TaskManager taskManager = new TaskManager();
    taskManager.start();
    for (TaskWorker worker : workers) {
      worker.start();
    }
    isInitDone = true;
  }

  @Override
  public void execute(Runnable command) {
    if (!isInitDone) {
      init();
    }
    mainQueue.add(command);
  }

  private static class TaskWorker extends Thread {
    private final BlockingQueue<Runnable> workerQueue = new LinkedBlockingQueue<>();

    @Override
    public void run() {
      while (!this.isInterrupted()) {
        try {
          Runnable nextTask = workerQueue.take();
          nextTask.run();
        } catch (Exception e) {
          throw new RuntimeException(e);
        }
      }
    }
  }
  private class TaskManager extends Thread {
    @Override
    public void run() {
      while (!this.isInterrupted()) {
        for (TaskWorker worker : workers) {
          try {
            Runnable task = mainQueue.take();
            worker.workerQueue.add(task);
          } catch (InterruptedException e) {
            throw new RuntimeException(e);
          }
        }
      }
    }
  }
}
