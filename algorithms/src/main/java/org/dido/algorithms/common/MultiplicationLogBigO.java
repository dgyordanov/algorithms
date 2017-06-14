package org.dido.algorithms.common;

/**
 * Multiply 2 numbers x and y with O(log(n))
 */
public class MultiplicationLogBigO {


    public static int multiply(int x, int y) {
        int multiplier = y >= 0 ? 1 : -1;

        // End of recursion
        if (y == 0) return 0;
        if (y == 1) return x;
        if (y == -1) return -x;

        int z = multiply(2 * x, y / 2);
        if (y % 2 == 0) {
            return z;
        } else {
            return z + (x * multiplier);
        }

    }

    public static void main(String[] args) {
        System.out.println(multiply(-325, -2341));
    }

}
