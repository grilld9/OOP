package ru.nsu.fit.maksimenkov.heapsort;

import java.util.Arrays;

public class Main {
  public static void sort(int[] arr) {
    Add add = new Add();
    Extract extr = new Extract();
    ShiftDown sd = new ShiftDown();
    int curr;
    int[] heap = new int[100500];
    int len = arr.length;
    for (int i = 0; i < len; i++) {
      add.add_new(heap, i, arr[i]);
    }
    int last = len - 1;
    for (int i = 0; i < len; i++) {
      arr[i] = extr.extractMin(heap, last);
      last--;
      sd.shiftDown(heap, 0, last);
    }
    System.out.println("result:" + Arrays.toString(arr));
  }
}