package org.dido.algorithms.common;

public class ReverseString {

    private static String string = "Hallo";

    private static String reverse(String string) {
        if (string.isEmpty()) {
            return string;
        }

        return reverse(string.substring(1)) + string.charAt(0);
    }

    private static String reverseIterative(String string) {
        char[] chararray = string.toCharArray();
        for (int i = 0; i < chararray.length / 2; i++) {
            char tmp = chararray[i];
            chararray[i] = chararray[chararray.length - i - 1];
            chararray[chararray.length - i - 1] = tmp;
        }

        return new String(chararray);
    }

    public static void main(String[] args) {
        System.out.println(reverse(string));
        System.out.println(reverseIterative(string));
    }
}
