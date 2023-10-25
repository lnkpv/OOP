package ru.nsu.yakupova;

import java.util.Objects;

/**
 * Class for Edge.
 */
public class Edge<T> {
    private final T from;
    private final T to;
    private final int weight;

    public Edge(T from, T to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    /**
     * Getter for "from vertex".
     */
    public T getFrom() {
        return from;
    }

    /**
     * Getter for "to vertex".
     */
    public T getTo() {
        return to;
    }

    /**
     * Getter for weight.
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Equals for edges.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Edge<?>)) {
            return false;
        }
        Edge<T> o = (Edge<T>) obj;
        return from.equals(o.from) && to.equals(o.to) && (weight == o.weight);
    }

    /**
     * HashCode for edges.
     */
    @Override
    public int hashCode() {
        return Objects.hash(from, to);
    }

    /**
     * ToString for edges.
     */
    @Override
    public String toString() {
        return String.format("%s -(%d)-> %s", from.toString(), weight, to.toString());
    }
}
