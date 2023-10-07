package ru.nsu.yakupova;

import java.util.*;


/**
 * Class for Tree (Task_1_2_1).
 */
public class Tree<T> implements Iterable<Tree<T>> {
    public final T value;
    private Tree<T> parent = null;
    private final ArrayList<Tree<T>> child = new ArrayList<>();

    public Tree(T node) {
        this.value = node;
    }

    /**
     * Adding child.
     */
    public Tree<T> addNode(T newNode) {
        Tree<T> child = new Tree<>(newNode);
        return this.addNode(child);
    }

    /**
     * Adding subtree.
     */
    public Tree<T> addNode(Tree<T> newNode) {
        newNode.parent = this;
        this.child.add(newNode);
        return newNode;
    }

    /**
     * Remove for Tree.
     */
    public void remove() {
        this.child.clear();
        if (this.parent != null) {
            this.parent.child.remove(this);
        }
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

    /**
     * New iterator.
     */
    @Override
    public Iterator<Tree<T>> iterator() {
        return new TreeIterator<>(this);
    }

    public static class TreeIterator<E> implements Iterator<Tree<E>> {

        private final ArrayList<Tree<E>> queue;
        private Tree<E> curNode;

        public TreeIterator(Tree<E> node) {
            queue = new ArrayList<>();
            queue.add(node);
            curNode = node;
        }

        /**
         * New hasNext for iterator.
         */
        @Override
        public boolean hasNext() {
            return !queue.isEmpty();
        }

        /**
         * New next for iterator.
         */
        @Override
        public Tree<E> next() {
            Tree<E> current = queue.get(0);
            queue.remove(0);
            if (current == null) {
                throw new NoSuchElementException();
            }
            if (!current.child.isEmpty()) {
                queue.addAll(0, current.child);
            }
            curNode = current;
            return current;
        }

        /**
         * New remove for iterator.
         */
        @Override
        public void remove() {
            Tree<E> current = curNode;
            int curCount = current.child.size();

            if (curCount > 0) {
                queue.subList(0, curCount).clear();
            }
            current.remove();
        }
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
