package ru.nsu.yakupova;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class for Adjacency Matrix.
 */
public class AdjMatrix<T> implements Graph<T> {
    Map<Vertex<T>, Map<Vertex<T>, Edge<T>>> matrix;
    private final Map<T, Vertex<T>> vertices;
    private final List<Edge<T>> edges;
    private int modifications;
    private boolean oriented = false;

    /**
     * Construct Adjacency Matrix.
     */
    public AdjMatrix(boolean orient) {
        this.matrix = new HashMap<>();
        this.vertices = new HashMap<>();
        this.modifications = 0;
        this.oriented = orient;
        this.edges = new ArrayList<>();
    }

    /**
     * Method for changing vertices for Adjacency Matrix.
     */
    @Override
    public void changeVertex(T value, T newValue) {
        var vert = getVertex(value);
        vert.setValue(newValue);
        vertices.put(newValue, vert);
        vertices.remove(value);
    }

    /**
     * Method for adding vertices for Adjacency Matrix.
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
        matrix.putIfAbsent(vertex, new HashMap<>());
        return vertex;
    }

    /**
     * Method for removing vertices for Adjacency Matrix.
     */
    @Override
    public void removeVertex(T vertexValue) {
        if (!vertices.containsKey(vertexValue)) {
            return;
        }
        var vertex = vertices.get(vertexValue);
        vertices.remove(vertexValue);
        var values = matrix.get(vertex);
        for (var edge : values.values()) {
            matrix.get(edge.getTo()).values()
                    .removeIf(elem -> elem.getTo() == vertex);
        }
        matrix.remove(vertex);
    }

    /**
     * Method for adding edges for Adjacency Matrix.
     */
    @Override
    public void addEdge(T from, T to, int weight) {
        var fromVert = addVertex(from);
        var toVert = addVertex(to);
        var edge1 = new Edge<>(fromVert, toVert, weight);
        var edge2 = new Edge<>(toVert, fromVert, weight);

        if (!matrix.get(fromVert).containsKey(toVert)) {
            matrix.get(fromVert).put(toVert, edge1);
        }
        if (!edges.contains(edge1)) {
            edges.add(edge1);
        }
        if (!oriented && !matrix.get(toVert).containsKey(toVert)) {
            matrix.get(toVert).put(fromVert, edge2);
        }
    }

    /**
     * Method for removing edges for Adjacency Matrix.
     */
    @Override
    public void removeEdge(T from, T to) {
        var vertexFrom = getVertex(from);
        var vertexTo = getVertex(to);
        var edges = matrix.get(vertexFrom).values();
        if (edges != null) {
            edges.removeIf(edge -> edge.getTo() == vertexTo);
        }
        if (!oriented) {
            edges = matrix.get(vertexTo).values();
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
     * Getter for single vertex for Adjacency Matrix.
     */
    public Vertex<T> getVertex(T vertexValue) {
        return vertices.get(vertexValue);
    }

    /**
     * Getter for vertices for Adjacency Matrix.
     */
    @Override
    public List<Vertex<T>> getVertices() {
        return new ArrayList<>(vertices.values());
    }

    /**
     * Getter for edges for Adjacency Matrix.
     */
    @Override
    public List<Edge<T>> getEdges(T from) {
        var fromVert = getVertex(from);
        return new ArrayList<>(matrix.get(fromVert).values());
    }

    /**
     * Getter for all edges for Adjacency List.
     */
    public List<Edge<T>> getAllEdges() {
        return edges;
    }

    /**
     * ToString for Adjacency Matrix.
     */
    @Override
    public String toString() {

        int n = matrix.keySet().size();
        int[][] table = new int[n][n];
        for (int[] row : table) {
            Arrays.fill(row, 0);
        }
        List<Vertex<T>> vert = new ArrayList<>(vertices.values());
        for (var v : vert) {
            var edges = new ArrayList<>(matrix.get(v).values());
            for (var edge : edges) {
                table[vert.indexOf(edge.getFrom())]
                        [vert.indexOf(edge.getTo())] = edge.getWeight();
            }
        }

        var builder = new StringBuilder();
        builder.append("    ");
        for (var vertex : vert) {
            builder.append("|");
            builder.append(String.format("%4s", vertex.toString()));
        }
        builder.append("\n");
        char[] chars = new char[(n + 1) * 5];
        Arrays.fill(chars, '-');
        String res = new String(chars);
        builder.append(res);
        builder.append("\n");
        int i = 0;


        for (var v : vert) {
            builder.append(String.format("%4s", v.toString()));
            for (var elem : table[i]) {
                builder.append("|");
                builder.append(String.format("%4s", elem));
            }
            builder.append("\n");
            i += 1;
        }
        return builder.toString();
    }
}
