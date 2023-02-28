package ru.nsu.fit.maksimenkov.simplenumbers;

import java.util.ArrayList;
import java.util.List;

public class Sequence {

  public boolean isCollectSimpleNumber(List<Integer> numbers) {

    long start = System.currentTimeMillis();
    List<Boolean> results = new ArrayList<>();
    for (Integer number : numbers) {
      results.add(this.isSimple(number));
    }
    for (Boolean result : results) {
      if (result) {
        System.out.format("Executed by %d ms\n", System.currentTimeMillis() - start);
        return true;
      }
    }
    System.out.format("Executed by %d ms\n", System.currentTimeMillis() - start);
    return false;
  }
  private boolean isSimple(int number) {
    for (int i = 2; i < number; i++) {
      if (number % i == 0) {
        return false;
      }
    }
    return true;
  }
}
