package ru.nsu.yakupova;

import java.util.List;
import java.util.Map;

/**
 * Abstract class Graph.
 */
public interface Graph<T> {

    /**
     * Method for adding verices.
     */
    abstract void addVertex(T vertex);

    /**
     * Method for removing vertices.
     */
    abstract void removeVertex(T vertex);

    /**
     * Method for adding edges.
     */
    abstract void addEdge(T from, T to, int weight);

    /**
     * Method for removing edges.
     */
    abstract void removeEdge(T from, T to);

    /**
     * Setter for weight.
     */
    abstract void setWeight(T from, T to, int weight);

    /**
     * Getter for vertices.
     */
    public List<T> getVertices();

    /**
     * Getter for edges.
     */
    public List<Edge<T>> getEdges(T from);

    /**
     * Method for sorting by distance.
     */
    public List<Map.Entry<T, Integer>> sortVerticesByDistance(T startVertex);
}