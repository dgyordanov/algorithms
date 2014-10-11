package org.dido.algorithms.sorting;

import java.util.Arrays;

public class QuickSort {
    private static int[] array = new int[] { 5, 2, 7, 9, 54, 25, 4, 64, 32 };

    private static void quickSort(int left, int right) {
        if (left < right) {
            int q = partition(left, right);
            quickSort(left, q);
            quickSort(q + 1, right);
        }

    }

    private static int partition(int left, int right) {
        System.out.println("left: " + left + " right: " + right);
        System.out.println(Arrays.toString(array));
        int q = left - 1;
        int x = array[right];
        for (int i = left; i <= right; i++) {
            if (array[i] <= x) {
                q++;
                // Put the current element on left + q position because it is
                // less than x
                if (i != q) {
                    array[i] += array[q];
                    array[q] = array[i] - array[q];
                    array[i] = array[i] - array[q];
                }
            }

        }
        System.out.println(Arrays.toString(array));
        System.out.println("q: " + q);
        if (q == right) {
            q--;
        }
        return q;
    }

    public static void main(String[] args) {
        quickSort(0, array.length - 1);
    }
}
