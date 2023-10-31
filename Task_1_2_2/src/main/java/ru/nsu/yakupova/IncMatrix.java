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
    private final List<Edge<T>> genEdges;

    public IncMatrix() {
        incMatrix = new HashMap<>();
        vertices = new HashMap<>();
        genEdges = new ArrayList<>();
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
        vertex = new Vertex<>(vertexValue);
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
        if (!incMatrix.get(toVert).containsKey(edge2)) {
            incMatrix.get(toVert).put(edge2, weight);
        }
        genEdges.add(edge1);
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
        edges = incMatrix.get(vertexTo).keySet();
        if (edges != null) {
            edges.removeIf(edge -> edge.getFrom() == vertexFrom);
        }
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
     * Sorting vertices by distance for Incident Matrix.
     */
    @Override
    public List<Map.Entry<Vertex<T>, Integer>> sortVerticesByDistance(T startVertex) {
        var start = getVertex(startVertex);
        Algorithms<T> algo = new Algorithms<>(this);
        return algo.dijkstra(start);
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
        List<Vertex<T>> vertices = new ArrayList<>(incMatrix.keySet());
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

