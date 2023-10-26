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
    public List<Map.Entry<T, Integer>> dijkstra(T startVertex) {
        Map<T, Integer> distance = new HashMap<>();
        PriorityQueue<Edge<T>> pq = new PriorityQueue<>(Comparator.comparingInt(Edge::getWeight));
        List<T> vertices = graph.getVertices();

        for (T vertex : vertices) {
            if (vertex.equals(startVertex)) {
                distance.put(vertex, 0);
                var tmp = new Vertex<>(startVertex);
                pq.add(new Edge<T>(tmp, tmp, 0));
            } else {
                distance.put(vertex, Integer.MAX_VALUE);
            }
        }

        while (!pq.isEmpty()) {
            Edge<T> currentEdge = pq.poll();
            Vertex<T> currentVertex = currentEdge.getTo();
            var curVertId = currentVertex.getId();
            var edges = graph.getEdges(curVertId);
            if (edges != null) {
                for (Edge<T> edge : edges) {
                    int newDist = distance.get(curVertId) + edge.getWeight();
                    int neighborDist = distance.get(edge.getTo().getId());

                    if (newDist < neighborDist) {
                        pq.add(new Edge<>(currentVertex, edge.getTo(), newDist));
                        distance.put(edge.getTo().getId(), newDist);
                    }
                }
            }
        }

        List<Map.Entry<T, Integer>> sortedDistances = new ArrayList<>(distance.entrySet());
        sortedDistances.sort(Map.Entry.comparingByValue());
        return sortedDistances;
    }
}
