package ru.nsu.yakupova;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Objects;

/**
 * Class for Tree (Task_1_2_1).
 */
public class Tree<T> {
    public final T value;
    private Tree<T> parent = null;
    private final ArrayList<Tree<T>> child = new ArrayList<>();
    private boolean blocked = false;


    public Tree(T node) {
        this.value = node;
    }

    /**
     * Check for block
     */
    private void checkBlocked() throws ConcurrentModificationException {
        var tmp = this;
        while (tmp.parent != null) {
            if (tmp.blocked) {
                throw new ConcurrentModificationException("Can't modify part of iterable tree");
            }
            tmp = tmp.parent;
        }
        if (tmp.blocked) {
            throw new ConcurrentModificationException("Can't modify part of iterable tree");
        }
    }

    /**
     * Block changing.
     */
    public void changeBlocked() {
        blocked = !(blocked);
    }

    /**
     * Adding child.
     */
    public Tree<T> addNode(T newNode) {
        checkBlocked();
        Tree<T> child = new Tree<>(newNode);
        return this.addNode(child);
    }

    /**
     * Adding subtree.
     */
    public Tree<T> addNode(Tree<T> newNode) {
        checkBlocked();
        newNode.parent = this;
        this.child.add(newNode);
        return newNode;
    }

    /**
     * Remove for Tree.
     */
    public void remove() {
        checkBlocked();
        this.child.clear();
        if (this.parent != null) {
            this.parent.child.remove(this);
        }
    }

    /**
     * Method for children.
     */
    public ArrayList<Tree<T>> getChild() {
        return this.child;
    }

    /**
     * Method for value.
     */
    public T getValue() {
        return this.value;
    }

    /**
     * Equals for Tree.
     */
    @Override
    public boolean equals(Object o) {
        Tree<?> tree = null;
        if (!(o instanceof Tree<?>)) {
            return false;
        } else {
            tree = (Tree<?>) o;
        }
        if (!value.equals(tree.value)) {
            return false;
        }
        return child.equals(tree.child);
    }

    /**
     * New hashCode().
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.value);
    }

    /**
     * Method toString() for Tree.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        if (this.child.isEmpty()) {
            if (value == null) {
                result.append("{}");
            } else {
                if (parent == null) {
                    result.append(String.format("{%s}", value.toString()));
                } else {
                    result.append(String.format("%s", value.toString()));
                }
            }
        } else {
            result.append(String.format("{%s -> (", value.toString()));
            StringBuilder tmp = new StringBuilder();
            for (var curChild : this.child) {
                tmp.append(String.format(", %s", curChild.toString()));
            }
            tmp.delete(0, 2);
            result.append(tmp);
            result.append(")}");
        }
        return result.toString();
    }

}
