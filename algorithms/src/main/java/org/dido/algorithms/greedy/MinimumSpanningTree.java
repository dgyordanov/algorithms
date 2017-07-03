package org.dido.algorithms.greedy;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Find the minimum spanning tree found by Kruskal’s greedy algorithm.
 * Input: a weighted graph passed as a list of edges
 * Output: the minimum spanning tree as a list of edges
 */
public class MinimumSpanningTree {

    public static void main(String[] args) {
        List<Edge> allEdges = initialGraph();


        List<Edge> result = findMinimumSpanningTree(allEdges);

        result.forEach(System.out::println);
    }

    private static List<Edge> initialGraph() {
        List<Edge> allEdges = new LinkedList<>();
        allEdges.add(new Edge(1, 2, 5));
        allEdges.add(new Edge(1, 3, 6));
        allEdges.add(new Edge(1, 4, 4));
        allEdges.add(new Edge(2, 3, 1));
        allEdges.add(new Edge(2, 4, 2));
        allEdges.add(new Edge(3, 4, 2));
        allEdges.add(new Edge(3, 5, 5));
        allEdges.add(new Edge(3, 6, 3));
        allEdges.add(new Edge(4, 6, 4));
        allEdges.add(new Edge(5, 6, 4));

        return allEdges;
    }

    private static List<Edge> findMinimumSpanningTree(List<Edge> allEdges) {
        // Sort the edges by weight because we need the lightes edge as a next step for the algorithm
        Collections.sort(allEdges);

        // Init the vertices as a list of subTrees. Each subTree contains only one vertex.
        // These subTrees will be linked by proper edges later on until all the vertices are joined to a single tree.
        List<Set<Integer>> subTrees = initSets(allEdges);

        List<Edge> result = new LinkedList<>();
        for (Edge edge : allEdges) {
            // Find the subTree which contains the from vertex
            Set<Integer> includeFrom = findSet(edge.getFrom(), subTrees);
            // Find the tree which contains the to vertex
            Set<Integer> includeTo = findSet(edge.getTo(), subTrees);
            if (includeFrom != includeTo) {
                // The edge does not close a cycle and should be part of the solution
                result.add(edge);
                // join both subTrees
                includeFrom.addAll(includeTo);
                subTrees.remove(includeTo);
            }
        }
        return result;
    }

    /**
     * Return a list of subTrees each of which contains only one vertex of the graph
     *
     * @param allEdges all graph edges
     * @return list of subTrees
     */
    private static List<Set<Integer>> initSets(List<Edge> allEdges) {
        return allEdges.stream()
                // extract vertices from edges
                .flatMap(edge -> Stream.of(new Integer[]{edge.getFrom(), edge.getTo()}))
                // remove duplicates
                .distinct()
                // Map to sets with a single element
                .map(vertex -> new LinkedHashSet<>(Collections.singletonList(vertex)))
                .collect(Collectors.toList());
    }

    /**
     * Find the subTree which contains a given vertex
     *
     * @param vertex   look for a subTree which contains this vertex
     * @param subTrees list of subTrees to look in
     * @return the subTree which contains the given vertex
     */
    private static Set<Integer> findSet(int vertex, List<Set<Integer>> subTrees) {
        return subTrees.stream()
                .filter(subTree -> subTree.contains(vertex))
                .findFirst()
                .orElse(Collections.emptySet());
    }

}
