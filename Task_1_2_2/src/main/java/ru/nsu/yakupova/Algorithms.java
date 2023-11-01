package ru.nsu.yakupova;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Class for algorithms.
 */
class Algorithms<T> {
    private final Graph<T> graph;

    public Algorithms(Graph<T> graph) {
        this.graph = graph;
    }

    /**
     * Algorithm dijkstra.
     */
    public List<Map.Entry<Vertex<T>, Integer>> dijkstra(Vertex<T> startVertex) {
        Map<Vertex<T>, Integer> distance = new HashMap<>();
        PriorityQueue<Edge<T>> pq = new PriorityQueue<>(Comparator.comparingInt(Edge::getWeight));
        List<Vertex<T>> vertices = graph.getVertices();

        for (Vertex<T> vertex : vertices) {
            if (vertex.equals(startVertex)) {
                distance.put(vertex, 0);
                pq.add(new Edge<>(startVertex, startVertex, 0));
            } else {
                distance.put(vertex, Integer.MAX_VALUE);
            }
        }

        while (!pq.isEmpty()) {
            Edge<T> currentEdge = pq.poll();
            Vertex<T> currentVertex = currentEdge.getTo();
            var curVertValue = currentVertex.getVertValue();
            var edges = graph.getEdges(curVertValue);
            if (edges != null) {
                for (Edge<T> edge : edges) {
                    int newDist = distance.get(currentVertex) + edge.getWeight();
                    int neighborDist = distance.get(edge.getTo());

                    if (newDist < neighborDist) {
                        pq.add(new Edge<>(currentVertex, edge.getTo(), newDist));
                        distance.put(edge.getTo(), newDist);
                    }
                }
            }
        }

        List<Map.Entry<Vertex<T>, Integer>> sortedDistances = new ArrayList<>(distance.entrySet());
        sortedDistances.sort(Map.Entry.comparingByValue());
        return sortedDistances;
    }

    /**
     * Bellman-Ford's algorithm.
     */
    public List<Map.Entry<Vertex<T>, Integer>> bellmanFord(Vertex<T> startVertex) {
        var vertices = graph.getVertices();
        Map<Vertex<T>, Integer> distance = new HashMap<>();
        for (Vertex<T> vertex : vertices) {
            if (vertex.equals(startVertex)) {
                distance.put(vertex, 0);
            } else {
                distance.put(vertex, Integer.MAX_VALUE);
            }
        }
        int num = vertices.size();
        var edges = graph.getAllEdges();
        for (int i = 1; i < num; ++i) {
            for (var edge : edges) {
                var src = edge.getFrom();
                var dest = edge.getTo();
                int weight = edge.getWeight();

                if (distance.get(src) != Integer.MAX_VALUE
                        && distance.get(src) + weight < distance.get(dest)) {
                    distance.put(dest, distance.get(src) + weight);
                }
            }
        }

        // Проверка на наличие отрицательных циклов
        for (var edge : edges) {
            var src = edge.getFrom();
            var dest = edge.getTo();
            int weight = edge.getWeight();

            if (distance.get(src) != Integer.MAX_VALUE
                    && distance.get(src) + weight < distance.get(dest)) {
                System.out.println("Graph contains negative weight cycle");
                return null;
            }
        }

        List<Map.Entry<Vertex<T>, Integer>> sortedDistances = new ArrayList<>(distance.entrySet());
        sortedDistances.sort(Map.Entry.comparingByValue());
        return sortedDistances;
    }
}
