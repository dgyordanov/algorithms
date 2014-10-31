package org.dido.algorithms.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Dejkstra {

    // The starting point to each graph
    private static final int NODE = 0;

    private static int GRAPH_SIZE = 14;

    private static byte[][] graph = new byte[][] { 
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

    private static List<Integer> notProcessedNodes = new LinkedList<Integer>();

    private static int[] distances = new int[GRAPH_SIZE];

    public static void main(String[] args) {
        // Initialize notProcessedNodes with all nodes
        for (int i = 0; i < GRAPH_SIZE; i++) {
            if (NODE != i) {
                notProcessedNodes.add(i);
            }
        }

        // Initialize distances with very big values
        Arrays.fill(distances, Integer.MAX_VALUE / 2);

        // Initialize Distances
        for (int i = 0; i < GRAPH_SIZE; i++) {
            if (graph[NODE][i] > 0) {
                distances[i] = graph[NODE][i];
            } else {
                distances[i] = Integer.MAX_VALUE / 2;
            }
        }

        // ===== the algorithm =====
        while (!notProcessedNodes.isEmpty()) {
            // find closest node and remove it from the list
            int nextClosestNode = findClosestNode();
            notProcessedNodes.remove(new Integer(nextClosestNode));

            for (int i = 0; i < GRAPH_SIZE; i++) {
                if (graph[nextClosestNode][i] != 0 &&
                        distances[i] > distances[nextClosestNode] + graph[nextClosestNode][i]) {
                    distances[i] = distances[nextClosestNode] + graph[nextClosestNode][i];
                }
            }
            System.out.println(Arrays.toString(distances));
        }

        System.out.println(Arrays.toString(distances));
    }

    private static int findClosestNode() {
        int distance = Integer.MAX_VALUE;
        int result = 0;
        for (int i : notProcessedNodes) {
            if (distances[i] < distance) {
                result = i;
                distance = distances[i];
            }
        }
        return result;
    }

}
