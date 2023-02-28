package ru.nsu.fit.maksimenkov.simplenumbers;

import java.util.concurrent.Callable;

public class IsSimpleCall implements  Callable<Integer> {
  int number;
  IsSimpleCall(int num) {
    this.number = num;
  }
  @Override
  public Integer call() throws Exception {
    for (int i = 2; i < number; i++) {
      if (number % i == 0) {
        return 0;
      }
    }
    return 1;
  }
}
