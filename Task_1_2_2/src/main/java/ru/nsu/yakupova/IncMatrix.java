package ru.nsu.yakupova;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class for Incident Matrix.
 */
public class IncMatrix<T> extends Graph<T> {
    Map<T, Map<Edge<T>, Integer>> incMatrix;
    List<Edge<T>> genEdges;

    public IncMatrix() {
        incMatrix = new HashMap<>();
        genEdges = new ArrayList<>();
    }

    /**
     * Method for adding vertices for Incident Matrix.
     */
    @Override
    public void addVertex(T vertex) {
        incMatrix.putIfAbsent(vertex, new HashMap<>());
    }

    /**
     * Method for removing vertices for Incident Matrix.
     */
    @Override
    public void removeVertex(T vertex) {
        var values = incMatrix.get(vertex);
        for (var edge : values.keySet()) {
            incMatrix.get(edge.getTo()).keySet().removeIf(elem -> elem.getTo() == vertex);
        }
        incMatrix.remove(vertex);
    }

    /**
     * Method for adding edges for Incident Matrix.
     */
    @Override
    public void addEdge(T from, T to, int weight) {
        addVertex(from);
        addVertex(to);
        incMatrix.get(from).put(new Edge<T>(from, to, weight), weight);
        incMatrix.get(to).put(new Edge<T>(to, from, weight), weight);
        genEdges.add(new Edge<T>(from, to, weight));
    }

    /**
     * Method for removing edges for Incident Matrix.
     */
    @Override
    public void removeEdge(T from, T to) {
        incMatrix.get(from).keySet().removeIf(edge -> edge.getTo() == to);
        incMatrix.get(to).keySet().removeIf(edge -> edge.getFrom() == from);
    }

    /**
     * Getter for vertices for Incident Matrix.
     */
    @Override
    public List<T> getVertices() {
        return new ArrayList<>(incMatrix.keySet());
    }

    /**
     * Getter for edges for Incident Matrix.
     */
    @Override
    public List<Edge<T>> getEdges(T from) {
        return new ArrayList<>(incMatrix.get(from).keySet());
    }

    /**
     * ToString for Incident Matrix.
     */
    @Override
    public String toString() {
        int n = incMatrix.keySet().size();
        int m = genEdges.size();
        int[][] table = new int[n][m];
        for (int[] row : table) {
            Arrays.fill(row, 0);
        }
        List<T> vertices = new ArrayList<>(incMatrix.keySet());
        int x = 0;
        for (var edge : genEdges) {
            table[vertices.indexOf(edge.getFrom())][x] = edge.getWeight();
            table[vertices.indexOf(edge.getTo())][x] = edge.getWeight();
            x++;
        }

        var builder = new StringBuilder();
        builder.append("    ");
        for (var edge : genEdges) {
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

