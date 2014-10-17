package org.dido.algorithms.sorting;

import java.util.Arrays;

public class HeapSort {

    private static int N;
    private static int[] array = new int[] { 5, 2, 7, 9, 54, 25, 4, 64, 32 };

    /* Sort Function */
    public static void sort(int arr[]) {
        heapify(arr);
        for (int i = N; i > 0; i--) {
            swap(arr, 0, i);
            N = N - 1;
            maxheap(arr, 0);
        }
    }

    public static void heapify(int arr[]) {
        N = arr.length - 1;
        for (int i = N / 2; i >= 0; i--)
            maxheap(arr, i);
    }

    /* Function to swap largest element in heap */
    public static void maxheap(int arr[], int i) {
        System.out.println(N + "; " + i + " -> " + Arrays.toString(array));
        int left = 2 * i;
        int right = 2 * i + 1;
        int max = i;
        if (left <= N && arr[left] > arr[i])
            max = left;
        if (right <= N && arr[right] > arr[max])
            max = right;

        if (max != i) {
            swap(arr, i, max);
            maxheap(arr, max);
        }
    }

    /* Function to swap two numbers in an array */
    public static void swap(int arr[], int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        sort(array);
        System.out.println(Arrays.toString(array));
    }
}