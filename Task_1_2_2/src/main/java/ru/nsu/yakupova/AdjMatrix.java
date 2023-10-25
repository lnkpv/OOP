package ru.nsu.yakupova;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class for Adjacency Matrix.
 */
public class AdjMatrix<T> extends Graph<T> {
    Map<T, Map<T, Edge<T>>> matrix;

    public AdjMatrix() {
        this.matrix = new HashMap<>();
    }

    /**
     * Method for adding vertices for Adjacency Matrix.
     */
    @Override
    public void addVertex(T vertex) {
        matrix.putIfAbsent(vertex, new HashMap<>());
        matrix.putIfAbsent(vertex, new HashMap<>());
    }

    /**
     * Method for removing vertices for Adjacency Matrix.
     */
    @Override
    public void removeVertex(T vertex) {
        var values = matrix.get(vertex);
        for (Edge<T> edge : values.values()) {
            matrix.get(edge.getTo()).values().removeIf(elem -> elem.getTo() == vertex);
        }
        matrix.remove(vertex);
    }

    /**
     * Method for adding edges for Adjacency Matrix.
     */
    @Override
    public void addEdge(T from, T to, int weight) {
        matrix.get(from).put(to, new Edge<T>(from, to, weight));
        matrix.get(to).put(from, new Edge<T>(to, from, weight));
    }

    /**
     * Method for removing edges for Adjacency Matrix.
     */
    @Override
    public void removeEdge(T from, T to) {
        matrix.get(from).values().removeIf(edge -> edge.getTo() == to);
        matrix.get(to).values().removeIf(edge -> edge.getFrom() == from);
    }

    /**
     * Getter for vertices for Adjacency Matrix.
     */
    @Override
    public List<T> getVertices() {
        return new ArrayList<>(matrix.keySet());
    }

    /**
     * Getter for edges for Adjacency Matrix.
     */
    @Override
    public List<Edge<T>> getEdges(T from) {
        return new ArrayList<>(matrix.get(from).values());
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
        List<T> vertices = new ArrayList<>(matrix.keySet());
        for (var vert : vertices) {
            var edges = new ArrayList<>(matrix.get(vert).values());
            for (var edge : edges) {
                table[vertices.indexOf(edge.getFrom())][vertices.indexOf(edge.getTo())] = edge.getWeight();
            }
        }

        var builder = new StringBuilder();
        builder.append("    ");
        for (var vertex : vertices) {
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


        for (var vertex : vertices) {
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
