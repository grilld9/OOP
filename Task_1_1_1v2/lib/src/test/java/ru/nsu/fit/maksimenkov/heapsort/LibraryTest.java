/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package ru.nsu.fit.maksimenkov.heapsort;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

class Tests {

    @Test
    public void test1() {
        int[] array = new int[]{5, 4, 3, 2, 1};
        System.out.println("test#1:" + Arrays.toString(array));
        Main ma = new Main();
        ma.sort(array);
    }
    @Test
    public void test2() {
        int[] array = new int[]{5, 6, 3, 9, 4, 6, 2, 0, 1, 3, 6, 8, 65, 34, 32, 77, 21, 79};
        System.out.println("test#2:" + Arrays.toString(array));
        Main ma = new Main();
        ma.sort(array);
    }

}