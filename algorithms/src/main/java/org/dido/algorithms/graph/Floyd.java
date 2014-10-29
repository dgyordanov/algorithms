package org.dido.algorithms.graph;

import java.util.Arrays;

public class Floyd {

    private static int GRAPH_SIZE = 14;

    private static int[][] graph = new int[][] { 
        { 0, 11,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
        { 11,0, 8, 3,14, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
        { 0, 8, 0, 0, 0, 9, 0, 0, 0, 0, 0, 0, 0, 0 },
        { 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 0 },
        { 0, 14,0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0 },
        { 0, 0, 9, 0, 0, 0, 2, 0, 0, 0, 0, 1, 0, 0 },
        { 0, 0, 0, 0, 3, 2, 0, 0, 0, 21,0, 0, 0, 0 },
        { 0, 0, 0, 0, 0, 0, 0, 0, 11,0, 0, 0, 0, 0 },
        { 0, 0, 0, 0, 0, 0, 0, 11,0, 0, 0, 0, 12,13 },
        { 0, 0, 0, 0, 0, 0, 21,0, 0, 0, 4, 0, 0, 0 },
        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0 },
        { 0, 0, 0, 7, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
        { 0, 0, 0, 0, 0, 0, 0, 0, 12,0, 0, 0, 0, 8 },
        { 0, 0, 0, 0, 0, 0, 0, 0, 13,0, 0, 0, 8, 0 }
    };
    
    public static void main(String[] args) {
        for (int i = 0; i < GRAPH_SIZE; i++) {
            for (int j = 0; j < GRAPH_SIZE; j++) {
                if (graph[i][j] == 0) {
                    // Set a huge number for initial distance if there is no
                    // direct path i-j in order comparison below to be easier.
                    // Do not use Integer.MAX_VALUE in order to escape overflow.
                    graph[i][j] = Integer.MAX_VALUE / 2;
                }
            }
        }

        for (int k = 0; k < GRAPH_SIZE; k++) {
            for (int i = 0; i < GRAPH_SIZE; i++) {
                for (int j = 0; j < GRAPH_SIZE; j++) {
                    if (i == j) {
                        continue;
                    }
                    if (graph[i][j] > graph[i][k] + graph[k][j]) {
                        // there is a path i-j through k which is shorter
                        graph[i][j] = graph[i][k] + graph[k][j];
                    }
                }
            }
        }

        printGraph();
    }

    public static void printGraph() {
        for (int i = 0; i < GRAPH_SIZE; i++) {
            for (int j = 0; j < GRAPH_SIZE; j++) {
                System.out.printf("%10d ", graph[i][j]);
            }
            System.out.println();
        }
    }
}
