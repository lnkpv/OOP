package ru.nsu.yakupova;

import java.util.List;
import java.util.Map;

/**
 * Abstract class Graph.
 */
public interface Graph<T> {

    /**
     * Method for changing vertices.
     */
    abstract void changeVertex(T value, T newValue);

    /**
     * Method for adding vertices.
     */
    abstract Vertex<T> addVertex(T vertex);

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
     * Getter for single vertex.
     */
    public Vertex<T> getVertex(T vertexValue);

    /**
     * Getter for vertices.
     */
    public List<Vertex<T>> getVertices();

    /**
     * Getter for edges.
     */
    public List<Edge<T>> getEdges(T from);

    /**
     * Method for sorting by distance.
     */
    public List<Map.Entry<Vertex<T>, Integer>> sortVerticesByDistance(T startVertex);
}