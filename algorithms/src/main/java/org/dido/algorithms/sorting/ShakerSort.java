package org.dido.algorithms.sorting;

import java.util.Arrays;

public class ShakerSort {
    private static int[] array = new int[] { 5, 2, 7, 9, 54, 25, 4, 64, 32 };

    public static void main(String[] args) {
        int left = 0;
        int right = array.length - 1;
        do {
            for (int i = left; i < right; i++) {
                if (array[i] > array[i + 1]) {
                    // Swap
                    array[i] += array[i + 1];
                    array[i + 1] = array[i] - array[i + 1];
                    array[i] = array[i] - array[i + 1];
                }
            }

            for (int i = right; i > left; i--) {
                if (array[i] < array[i - 1]) {
                    // Swap
                    array[i] += array[i - 1];
                    array[i - 1] = array[i] - array[i - 1];
                    array[i] = array[i] - array[i - 1];
                }
            }

            left++;
            right--;
        } while (left < right);

        System.out.println(Arrays.toString(array));
    }
}
