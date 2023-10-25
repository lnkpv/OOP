package ru.nsu.yakupova;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Abstract class Graph.
 */
public abstract class Graph<T> {

    abstract void addVertex(T vertex);

    abstract void removeVertex(T vertex);

    abstract void addEdge(T from, T to, int weight);

    abstract void removeEdge(T from, T to);

    abstract List<T> getVertices();

    abstract List<Edge<T>> getEdges(T from);

    /**
     * Method for sorting by distance.
     */
    public List<Map.Entry<T, Integer>> sortVerticesByDistance(T startVertex) {
        Map<T, Integer> distance = new HashMap<>();
        PriorityQueue<Edge<T>> pq = new PriorityQueue<>(Comparator.comparingInt(Edge::getWeight));
        List<T> vertices = getVertices();

        for (T vertex : vertices) {
            if (vertex.equals(startVertex)) {
                distance.put(vertex, 0);
                pq.add(new Edge<T>(startVertex, startVertex, 0));
            } else {
                distance.put(vertex, Integer.MAX_VALUE);
            }
        }

        // Алгоритм Дейкстры
        while (!pq.isEmpty()) {
            Edge<T> currentEdge = pq.poll();
            T currentVertex = currentEdge.getTo();
            var edges = getEdges(currentVertex);
            if (edges != null) {
                for (Edge<T> edge : edges) {
                    int newDist = distance.get(currentVertex) + edge.getWeight();
                    int neighborDist = distance.get(edge.getTo());

                    if (newDist < neighborDist) {
                        pq.add(new Edge<T>(currentVertex, edge.getTo(), newDist));
                        distance.put(edge.getTo(), newDist);
                    }
                }
            }
        }

        List<Map.Entry<T, Integer>> sortedDistances = new ArrayList<>(distance.entrySet());
        sortedDistances.sort(Map.Entry.comparingByValue());
        return sortedDistances;
    }
}