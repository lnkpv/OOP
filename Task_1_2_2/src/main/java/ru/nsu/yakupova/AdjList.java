package ru.nsu.yakupova;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Class for Adjacency List.
 */
public class AdjList<T> implements Graph<T> {

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
            adjacencyList.get(edge.getTo().getId()).removeIf(elem -> elem.getTo().getId() == vertex);
        }
        adjacencyList.remove(vertex);
    }

    /**
     * Method for adding edges for Adjacency List.
     */
    @Override
    public void addEdge(T from, T to, int weight) {
        var fromVert = new Vertex<>(from);
        var toVert = new Vertex<>(to);
        addVertex(from);
        addVertex(to);
        adjacencyList.get(from).add(new Edge<>(fromVert, toVert, weight));
        adjacencyList.get(to).add(new Edge<>(toVert, fromVert, weight));
    }

    /**
     * Method for removing edges for Adjacency List.
     */
    @Override
    public void removeEdge(T from, T to) {
        List<Edge<T>> edges = adjacencyList.get(from);
        if (edges != null) {
            edges.removeIf(edge -> edge.getTo().getId() == to);
        }
        edges = adjacencyList.get(to);
        if (edges != null) {
            edges.removeIf(edge -> edge.getFrom().getId() == from);
        }
    }

    /**
     * Method for setting weight for Adjacency List.
     */
    @Override
    public void setWeight(T from, T to, int weight) {
        removeEdge(from, to);
        addEdge(from, to, weight);
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
     * Sorting vertices by distance for Adjacency List.
     */
    @Override
    public List<Map.Entry<T, Integer>> sortVerticesByDistance(T startVertex) {
        Algorithms<T> algo = new Algorithms<>(this);
        return algo.dijkstra(startVertex);
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
