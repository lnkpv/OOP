package ru.nsu.yakupova;

import java.util.*;

/**
 *  Class for Tree (Task_1_2_1).
 */
public class Tree<T> implements Iterable<T> {
    private final T value;
    private Tree<T> parent = null;
    private final List<Tree<T>> children = new ArrayList<>();
    private int modCount = 0;

    public Tree(T value) {
        this.value = value;
    }

    public Tree<T> addNode(T value) {
        Tree<T> newChild = new Tree<>(value);
        newChild.parent = this;
        children.add(newChild);
        modCount++;
        return newChild;
    }

    public void addNode(Tree<T> subtree) {
        subtree.parent = this;
        children.add(subtree);
        modCount++;
    }

    public void remove() {
        if (this.parent != null) {
            this.parent.children.remove(this);
        }
        this.children.clear();
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private Queue<Tree<T>> queue = new LinkedList<>(Collections.singleton(Tree.this));
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

    @Override
    public boolean equals(Object o) {
        Tree<?> tree = null;
        if (!(o instanceof Tree<?>)) {
            return false;
        } else {
            tree = (Tree<?>) o;
        }
        if (!value.equals(tree.value)) return false;
        return children.equals(tree.children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.value);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        if (this.children.isEmpty()) {
            result.append(String.format("%s", value.toString()));
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

    public static void main(String[] args) {
        Tree<String> tree = new Tree<>("R1");
        var a = tree.addNode("A");
        var b = a.addNode("B");

        Tree<String> subtree = new Tree<>("R2");
        subtree.addNode("C");
        subtree.addNode("D");

        tree.addNode(subtree);
        b.remove();
        System.out.println(tree);
    }
}
