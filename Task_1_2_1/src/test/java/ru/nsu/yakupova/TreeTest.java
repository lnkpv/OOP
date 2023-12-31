package ru.nsu.yakupova;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Objects;
import org.junit.jupiter.api.Test;

/**
 * Tests for Tree.
 */
class TreeTest {

    @Test
    void checkNotEquals_one() {
        Tree<String> t1 = new Tree<>("R1");
        Tree<String> t2 = new Tree<>("R2");
        assertNotEquals(t1, t2);
    }

    @Test
    void checkEquals_one() {
        Tree<String> t1 = new Tree<>("R1");
        Tree<String> t2 = new Tree<>("R1");
        assertEquals(t1, t2);
    }

    @Test
    void checkNotEquals_more() {
        Tree<String> t1 = new Tree<>("R1");
        var a = t1.addNode("A");
        a.addNode("B");
        Tree<String> subtree = new Tree<>("R2");
        subtree.addNode("C");
        subtree.addNode("D");
        assertNotEquals(t1, subtree);
        t1.addNode(subtree);
        assertNotEquals(t1, subtree);

    }

    @Test
    void checkEquals_more() {
        Tree<String> t1 = new Tree<>("R1");
        t1.addNode("A");
        t1.addNode("B");
        Tree<String> subtree = new Tree<>("R1");
        subtree.addNode("A");
        subtree.addNode("B");
        assertEquals(t1, subtree);
        t1.addNode(subtree);
        assertNotEquals(t1, subtree);
    }

    @Test
    void checkToString() {
        Tree<String> t1 = new Tree<>("R1");
        t1.addNode("A");
        t1.addNode("B");
        Tree<String> subtree = new Tree<>("R1");
        subtree.addNode("A");
        subtree.addNode("B");
        t1.addNode(subtree);
        assertEquals(t1.toString(), "{R1 -> (A, B, {R1 -> (A, B)})}");
    }

    @Test
    void checkRemoving() {
        Tree<String> t1 = new Tree<>("R1");
        t1.addNode("A");
        var b = t1.addNode("B");
        Tree<String> t2 = new Tree<>("R1");
        t2.addNode("A");
        b.remove();
        assertEquals(t1, t2);
    }

    @Test
    void checkPrintOneElem() {
        Tree<String> t1 = new Tree<>("R1");
        var a = t1.addNode("A");
        a.addNode("B");
        a.remove();
        assertEquals(t1.toString(), "{R1}");
    }

    @Test
    void checkPrintEmptyTree() {
        Tree<String> t1 = new Tree<>("R1");
        t1.addNode("A");
        t1.addNode("B");
        t1.remove();
        assertEquals(t1.toString(), "{R1}");
    }

    @Test
    void checkGetChild() {
        Tree<String> t1 = new Tree<>("R1");
        t1.addNode("A");
        t1.addNode("B");
        assertEquals(t1.getChild().toString(), "[A, B]");
    }

    @Test
    void checkGetValue() {
        Tree<String> t1 = new Tree<>("R1");
        t1.addNode("A");
        t1.addNode("B");
        assertEquals(t1.getValue(), "R1");
    }

    @Test
    void checkBuildingTreeDfs() {
        Tree<String> t1 = new Tree<>("0");
        var a = t1.addNode("1");
        a.addNode("2");
        Tree<String> t2 = new Tree<>("3");
        t2.addNode("4");
        t2.addNode("4");
        t1.addNode(t2);

        ArrayList<String> result = new ArrayList<>();
        for (var vertex : new Dfs<>(t1)) {
            result.add(vertex);
        }
        assertEquals(result.toString(), "[0, 1, 2, 3, 4, 4]");
    }


    @Test
    void checkRemovingElemInCycleDfs() {
        Tree<String> t1 = new Tree<>("0");
        var a = t1.addNode("1");
        a.addNode("2");
        Tree<String> t2 = new Tree<>("3");
        t2.addNode("4");
        t2.addNode("4");
        t1.addNode(t2);

        ArrayList<String> result = new ArrayList<>();
        var iterator = new Dfs.DfsIterator<>(t1);
        while (iterator.hasNext()) {
            var elem = iterator.next();
            if (Objects.equals(elem, "2")) {
                iterator.remove();
            } else {
                result.add(elem);
            }
        }
        assertEquals(result.toString(), "[0, 1, 3, 4, 4]");
    }


    @Test
    void checkRemovingSameElemInCycleDfs() {
        Tree<String> t1 = new Tree<>("0");
        var a = t1.addNode("1");
        a.addNode("2");
        Tree<String> t2 = new Tree<>("3");
        t2.addNode("4");
        t2.addNode("4");
        t1.addNode(t2);

        ArrayList<String> result = new ArrayList<>();
        var iterator = new Dfs.DfsIterator<>(t1);
        while (iterator.hasNext()) {
            var elem = iterator.next();
            if (Objects.equals(elem, "4")) {
                iterator.remove();
            } else {
                result.add(elem);
            }
        }
        assertEquals(result.toString(), "[0, 1, 2, 3]");
    }

