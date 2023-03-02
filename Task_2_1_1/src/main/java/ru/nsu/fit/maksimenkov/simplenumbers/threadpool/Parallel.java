package ru.nsu.fit.maksimenkov.simplenumbers.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import ru.nsu.fit.maksimenkov.simplenumbers.IsSimpleCall;

public class Parallel {
  private int countOfThreads = 2;
  public void setCountOfThreads(int count) {
    countOfThreads = count;
  }
  public boolean parallelExecution(List<Integer> numbers)
      throws InterruptedException, ExecutionException {
    ExecutorService es = Executors.newFixedThreadPool(countOfThreads);
    List<Callable<Boolean>> tasks = new ArrayList<>();
    for (Integer number : numbers) {
      tasks.add(new IsSimpleCall(number));
    }
    long start = System.currentTimeMillis();
    List<Future<Boolean>> listResult = es.invokeAll(tasks);
    System.out.format("Executed by %d ms\n", System.currentTimeMillis() - start);
    for (Future<Boolean> result : listResult) {
      if (result.get()) {
        return true;
      }
    }
    return false;
  }
}




