package ru.nsu.yakupova;

import java.util.Objects;

/**
 * Class for Vertex.
 */
public class Vertex<T> {
    private final int id;
    private T value;

    public Vertex(T value, int id) {
        this.value = value;
        this.id = id;
    }

    /**
     * Setter for vertex value.
     */
    public void setValue(T value) {
        this.value = value;
    }

    /**
     * Getter for vertex value.
     */
    public T getVertValue() {
        return this.value;
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
        return value.equals(o.value);
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
        return String.format("%s", value.toString());
    }
}