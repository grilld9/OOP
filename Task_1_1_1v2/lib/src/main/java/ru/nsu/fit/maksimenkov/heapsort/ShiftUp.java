package ru.nsu.fit.maximenkov.heapsort;

public class ShiftUp {
    int buffer = 0;
    public void shiftUp(int[] arr, int idx){
        if (arr[idx] < arr[idx / 2]){
            buffer = arr[idx / 2];
            arr[idx / 2] = arr[idx];
            arr[idx] = buffer;
            shiftUp(arr, idx / 2);
        }
    }
}
