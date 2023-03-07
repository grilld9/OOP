package ru.nsu.fit.maksimenkov.simplenumbers.selfthreadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import ru.nsu.fit.maksimenkov.simplenumbers.IsSimpleCall;
//import ru.nsu.fit.maksimenkov.simplenumbers.Simple;

public class SelfParallel {
  private final int nThreads;

  public SelfParallel(int nThreads) {
    this.nThreads = nThreads;
  }
  public boolean execution(List<Integer> array) throws ExecutionException, InterruptedException {
    List<FutureTask<Boolean>> futures = new ArrayList<>();
    //Simple simple = new Simple();
    ThreadPool threadPool = new ThreadPool(nThreads);
    for (Integer number : array) {
      FutureTask<Boolean> ft = new FutureTask<>(new IsSimpleCall(number));
      futures.add(ft);
      threadPool.execute(ft);
      //futures.add(CompletableFuture.supplyAsync(() -> simple.isSimple(number), threadPool));
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