    @Test
    void checkRemovingSubtreeInCycleDfs() {
        Tree<String> t1 = new Tree<>("0");
        var a = t1.addNode("1");
        a.addNode("2");
        Tree<String> t2 = new Tree<>("3");
        t2.addNode("4");
        t2.addNode("4");
        t1.addNode(t2);

        ArrayList<String> result = new ArrayList<>();
        var iterator = new Dfs.DfsIterator<>(t1);
        while (iterator.hasNext()) {
            var elem = iterator.next();
            if (Objects.equals(elem, "1")) {
                iterator.remove();
            } else {
                result.add(elem);
            }
        }
        assertEquals(result.toString(), "[0, 3, 4, 4]");
    }


    @Test
    void checkBuildingTreeBfs() {
        Tree<String> t1 = new Tree<>("0");
        var a = t1.addNode("1");
        a.addNode("2");
        Tree<String> t2 = new Tree<>("3");
        t2.addNode("4");
        t2.addNode("4");
        t1.addNode(t2);

        ArrayList<String> result = new ArrayList<>();
        for (var vertex : new Bfs<>(t1)) {
            result.add(vertex);
        }
        assertEquals(result.toString(), "[0, 1, 3, 2, 4, 4]");
    }


    @Test
    void checkRemovingElemInCycleBfs() {
        Tree<String> t1 = new Tree<>("0");
        var a = t1.addNode("1");
        a.addNode("2");
        Tree<String> t2 = new Tree<>("3");
        t2.addNode("4");
        t2.addNode("4");
        t1.addNode(t2);

        ArrayList<String> result = new ArrayList<>();
        var iterator = new Bfs.BfsIterator<>(t1);
        while (iterator.hasNext()) {
            var elem = iterator.next();
            if (Objects.equals(elem, "2")) {
                iterator.remove();
            } else {
                result.add(elem);
            }
        }
        assertEquals(result.toString(), "[0, 1, 3, 4, 4]");
    }


    @Test
    void checkRemovingSameElemInCycleBfs() {
        Tree<String> t1 = new Tree<>("0");
        var a = t1.addNode("1");
        a.addNode("2");
        Tree<String> t2 = new Tree<>("3");
        t2.addNode("4");
        t2.addNode("4");
        t1.addNode(t2);

        ArrayList<String> result = new ArrayList<>();
        var iterator = new Bfs.BfsIterator<>(t1);
        while (iterator.hasNext()) {
            var elem = iterator.next();
            if (Objects.equals(elem, "4")) {
                iterator.remove();
            } else {
                result.add(elem);
            }
        }
        assertEquals(result.toString(), "[0, 1, 3, 2]");
    }

    @Test
    void checkRemovingSubtreeInCycleBfs() {
        Tree<String> t1 = new Tree<>("0");
        var a = t1.addNode("1");
        a.addNode("2");
        Tree<String> t2 = new Tree<>("3");
        t2.addNode("4");
        t2.addNode("4");
        t1.addNode(t2);

        ArrayList<String> result = new ArrayList<>();
        var iterator = new Bfs.BfsIterator<>(t1);
        while (iterator.hasNext()) {
            var elem = iterator.next();
            if (Objects.equals(elem, "1")) {
                iterator.remove();
            } else {
                result.add(elem);
            }
        }
        assertEquals(result.toString(), "[0, 3, 4, 4]");
    }

    @Test
    void checkConcurrentModExceptionBfs() {
        Tree<String> t1 = new Tree<>("0");
        Tree<String> t2 = new Tree<>("3");
        t2.addNode("4");
        t2.addNode("4");
        t1.addNode(t2);
        var a = t1.addNode("1");
        var b = a.addNode("2");
        assertThrows(ConcurrentModificationException.class, () -> {
            for (var vertex : new Bfs<>(a)) {
                if (Objects.equals(vertex, "2")) {
                    b.remove();
                }
            }
        });
    }

    @Test
    void checkConcurrentModExceptionDfs() {
        Tree<String> t1 = new Tree<>("0");
        Tree<String> t2 = new Tree<>("3");
        t2.addNode("4");
        t2.addNode("4");
        t1.addNode(t2);
        var a = t1.addNode("1");
        var b = a.addNode("2");
        assertThrows(ConcurrentModificationException.class, () -> {
            for (var vertex : new Dfs<>(a)) {
                if (Objects.equals(vertex, "2")) {
                    b.remove();
                }
            }
        });
    }

    @Test
    void checkConcurrentModExceptionBfs_out() {
        Tree<String> t1 = new Tree<>("0");
        Tree<String> t2 = new Tree<>("3");
        t2.addNode("4");
        t2.addNode("4");
        t1.addNode(t2);
        var a = t1.addNode("1");
        var b = a.addNode("2");
        assertDoesNotThrow(() -> {
            for (var vertex : new Bfs<>(t2)) {
                if (Objects.equals(vertex, "4")) {
                    b.remove();
                }
            }
        });
    }

    @Test
    void checkConcurrentModExceptionDfs_out() {
        Tree<String> t1 = new Tree<>("0");
        Tree<String> t2 = new Tree<>("3");
        t2.addNode("4");
        t2.addNode("4");
        t1.addNode(t2);
        var a = t1.addNode("1");
        var b = a.addNode("2");
        assertDoesNotThrow(() -> {
            for (var vertex : new Dfs<>(t2)) {
                if (Objects.equals(vertex, "4")) {
                    b.remove();
                }
            }
        });
    }
}