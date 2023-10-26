package ru.nsu.yakupova;

import java.util.Objects;

/**
 * Class for Vertex.
 */
public class Vertex<T> {
    private final T value;
    private int id;

    public Vertex(T value) {
        this.value = value;
        this.id = 0;
    }

    /**
     * Getter for vertex value.
     */
    public T getValue() {
        return this.value;
    }

    /**
     * Getter for vertex id.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Setter for vertex id.
     */
    public void setId(int id) {
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
        return value.equals(o.value);
    }

    /**
     * HashCode for vertices.
     */
    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    /**
     * ToString for vertices.
     */
    @Override
    public String toString() {
        return String.format("%s", value.toString());
    }
}