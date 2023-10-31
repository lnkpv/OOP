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

    public AdjMatrix() {
        this.matrix = new HashMap<>();
        this.vertices = new HashMap<>();
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
        vertex = new Vertex<>(vertexValue);
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
        if (!matrix.get(toVert).containsKey(fromVert)) {
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
        edges = matrix.get(vertexTo).values();
        if (edges != null) {
            edges.removeIf(edge -> edge.getFrom() == vertexFrom);
        }
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
     * Sorting vertices by distance for Adjacency Matrix.
     */
    @Override
    public List<Map.Entry<Vertex<T>, Integer>> sortVerticesByDistance(T startVertex) {
        var start = getVertex(startVertex);
        Algorithms<T> algo = new Algorithms<>(this);
        return algo.dijkstra(start);
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
        List<Vertex<T>> vertices = new ArrayList<>(matrix.keySet());
        for (var vert : vertices) {
            var edges = new ArrayList<>(matrix.get(vert).values());
            for (var edge : edges) {
                table[vertices.indexOf(edge.getFrom())]
                        [vertices.indexOf(edge.getTo())] = edge.getWeight();
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
