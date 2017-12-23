package org.dido.algorithms.problems;

public class HanoiTowers {

    public static void move(int numberOfRings, String source, String destination, String auxiliary) {
        if ( numberOfRings == 0) {
            // Nothing to move
            return;
        }

        // Move numberOfRings-1 elements to auxiliary
        move(numberOfRings - 1, source, auxiliary, destination);

        // Move the Nth element to the destination
        System.out.println(String.format("Move element %s from %s to %s", numberOfRings, source, destination));

        // Move back all numberOfRings-1 elements from auxiliary to destination
        move(numberOfRings - 1, auxiliary, destination, source);

    }

    public static void main(String[] args) {
        move(6, "source", "destination", "auxiliary");
    }

}
