package ru.nsu.fit.maksimenkov.heapsort;

public class Add {
  ShiftUp su = new ShiftUp();

  public void add_new(int[] arr, int ptr, int value) {
    arr[ptr] = value;
    su.shiftUp(arr, ptr);
  }
}
