package ru.nsu.yakupova;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class for DFS.
 */
public class Dfs<T> implements Iterable<T> {
    private final Tree<T> root;

    public Dfs(Tree<T> root) {
        this.root = root;
    }

    /**
     * New iterator.
     */
    @Override
    public Iterator<T> iterator() {
        return new DfsIterator<>(root);
    }

    /**
     * Class for Iterator.
     */
    public static class DfsIterator<E> implements Iterator<E> {

        private final ArrayList<Tree<E>> queue;
        private Tree<E> curNode;

        /**
         * TreeIterator construct.
         */
        public DfsIterator(Tree<E> node) {
            queue = new ArrayList<>();
            queue.add(node);
            curNode = node;
            curNode.changeBlocked();
        }

        /**
         * New hasNext for iterator.
         */
        @Override
        public boolean hasNext() {
            curNode.changeBlocked();
            return !queue.isEmpty();
        }

        /**
         * New next for iterator.
         */
        @Override
        public E next() {
            Tree<E> current = queue.get(0);
            queue.remove(0);
            if (current == null) {
                throw new NoSuchElementException();
            }
            if (!current.getChild().isEmpty()) {
                queue.addAll(0, current.getChild());
            }
            curNode = current;
            curNode.changeBlocked();
            return current.value;
        }

        /**
         * New remove for iterator.
         */
        @Override
        public void remove() {
            Tree<E> current = curNode;
            int curCount = current.getChild().size();

            if (curCount > 0) {
                queue.subList(0, curCount).clear();
            }
            curNode.changeBlocked();
            current.remove();
        }
    }
}