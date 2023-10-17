package ru.nsu.yakupova;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *  Class for BFS
 *
 */
public class BFS<T> implements Iterable<T> {
    private final Tree<T> root;

    public BFS(Tree<T> root) {
        this.root = root;
    }

    /**
     * New iterator.
     */
    @Override
    public Iterator<T> iterator() {
        return new BFSIterator<>(root);
    }

    /**
     * Class for Iterator.
     */
    public static class BFSIterator<E> implements Iterator<E> {

        private final ArrayList<Tree<E>> queue;
        private Tree<E> curNode;

        /**
         * TreeIterator construct.
         */
        public BFSIterator(Tree<E> node) {
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
        public E next() {
            Tree<E> current = queue.get(0);
            queue.remove(0);
            if (current == null) {
                throw new NoSuchElementException();
            }
            if (!current.getChild().isEmpty()) {
                queue.addAll(current.getChild());
            }
            curNode = current;
            return current.value;
        }

        /**
         * New remove for iterator.
         */
        @Override
        public void remove() {
            Tree<E> current = curNode;
            int curCount = current.getChild().size();
            int n = queue.size();
            if (curCount > 0) {
                queue.subList(n-curCount, n).clear();
            }
            current.remove();
        }
    }
}