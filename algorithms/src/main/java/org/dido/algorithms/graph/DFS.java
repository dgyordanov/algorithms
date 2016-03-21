package org.dido.algorithms.graph;

import java.util.Arrays;

public class DFS {

    private static int GRAPH_SIZE = 14;
    private static boolean[] used = new boolean[GRAPH_SIZE];

    private static int[] pred = new int[GRAPH_SIZE];

    static {
        Arrays.fill(pred, -1);
    }

    private static byte[][] graph = new byte[][]{
           //0  1  2  3  4  5  6  7  8  9 10 11 12 13
            {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // 0
            {1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // 1
            {0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0}, // 2
            {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0}, // 3
            {0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0}, // 4
            {0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0}, // 5
            {0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0}, // 6
            {0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0}, // 7
            {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1}, // 8
            {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0}, // 9
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0}, // 10
            {0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0}, // 11
            {0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1}, // 12
            {0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0}  // 13
    };

    public static void dfs(int v) {
        System.out.println(v + " ");
        used[v] = true;
        for (int i = 0; i < graph[v].length; i++) {
            if (graph[v][i] == 1 && !used[i]) {
                dfs(i);
            }
        }
    }

    public static void main(String[] args) {
        dfs(0);
    }
}
