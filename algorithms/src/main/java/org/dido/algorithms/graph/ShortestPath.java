package org.dido.algorithms.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Find the shortest path using BFS 
 */
public class ShortestPath {

    private static int GRAPH_SIZE = 14;
    
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

    private static int[] pred = new int[GRAPH_SIZE];
    static{
       Arrays.fill(pred, -1); 
    }
    private static boolean[] used = new boolean[GRAPH_SIZE];

    private static void bfs(int i) {
        // Used this queue for BFS
        Queue<Integer> queue = new LinkedList<Integer>();

        // Set the starting element
        queue.add(i);
        used[i] = true;

        while (!queue.isEmpty()) {
            int currentQueueElement = queue.poll();
            System.out.println(currentQueueElement + " " + Arrays.toString(pred));
            // get next element children
            for (int j = 0; j < GRAPH_SIZE; j++) {
                if (graph[currentQueueElement][j] == 1 && !used[j]) {
                    used[j] = true;
                    pred[j] = currentQueueElement;
                    queue.add(j);
                }
            }
        }
    }

    private static String printPath(int index) {
        StringBuilder result = new StringBuilder();

        if(pred[index] >= 0) {
            printPath(pred[index]);
        }

        System.out.print(index + " ");
        
        return result.toString();
    }

    public static void main(String[] args) {
        int start = 0;
        int end = 10;
        bfs(start);

        if (pred[end] > 0) {
            System.out.println("The shortest path is: " + printPath(end));
        } else {
            System.out.println("No path from " + start + " to "+ end);
        }

    }

}
