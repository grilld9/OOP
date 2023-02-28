package ru.nsu.fit.maksimenkov.simplenumbers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class Parallel {
  public int countOfThreads = 2;
  private ExecutorService es = Executors.newFixedThreadPool(countOfThreads);
  private List<Callable<Integer>> tasks = new ArrayList<>();

  public boolean parallelExecution(List<Integer> numbers)
      throws InterruptedException, ExecutionException {
    for (Integer number : numbers) {
      tasks.add(new IsSimpleCall(number));
    }
    List<Future<Integer>> listResult = es.invokeAll(tasks);
    for (Future<Integer> result : listResult) {
      if (result.get() == 1) {
        return true;
      }
    }
    return false;
  }
}




