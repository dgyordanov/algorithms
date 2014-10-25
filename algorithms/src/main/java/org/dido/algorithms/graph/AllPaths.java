package org.dido.algorithms.graph;

import java.util.Arrays;

public class AllPaths {

    private static int GRAPH_SIZE = 14;
    private static boolean[] used = new boolean[GRAPH_SIZE];

    private static int[] pred = new int[GRAPH_SIZE];
    static {
        Arrays.fill(pred, -1);
    }

    private static byte[][] graph = new byte[][] {
        //0  1  2  3  4  5  6  7  8  9 10 11 12 13
        { 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, // 0
        { 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, // 1
        { 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 }, // 2
        { 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0 }, // 3
        { 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 }, // 4
        { 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0 }, // 5
        { 0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0 }, // 6
        { 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 }, // 7
        { 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1 }, // 8
        { 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0 }, // 9
        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0 }, // 10
        { 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 }, // 11
        { 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1 }, // 12
        { 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0 }  // 13
    };

    private static void dfs(int from, int to) {
        // System.out.print("Testing: ");
        // printPath(from);System.out.println();
        used[from] = true;
        for (int j = 0; j < GRAPH_SIZE; j++) {
            if (graph[from][j] == 1) {
                if (j == to) {
                    pred[j] = from;
                    
                    System.out.println("Path found");
                    printPath(to);
                    System.out.println();
                    
                    // Step back
                    used[from] = false;
                    return;
                }

                if (!used[j]) {
                    pred[j] = from;
                    dfs(j, to);
                }
            }
        }
        // Step back
        used[from] = false;
    }

    public static void main(String[] args) {
        dfs(0, 10);
    }

    private static void printPath(int index) {

        if (pred[index] >= 0) {
            printPath(pred[index]);
        }

        System.out.print(index + " ");

    }

}
