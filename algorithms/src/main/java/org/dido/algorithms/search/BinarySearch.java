package org.dido.algorithms.search;

public class BinarySearch {

    public static int[] sortedArray = new int[] { 2, 4, 6, 7, 8, 9, 33, 54, 65, 74, 77, 87 };

    private static int search(int number, int left, int right) {
        int mid = (right + left) / 2;

        if (sortedArray[mid] == number) {
            return mid;
        }

        if (left == right) {
            return -1;
        }

        if (sortedArray[mid] > number) {
            return search(number, left, mid - 1);
        } else {
            return search(number, mid + 1, right);
        }

    }

    public static void main(String[] args) {
        System.out.println(search(2, 0, sortedArray.length - 1));
    }

}
