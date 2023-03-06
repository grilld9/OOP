package ru.nsu.fit.maksimenkov.simplenumbers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.Test;
import ru.nsu.fit.maksimenkov.simplenumbers.selfthreadpool.SelfParallel;
import ru.nsu.fit.maksimenkov.simplenumbers.sequence.Sequence;
import ru.nsu.fit.maksimenkov.simplenumbers.threadpool.Parallel;

public class LibraryTest {

  @Test
  public void parallelTest() throws InterruptedException, ExecutionException, FileNotFoundException {
    Parallel pl = new Parallel();
    pl.setCountOfThreads(6);
    File file = new File("src\\main\\resources\\nums.txt");
    Scanner scanner = new Scanner(file.getAbsoluteFile());
    List<Integer> list = new ArrayList<>();
    while(scanner.hasNext()) {
      list.add(scanner.nextInt());
    }
    pl.parallelExecution(list);
  }

  @Test
  public void selfParallelTest() throws FileNotFoundException, ExecutionException, InterruptedException {
    SelfParallel sPl = new SelfParallel(6);
    File file = new File("src\\main\\resources\\nums.txt");
    Scanner scanner = new Scanner(file.getAbsoluteFile());
    List<Integer> list = new ArrayList<>();
    while(scanner.hasNext()) {
      list.add(scanner.nextInt());
    }
    //List<Integer> list = Arrays.asList(2, 4, 6, 7, 11);
    System.out.println(sPl.execution(list));
  }

  @Test
  public void sequenceTest() throws FileNotFoundException {
    Sequence sq = new Sequence();
    File file = new File("src\\main\\resources\\nums.txt");
    Scanner scanner = new Scanner(file.getAbsoluteFile());
    List<Integer> list = new ArrayList<>();
    while(scanner.hasNext()) {
      list.add(scanner.nextInt());
    }
    System.out.println(sq.isCollectSimpleNumber(list));
  }
}
