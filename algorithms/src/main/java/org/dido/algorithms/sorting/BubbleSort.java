package org.dido.algorithms.sorting;

import java.util.Arrays;

public class BubbleSort {

    private static int[] array = new int[] { 5, 2, 7, 9, 54, 25, 4, 64 , 32};

    public static void main(String[] args) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    // Swap
                    array[j] += array[j + 1];
                    array[j + 1] = array[j] - array[j + 1];
                    array[j] = array[j] - array[j + 1];
                }
            }
        }

        System.out.println(Arrays.toString(array));
    }

}
