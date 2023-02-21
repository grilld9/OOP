package ru.nsu.fit.maksimenkov.simplenumbers;

import java.util.List;

public class Sequence {

  public boolean isCollectSimpleNumber(List<Integer> numbers) {
    for (Integer number : numbers) {
      if (isSimple(number)) {
        return true;
      }
    }
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
