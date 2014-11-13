package org.dido.algorithms.graph;

public class FindCycles {

    private static int GRAPH_SIZE = 6;
    private static int[][] graph = new int[][] {
        { 0, 1, 1, 0, 0, 0 },
        { 1, 0, 1, 1, 0, 0 },
        { 1, 1, 0, 0, 0, 1 },
        { 0, 1, 0, 0, 1, 1 },
        { 0, 0, 0, 1, 0, 1 },
        { 0, 0, 1, 1, 1, 0 }
    };

    private static boolean[] used = new boolean[GRAPH_SIZE];
    private static int[] cycle = new int[GRAPH_SIZE];
    private static int d = 0;

    // find a tree
    private static void dfs(int node) {
        used[node] = true;
        for (int i = 0; i < GRAPH_SIZE; i++) {
            if (!used[i] && graph[node][i] == 1) {
                graph[node][i] = 2;
                graph[i][node] = 2;
                dfs(i);
            }
        }
    }

    private static void dfs2(int i, int j) {
        used[i] = true;
        if (i == j) {
            printCycle();
        } else {
            for (int k = 0; k < GRAPH_SIZE; k++) {
                if (!used[k] && graph[i][k] == 2) {
                    cycle[d++] = k + 1;
                    dfs2(k, j);
                    d--;
                }
            }
        }
    }

    private static void printCycle() {
        for (int i = 0; i < d; i++) {
            System.out.print(cycle[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // find a tree
        dfs(0);

        // find cycles
        for (int i = 0; i < GRAPH_SIZE; i++) {
            for (int j = i + 1; j < GRAPH_SIZE; j++) {
                if (graph[i][j] == 1) {
                    // reset used
                    for (int k = 0; k < GRAPH_SIZE; k++) {
                        used[k] = false;
                    }

                    // this link closes a cycle
                    cycle[0] = i + 1;
                    d = 1;
                    // find path from i to j in tree and close it with i-j for a cycle
                    dfs2(i, j);
                }
            }
        }
    }

}
