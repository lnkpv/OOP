package ru.nsu.yakupova;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Queue;


/**
 * Class for Tree (Task_1_2_1).
 */
public class Tree<T> implements Iterable<T> {
    private T value;
    private final List<Tree<T>> children = new ArrayList<>();
    private Tree<T> parent = null;
    private int modCount = 0;

    public Tree(T value) {
        this.value = value;
    }

    /**
     * Method for adding new nodes.
     */
    public Tree<T> addNode(T value) {
        Tree<T> newChild = new Tree<>(value);
        newChild.parent = this;
        children.add(newChild);
        modCount++;
        return newChild;
    }

    /**
     * Method for adding new subtrees.
     */
    public void addNode(Tree<T> subtree) {
        subtree.parent = this;
        children.add(subtree);
        modCount++;
    }

    /**
     * Method for removing nodes.
     */
    public void remove() {
        if (this.parent != null) {
            this.parent.children.remove(this);
        }
        this.value = null;
        this.children.clear();
    }

    /**
     * New iterator.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private final Queue<Tree<T>> queue = new LinkedList<>(Collections.singleton(Tree.this));
            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return !queue.isEmpty();
            }

            @Override
            public T next() {
                Tree<T> current = queue.poll();
                if (current == null) {
                    throw new NoSuchElementException();
                }
                queue.addAll(current.children);
                return current.value;
            }
        };
    }

    /**
     * Equals for trees.
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
        return children.equals(tree.children);
    }

    /**
     * Hashcode for trees.
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.value);
    }

    /**
     * New toString for trees.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        if (this.children.isEmpty()) {
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
            for (var curChild : this.children) {
                tmp.append(String.format(", %s", curChild.toString()));
            }
            tmp.delete(0, 2);
            result.append(tmp);
            result.append(")}");
        }
        return result.toString();
    }

    /**
     * Main.
     */
    public static void main(String[] args) {
        Tree<String> tree = new Tree<>("R1");
        var a = tree.addNode("A");
        var b = a.addNode("B");
        b.remove();
        Tree<String> subtree = new Tree<>("R2");
        subtree.addNode("C");
        subtree.addNode("D");

        tree.addNode(subtree);
        System.out.println(tree);
    }
}
