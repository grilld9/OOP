package ru.nsu.fit.maximenkov.heapsort;

public class Extract {
    public int extractMin(int[] arr, int last){
        int res = arr[0];
        arr[0] = arr[last];
        arr[last] = res;
        return res;
    }
}
