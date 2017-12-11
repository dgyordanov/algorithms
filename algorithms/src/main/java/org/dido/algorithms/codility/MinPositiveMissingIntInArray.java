package org.dido.algorithms.codility;

// you can also use imports, for example:
// import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class MinPositiveMissingIntInArray {

    public int solution(int[] A) {
        // Put all not positive at the beginning
        int positiveStartIndex = putNotPositiveFirstFirst(A);

        // Make all the element positive
        for (int i = 0; i < positiveStartIndex; i++) {
            A[i] = -A[i];
        }

        // for each positive element smaller than the A.length, put a negative sign for the number at position A[i] - 1
        for (int i = positiveStartIndex; i < A.length; i++) {
            int notNegAi = Math.abs(A[i]);
            if (0 < notNegAi && notNegAi <= A.length) {
                A[notNegAi - 1] = -Math.abs(A[notNegAi - 1]);
                if (A[notNegAi - 1] == 0) {
                    A[notNegAi - 1] = -1;
                }
            }
        }

        for (int i = 0; i < A.length; i++) {
            if (A[i] >= 0) {
                // found a positive number in the array so it is the smalled positive missing number
                return i + 1;
            }
        }

        // Seems that all the number in the array were between 1 and A.length so return the next one
        return A.length + 1;
    }

    private int putNotPositiveFirstFirst(int[] array) {
        int j = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] <= 0) {
                // swap
                array[j] = array[j] + array[i];
                array[i] = array[j] - array[i];
                array[j] = array[j] - array[i];
                j++;
            }
        }
        return j;
    }

    public static void main(String[] args) {
        MinPositiveMissingIntInArray s = new MinPositiveMissingIntInArray();
        System.out.println(s.solution(new int[]{1, 3, 6, 4, 1, 2}));
        System.out.println(s.solution(new int[]{1, 3, 6, 4, 1, -2}));
        System.out.println(s.solution(new int[]{1, 3, 60, 0, 1, 2}));
    }

}
