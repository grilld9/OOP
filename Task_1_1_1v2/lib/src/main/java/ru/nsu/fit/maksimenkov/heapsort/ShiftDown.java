package ru.nsu.fit.maksimenkov.heapsort;
public class ShiftDown {
    int buffer = 0;
    public void shiftDown(int[] arr, int idx, int last){
        if ((idx * 2 + 1) > last){
            return;
        }
        else if ((idx * 2 + 2) > last){
            if (arr[idx * 2 + 1] < arr[idx]){
                buffer = arr[idx * 2 + 1];
                arr[idx * 2 + 1] = arr[idx];
                arr[idx] = buffer;
                shiftDown(arr, idx * 2 + 1, last);
            }
        }
        else if (arr[idx * 2 + 1] < arr[idx * 2 + 2]){
            if (arr[idx * 2 + 1] < arr[idx]){
                buffer = arr[idx * 2 + 1];
                arr[idx * 2 + 1] = arr[idx];
                arr[idx] = buffer;
                shiftDown(arr, idx * 2 + 1, last);
            }
        }
        else {
            if (arr[idx * 2 + 2] < arr[idx]){
                buffer = arr[idx * 2 + 2];
                arr[idx * 2 + 2] = arr[idx];
                arr[idx] = buffer;
                shiftDown(arr, idx * 2 + 2, last);
            }
        }
    }
}
