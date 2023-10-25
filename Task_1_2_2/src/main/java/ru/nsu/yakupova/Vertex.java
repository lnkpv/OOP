package ru.nsu.yakupova;

import java.util.Objects;

/**
 * Class for Vertex.
 */
public class Vertex<T> {
    private final T id;

    public Vertex(T id) {
        this.id = id;
    }

    /**
     * Equals for vertices.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Vertex<?>)) {
            return false;
        }
        Vertex<T> o = (Vertex<T>) obj;
        return id.equals(o.id);
    }

    /**
     * HashCode for vertices.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * ToString for vertices.
     */
    @Override
    public String toString() {
        return String.format("%s", id.toString());
    }
}
