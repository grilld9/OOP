package ru.nsu.fit.maksimenkov.simplenumbers.selfthreadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import ru.nsu.fit.maksimenkov.simplenumbers.IsSimpleCall;

public class SelfParallel {

  private int nThreads;
  public SelfParallel(int nThreads) {
    this.nThreads = nThreads;
  }

  public boolean execution(List<Integer> array) {
    ThreadPool threadPool = new ThreadPool(nThreads);
    List<Callable<Boolean>> tasks = new ArrayList<>();
    for (Integer number : array) {
      tasks.add(new IsSimpleCall(number));
    }
    long start = System.currentTimeMillis();
    List<Boolean> results = threadPool.invokeAll(tasks);
    System.out.format("Executed by %d ms\n", System.currentTimeMillis() - start);
    for (Boolean result : results) {
      if (result) {
        return true;
      }
    }
    System.out.format("Executed by %d ms\n", System.currentTimeMillis() - start);
    return false;
  }
}
