package ru.nsu.fit.maksimenkov.simplenumbers;

public class IsSimple {

  public static boolean isSimple(int number) {
    for (int i = 2; i < number; i++) {
      if (number % i == 0) {
        return false;
      }
    }
    return true;
  }
}
