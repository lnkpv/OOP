package ru.nsu.yakupova;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Class for Incident Matrix.
 */
public class IncMatrix<T> implements Graph<T> {
    private final Map<Vertex<T>, Map<Edge<T>, Integer>> incMatrix;
    private final Map<T, Vertex<T>> vertices;
    private final List<Edge<T>> edges;
    private int modifications;
    private boolean oriented = false;

    /**
     * Construct Incident Matrix.
     */
    public IncMatrix(boolean orient) {
        this.incMatrix = new HashMap<>();
        this.vertices = new HashMap<>();
        this.edges = new ArrayList<>();
        this.modifications = 0;
        this.oriented = orient;
    }

    /**
     * Method for changing vertices for Incident Matrix.
     */
    @Override
    public void changeVertex(T value, T newValue) {
        var vert = getVertex(value);
        vert.setValue(newValue);
        vertices.put(newValue, vert);
        vertices.remove(value);
    }

    /**
     * Method for adding vertices for Incident Matrix.
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
        incMatrix.putIfAbsent(vertex, new HashMap<>());
        return vertex;
    }

    /**
     * Method for removing vertices for Incident Matrix.
     */
    @Override
    public void removeVertex(T vertexValue) {
        if (!vertices.containsKey(vertexValue)) {
            return;
        }
        var vertex = vertices.get(vertexValue);
        vertices.remove(vertexValue);
        var values = incMatrix.get(vertex);
        for (var edge : values.keySet()) {
            incMatrix.get(edge.getTo()).keySet()
                    .removeIf(elem -> elem.getTo() == vertex);
        }
        incMatrix.remove(vertex);
    }

    /**
     * Method for adding edges for Incident Matrix.
     */
    @Override
    public void addEdge(T from, T to, int weight) {
        var fromVert = addVertex(from);
        var toVert = addVertex(to);
        var edge1 = new Edge<>(fromVert, toVert, weight);
        var edge2 = new Edge<>(toVert, fromVert, weight);
        if (!incMatrix.get(fromVert).containsKey(edge1)) {
            incMatrix.get(fromVert).put(edge1, weight);
        }
        if (!edges.contains(edge1)) {
            edges.add(edge1);
        }
        if (!oriented && !incMatrix.get(toVert).containsKey(edge2)) {
            incMatrix.get(toVert).put(edge2, weight);
        }
    }

    /**
     * Method for removing edges for Incident Matrix.
     */
    @Override
    public void removeEdge(T from, T to) {
        var vertexFrom = getVertex(from);
        var vertexTo = getVertex(to);
        Set<Edge<T>> edges = incMatrix.get(vertexFrom).keySet();
        if (edges != null) {
            edges.removeIf(edge -> edge.getTo() == vertexTo);
        }
        if (!oriented) {
            edges = incMatrix.get(vertexTo).keySet();
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
     * Getter for single vertex for Incident Matrix.
     */
    public Vertex<T> getVertex(T vertexValue) {
        return vertices.get(vertexValue);
    }

    /**
     * Getter for vertices for Incident Matrix.
     */
    @Override
    public List<Vertex<T>> getVertices() {
        return new ArrayList<>(vertices.values());
    }

    /**
     * Getter for edges for Incident Matrix.
     */
    @Override
    public List<Edge<T>> getEdges(T from) {
        var fromVert = getVertex(from);
        return new ArrayList<>(incMatrix.get(fromVert).keySet());
    }

    /**
     * Getter for all edges for Adjacency List.
     */
    public List<Edge<T>> getAllEdges() {
        return edges;
    }

    /**
     * ToString for Incident Matrix.
     */
    @Override
    public String toString() {
        int n = incMatrix.keySet().size();
        int m = edges.size();
        int[][] table = new int[n][m];
        for (int[] row : table) {
            Arrays.fill(row, 0);
        }
        List<T> vert = new ArrayList<>(vertices.keySet());
        int x = 0;
        for (var edge : edges) {
            table[vert.indexOf(edge.getFrom().getVertValue())][x] = edge.getWeight();
            table[vert.indexOf(edge.getTo().getVertValue())][x] = edge.getWeight();
            x++;
        }

        var builder = new StringBuilder();
        builder.append("    ");
        for (var edge : edges) {
            builder.append("|");
            String e = edge.getFrom().toString() + "-" + edge.getTo().toString();
            builder.append(String.format("%4s", e));
        }
        builder.append("\n");
        char[] chars = new char[5 * (m + 1)];
        Arrays.fill(chars, '-');
        String res = new String(chars);
        builder.append(res);
        builder.append("\n");
        int i = 0;


        for (var vertex : vert) {
            builder.append(String.format("%4s", vertex.toString()));
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

