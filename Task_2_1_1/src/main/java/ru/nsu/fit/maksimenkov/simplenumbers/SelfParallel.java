package ru.nsu.fit.maksimenkov.simplenumbers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


public class SelfParallel {

  int nThreads;
  SelfParallel(int nThreads) {
    this.nThreads = nThreads;
  }

  public boolean execution(List<Integer> array) throws ExecutionException, InterruptedException {
    ThreadPool threadPool = new ThreadPool(nThreads);
    new Thread(threadPool).start();
    long start = System.currentTimeMillis();

    List<Future<Boolean>> futures = new ArrayList<>();
    for (Integer number : array) {
      futures.add(
          CompletableFuture.supplyAsync(
              () -> IsSimple.isSimple(number),
              threadPool
          ));
    }
    boolean res = false;
    for (Future<Boolean> future : futures) {
      boolean simple = future.get();
      if (simple) {
        res = true;
      }
    }
      System.out.format("Executed by %d ms\n", System.currentTimeMillis() - start);
      threadPool.shutdown();
      return res;
  }
}
