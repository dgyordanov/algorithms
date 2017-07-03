package org.dido.algorithms.greedy;

public class Edge implements Comparable<Edge> {

    private int from;
    private int to;
    private int weight;

    Edge(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    int getFrom() {
        return from;
    }

    int getTo() {
        return to;
    }

    int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return String.format("<From:%s To:%s Weight:%s>", from, to, weight);
    }

    @Override
    public int compareTo(Edge e) {
        return weight - e.getWeight();
    }

}
