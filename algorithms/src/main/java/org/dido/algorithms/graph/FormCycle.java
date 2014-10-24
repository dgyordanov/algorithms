package org.dido.algorithms.graph;

public class FormCycle {

    private static int GRAPH_SIZE = 14;
    private static boolean[] used = new boolean[GRAPH_SIZE];

    private static byte[][] graph = new byte[][] { 
        { 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
        { 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
        { 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
        { 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0 },
        { 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 },
        { 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0 },
        { 0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0 },
        { 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
        { 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1 },
        { 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0 },
        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0 },
        { 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
        { 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1 },
        { 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0 }
    };
    private static boolean cycl = false;
    
    private static void dfs(int i, int parent) {
        if (cycl) {
            return;
        }
        used[i] = true;
        for (int j = 0; j < GRAPH_SIZE; j++) {
            if (used[j] && parent != j) {
                System.out.println("Cycle found");
                cycl = true;
            } else {
                if (parent != j) {
                    dfs(j, i);
                }
            }
        }
    }
    
    public static void main(String[] args) {
        for (int i = 0; i < GRAPH_SIZE; i++) {
            dfs(i, -1);
            if (cycl) break;
        }
    }
}
