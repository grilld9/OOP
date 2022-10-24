package ru.nsu.fit.maksimenkov.heapsort;

import java.util.Arrays;

/**
 * Class to sort integral arrays.
 */
public class HeapSort {

  int buffer = 0;

  private void addNew(int[] arr, int ptr, int value) {
    arr[ptr] = value;
    shiftUp(arr, ptr);
  }

  private void shiftDown(int[] arr, int idx, int last) {
    if ((idx * 2 + 1) > last) {
      return;
    } else if ((idx * 2 + 2) > last) {
      if (arr[idx * 2 + 1] < arr[idx]) {
        buffer = arr[idx * 2 + 1];
        arr[idx * 2 + 1] = arr[idx];
        arr[idx] = buffer;
        shiftDown(arr, idx * 2 + 1, last);
      }
    } else if (arr[idx * 2 + 1] < arr[idx * 2 + 2]) {
      if (arr[idx * 2 + 1] < arr[idx]) {
        buffer = arr[idx * 2 + 1];
        arr[idx * 2 + 1] = arr[idx];
        arr[idx] = buffer;
        shiftDown(arr, idx * 2 + 1, last);
      }
    } else {
      if (arr[idx * 2 + 2] < arr[idx]) {
        buffer = arr[idx * 2 + 2];
        arr[idx * 2 + 2] = arr[idx];
        arr[idx] = buffer;
        shiftDown(arr, idx * 2 + 2, last);
      }
    }
  }

  private void shiftUp(int[] arr, int idx) {
    if (arr[idx] < arr[idx / 2]) {
      buffer = arr[idx / 2];
      arr[idx / 2] = arr[idx];
      arr[idx] = buffer;
      shiftUp(arr, idx / 2);
    }
  }

  private int extractMin(int[] arr, int last) {
    int res = arr[0];
    arr[0] = arr[last];
    arr[last] = res;
    return res;
  }


  /**
   * Method uses all other methods to do heap sort
   * @param arr input array for integer elements
   */
  public void sort(int[] arr) {
    int curr;
    int len = arr.length;
    int[] heap = new int[len];
    for (int i = 0; i < len; i++) {
      addNew(heap, i, arr[i]);
    }
    int last = len - 1;
    for (int i = 0; i < len; i++) {
      arr[i] = extractMin(heap, last);
      last--;
      shiftDown(heap, 0, last);
    }
  }
}