package ru.nsu.yakupova;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Class for Adjacency List.
 */
public class AdjList<T> extends Graph<T> {
    private final Map<T, List<Edge<T>>> adjacencyList;

    public AdjList() {
        this.adjacencyList = new HashMap<>();
    }

    /**
     * Method for adding vertices for Adjacency List.
     */
    @Override
    public void addVertex(T vertex) {
        adjacencyList.putIfAbsent(vertex, new ArrayList<>());
    }

    /**
     * Method for removing vertices for Adjacency List.
     */
    @Override
    public void removeVertex(T vertex) {
        for (Edge<T> edge : adjacencyList.get(vertex)) {
            adjacencyList.get(edge.getTo()).removeIf(elem -> elem.getTo() == vertex);
        }
        adjacencyList.remove(vertex);
    }

    /**
     * Method for adding edges for Adjacency List.
     */
    @Override
    public void addEdge(T from, T to, int weight) {
        adjacencyList.get(from).add(new Edge<>(from, to, weight));
        adjacencyList.get(to).add(new Edge<>(to, from, weight));
    }

    /**
     * Method for removing edges for Adjacency List.
     */
    @Override
    public void removeEdge(T from, T to) {
        List<Edge<T>> edges = adjacencyList.get(from);
        if (edges != null) {
            edges.removeIf(edge -> edge.getTo() == to);
        }
        edges = adjacencyList.get(to);
        if (edges != null) {
            edges.removeIf(edge -> edge.getFrom() == from);
        }
    }

    /**
     * Getter for vertices for Adjacency List.
     */
    @Override
    public List<T> getVertices() {
        return new ArrayList<>(adjacencyList.keySet());
    }

    /**
     * Getter for edges for Adjacency List.
     */
    @Override
    public List<Edge<T>> getEdges(T from) {
        return adjacencyList.get(from);
    }

    /**
     * ToString for Adjacency List.
     */
    @Override
    public String toString() {
        var builder = new StringBuilder();
        for (var vertex : adjacencyList.keySet()) {
            builder.append(vertex.toString());
            builder.append(": ");
            builder.append(adjacencyList.get(vertex).toString());
            builder.append("\n");
        }
        return builder.toString();
    }
}
