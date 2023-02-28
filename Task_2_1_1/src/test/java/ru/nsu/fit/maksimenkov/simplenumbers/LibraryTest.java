package ru.nsu.fit.maksimenkov.simplenumbers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.Test;

public class LibraryTest {

  @Test
  public void parallelTest() throws InterruptedException, ExecutionException {
    Parallel pl = new Parallel();
    pl.countOfThreads = 2;
    List<Integer> arr = Arrays.asList(4, 5, 6, 7, 8);
    pl.parallelExecution(arr);
  }

  @Test
  public void selfParallelTest()
      throws ExecutionException, InterruptedException, FileNotFoundException {
    SelfParallel sPl = new SelfParallel(6);
    File file = new File("src\\main\\resources\\nums.txt");
    Scanner scanner = new Scanner(file.getAbsoluteFile());
    List<Integer> list = new ArrayList<>();
    while(scanner.hasNext()) {
      list.add(scanner.nextInt());
    }
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
