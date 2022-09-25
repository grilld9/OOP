package ru.nsu.fit.maximenkov.heapsort;

public class Main {
    public static void main(String[] args){
        Add add = new Add();
        Extract extr = new Extract();
        ShiftDown sd = new ShiftDown();
        int curr;
        int[] arr_input = new int[] {5, 4, 3, 2, 1};
        int[] heap = new int[100500];
        int len = arr_input.length;
        for (int i = 0; i < len; i++){
            add.add_new(heap, i, arr_input[i]);
        }
        int last = len - 1;
        for (int i = 0; i < len; i++){
            curr = extr.extractMin(heap, last);
            System.out.println(curr);
            last--;
            sd.shiftDown(heap, 0, last);
        }
    }
}