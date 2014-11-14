package org.dido.algorithms.graph;

import java.util.Arrays;

public class HamiltonCycle {

    private static int GRAPH_SIZE = 6;
    private static int START_NODE = 0;
    private static int[][] graph = new int[][] {
        { 0, 5, 0, 0, 7, 7 }, 
        { 5, 0, 5, 0, 0, 0 }, 
        { 0, 5, 0, 6, 5, 0 }, 
        { 0, 0, 6, 0, 3, 3 }, 
        { 7, 0, 5, 3, 0, 5 }, 
        { 7, 0, 0, 3, 5, 0 }
    };
    
    private static boolean[] used = new boolean[GRAPH_SIZE];
    
    private static int[] minHamCycle = new int[GRAPH_SIZE + 1];
    private static int[] currentCycle = new int[GRAPH_SIZE + 1];
    private static int curSum = 0, minSum = Integer.MAX_VALUE;
    
    
    private static void hamilton(int node, int level) {
        // Add the current node in the cycle
        currentCycle[level] = node + 1;

        if ((START_NODE == node) && (level > 0)) {
            if (level == GRAPH_SIZE) {
                // the cycle is hamilton with min sum
                minSum = curSum;
                System.arraycopy(currentCycle, 0, minHamCycle, 0, currentCycle.length);
            }
        }

        if (used[node]) {
            // the cycle is not hamilton
            return;
        }

        used[node] = true;
        for (int i = 0; i < GRAPH_SIZE; i++) {
            if (graph[node][i] > 0 && node != i) {
                curSum += graph[node][i];
                // check if there is a hamilton cycle shorter than the current path
                if (curSum < minSum) {
                    hamilton(i, level + 1);
                }
                // Remove the node -> i length from the current sum (step back)
                curSum -= graph[node][i];
            }
        }

        // Step back
        used[node] = false;
    }
    
    public static void main(String[] args) {
        hamilton(START_NODE, 0);
        System.out.println(Arrays.toString(minHamCycle));
    }

}
