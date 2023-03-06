package ru.nsu.fit.maksimenkov.simplenumbers.selfthreadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import ru.nsu.fit.maksimenkov.simplenumbers.Simple;

public class SelfParallel {
  private final int nThreads;

  public SelfParallel(int nThreads) {
    this.nThreads = nThreads;
  }
  public boolean execution(List<Integer> array) throws ExecutionException, InterruptedException {
    List<Future<Boolean>> futures = new ArrayList<>();
    Simple simple = new Simple();
    ThreadPool threadPool = new ThreadPool(nThreads);
    for (Integer number : array) {
      futures.add(CompletableFuture.supplyAsync(() -> simple.isSimple(number), threadPool));
    }
    long start = System.currentTimeMillis();
    boolean result = false;
    for (Future<Boolean> future : futures) {
      if (future.get()) {
        result = true;
      }
    }
    System.out.format("Executed by %d ms\n", System.currentTimeMillis() - start);
    return result;
  }
}
