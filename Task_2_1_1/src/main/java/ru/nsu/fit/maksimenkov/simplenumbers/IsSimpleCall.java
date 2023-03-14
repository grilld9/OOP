package ru.nsu.fit.maksimenkov.simplenumbers;

import java.util.concurrent.Callable;

public class IsSimpleCall implements  Callable<Boolean> {
  int number;
  public IsSimpleCall(int num) {
    this.number = num;
  }
  @Override
  public Boolean call() {
    for (int i = 2; i < number; i++) {
      if (number % i == 0) {
        return false;
      }
    }
    return true;
  }
}
