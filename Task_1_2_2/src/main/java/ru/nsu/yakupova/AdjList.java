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
    private final List<Edge<T>> edges;
    private int modifications;
    private final boolean oriented;

    /**
     * Construct Adjacency List.
     */
    public AdjList(boolean orient) {
        this.adjacencyList = new HashMap<>();
        this.vertices = new HashMap<>();
        this.modifications = 0;
        this.edges = new ArrayList<>();
        this.oriented = orient;
    }

    /**
     * Method for changing vertices for Adjacency List.
     */
    @Override
    public void changeVertex(T value, T newValue) {
        var vert = getVertex(value);
        vert.setValue(newValue);
        vertices.put(newValue, vert);
        vertices.remove(value);
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
        vertex = new Vertex<>(vertexValue, modifications);
        modifications += 1;
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
        if (!edges.contains(edge1)) {
            edges.add(edge1);
        }
        if (!oriented && !adjacencyList.get(toVert).contains(edge2)) {
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
        List<Edge<T>> edges = getEdges(from);
        if (edges != null) {
            edges.removeIf(edge -> edge.getTo() == vertexTo);
        }
        if (!oriented) {
            edges = getEdges(to);
            if (edges != null) {
                edges.removeIf(edge -> edge.getTo() == vertexFrom);
            }
        }
    }

    /**
     * Getter for orientation flag.
     */
    public boolean getOriented() {
        return this.oriented;
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
     * Getter for all edges for Adjacency List.
     */
    public List<Edge<T>> getAllEdges(){
        return edges;
    }

    /**
     * ToString for Adjacency List.
     */
    @Override
    public String toString() {
        var builder = new StringBuilder();
        List<Vertex<T>> vert = new ArrayList<>(vertices.values());
        for (var vertex : vert) {
            builder.append(vertex.toString());
            builder.append(": ");
            builder.append(adjacencyList.get(vertex).toString());
            builder.append("\n");
        }
        return builder.toString();
    }
}
