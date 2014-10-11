package org.dido.algorithms.sorting;

import java.util.Arrays;

public class InsertSort {

    private static int[] array = new int[] { 5, 2, 7, 9, 54, 25, 4, 64 };

    public static void main(String[] args) {
        for (int i = 0; i < array.length; i++) {
            int current = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > current) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = current;
        }

        System.out.println(Arrays.toString(array));
    }

}
