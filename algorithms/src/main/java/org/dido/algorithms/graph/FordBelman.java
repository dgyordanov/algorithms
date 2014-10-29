package org.dido.algorithms.graph;

import java.util.Arrays;

/**
 * Find shortest path from a node to all nodes 
 */
public class FordBelman {
   
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
    
    private static int[] distances = new int[GRAPH_SIZE];
    
    public static void main(String[] args) {
        // fill distances with max value at the beginning
        Arrays.fill(distances, Integer.MAX_VALUE);

        // set distances to direct children of the node
        for (int i = 0; i < GRAPH_SIZE; i++) {
            if (graph[NODE][i] > 0) {
                // set the distance to children
                distances[i] = graph[NODE][i];
            }
        }
        // initialization phase finished

        for (int k = 0; k < GRAPH_SIZE - 2; k++) {
            // repeat triangle inequality GRAPH_SIZE - 2 times in order to reach
            // the longest possible distance
            for (int i = 0; i < GRAPH_SIZE; i++) {
                if (i == NODE) {
                    // do not want to find a path to itself
                    continue;
                }

                for (int j = 0; j < GRAPH_SIZE; j++) {
                    if (distances[j] < Integer.MAX_VALUE && graph[j][i] > 0 // there is a path to i through j
                            && distances[i] > distances[j] + graph[j][i]) {
                        // there is a path through j which is shorter
                        distances[i] = distances[j] + graph[j][i];
                    }
                }
            }
        }

        System.out.println(Arrays.toString(distances));
    }
}
