package ru.nsu.yakupova;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Class for Adjacency List.
 */
public class AdjList<T> implements Graph<T> {

    private final Map<Vertex<T>, List<Edge<T>>> adjacencyList;
    private final Map<T, Vertex<T>> vertices;

    /**
     * Construct Adjacency List.
     */
    public AdjList() {
        this.adjacencyList = new HashMap<>();
        this.vertices = new HashMap<>();
    }

    /**
     * Method for adding vertices for Adjacency List.
     */
    @Override
    public Vertex<T> addVertex(T vertexValue) {
        Vertex<T> vertex;
        if (vertices.containsKey(vertexValue)) {
            vertex = vertices.get(vertexValue);
            return vertex;
        }
        vertex = new Vertex<>(vertexValue);
        vertices.putIfAbsent(vertexValue, vertex);
        adjacencyList.putIfAbsent(vertex, new ArrayList<>());
        return vertex;
    }

    /**
     * Method for removing vertices for Adjacency List.
     */
    @Override
    public void removeVertex(T vertexValue) {
        if (!vertices.containsKey(vertexValue)) {
            return;
        }
        var vertex = vertices.get(vertexValue);
        vertices.remove(vertexValue);
        for (Edge<T> edge : adjacencyList.get(vertex)) {
            adjacencyList.get(edge.getTo())
                    .removeIf(elem -> elem.getTo() == vertex);
        }
        adjacencyList.remove(vertex);
    }

    /**
     * Method for adding edges for Adjacency List.
     */
    @Override
    public void addEdge(T from, T to, int weight) {
        var fromVert = addVertex(from);
        var toVert = addVertex(to);
        var edge1 = new Edge<>(fromVert, toVert, weight);
        var edge2 = new Edge<>(toVert, fromVert, weight);
        if (!adjacencyList.get(fromVert).contains(edge1)) {
            adjacencyList.get(fromVert).add(edge1);
        }
        if (!adjacencyList.get(toVert).contains(edge2)) {
            adjacencyList.get(toVert).add(edge2);
        }
    }

    /**
     * Method for removing edges for Adjacency List.
     */
    @Override
    public void removeEdge(T from, T to) {
        var vertexFrom = getVertex(from);
        var vertexTo = getVertex(to);
        List<Edge<T>> edges = adjacencyList.get(vertexFrom);
        if (edges != null) {
            edges.removeIf(edge -> edge.getTo() == vertexTo);
        }
        edges = adjacencyList.get(vertexTo);
        if (edges != null) {
            edges.removeIf(edge -> edge.getFrom() == vertexFrom);
        }
    }

    /**
     * Getter for single vertex for Adjacency List.
     */
    @Override
    public Vertex<T> getVertex(T vertexValue) {
        return vertices.get(vertexValue);
    }

    /**
     * Getter for vertices for Adjacency List.
     */
    @Override
    public List<Vertex<T>> getVertices() {
        return new ArrayList<>(vertices.values());
    }

    /**
     * Getter for edges for Adjacency List.
     */
    @Override
    public List<Edge<T>> getEdges(T from) {
        var fromVert = getVertex(from);
        return adjacencyList.get(fromVert);
    }

    /**
     * Sorting vertices by distance for Adjacency List.
     */
    @Override
    public List<Map.Entry<Vertex<T>, Integer>> sortVerticesByDistance(T startVertex) {
        var start = getVertex(startVertex);
        Algorithms<T> algo = new Algorithms<>(this);
        return algo.dijkstra(start);
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
