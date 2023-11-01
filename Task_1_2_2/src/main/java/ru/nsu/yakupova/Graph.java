package ru.nsu.yakupova;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Abstract class Graph.
 */
public interface Graph<T> {
    /**
     * Method for changing vertices.
     */
    void changeVertex(T value, T newValue);

    /**
     * Method for adding vertices.
     */
    Vertex<T> addVertex(T vertexValue);

    /**
     * Method for removing vertices.
     */
    void removeVertex(T vertex);

    /**
     * Method for adding edges.
     */
    void addEdge(T from, T to, int weight);

    /**
     * Method for removing edges.
     */
    void removeEdge(T from, T to);

    /**
     * Getter for orientation flag.
     */
    boolean getOriented();

    /**
     * Getter for single vertex.
     */
    Vertex<T> getVertex(T vertexValue);

    /**
     * Getter for vertices.
     */
    List<Vertex<T>> getVertices();

    /**
     * Getter for edges.
     */
    List<Edge<T>> getEdges(T from);

    /**
     * Getter for all edges.
     */
    List<Edge<T>> getAllEdges();

    /**
     * Method for sorting by distance.
     */
    default List<Map.Entry<Vertex<T>, Integer>> sortVerticesByDistance(T startVertex){
        var start = getVertex(startVertex);
        Algorithms<T> algo = new Algorithms<>(this);
        if (this.getOriented()){
            return algo.bellmanFord(start);
        }
        else {
            return algo.dijkstra(start);
        }
    }
}