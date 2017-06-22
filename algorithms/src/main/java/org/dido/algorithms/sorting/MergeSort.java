package org.dido.algorithms.sorting;

import java.util.Arrays;

public class MergeSort {

    public static void main(String[] args) {
        int[] toSort = new int[]{3, 521, 524, 63, 35348, 365, 24, 635, 2, 7467, 564, 356, 1};
        mergesort(toSort);
        System.out.println(Arrays.toString(toSort));
    }

    public static void mergesort(int[] array) {
        int[] tmp = new int[array.length];
        mergesort(array, tmp, 0, array.length - 1);
    }

    private static void mergesort(int[] array, int[] tmp, int left, int right) {
        if (left >= right) {
            // The array has only one element so it is sorted and it is the end of the recursion
            return;
        }

        int mid = (right + left) / 2;

        // Split into 2 arrays and sort them
        mergesort(array, tmp, left, mid);
        mergesort(array, tmp, mid + 1, right);

        // merge the arrays
        merge(array, tmp, left, mid, right);
    }


    private static void merge(int[] array, int[] tmp, int left, int mid, int right) {
        int tmpIndex = left;
        int orgLeft = left;
        int secontHalfIndex = mid + 1;

        while (tmpIndex <= right) {
            if (left > mid) {
                // the whole left part is already in the tmp. Add the rest of the right part which is sorted.
                tmp[tmpIndex++] = array[secontHalfIndex++];
            } else if (secontHalfIndex > right) {
                // the whole right part is already in the tmp. Add the rest of the left part which is sorted.
                tmp[tmpIndex++] = array[left++];
            } else if (array[left] <= array[secontHalfIndex]) {
                tmp[tmpIndex++] = array[left++];
            } else {
                tmp[tmpIndex++] = array[secontHalfIndex++];
            }
        }

        // copy tmp to array
        while (orgLeft <= right) {
            array[orgLeft] = tmp[orgLeft];
            orgLeft++;
        }

    }

}
