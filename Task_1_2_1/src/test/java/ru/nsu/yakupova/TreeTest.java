package ru.nsu.yakupova;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


import java.util.ArrayList;
import java.util.Objects;

import org.junit.jupiter.api.Test;
import ru.nsu.yakupova.Tree.TreeIterator;


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
    void checkBuildingTree() {
        Tree<String> t1 = new Tree<>("0");
        var a = t1.addNode("1");
        a.addNode("2");
        Tree<String> t2 = new Tree<>("3");
        t2.addNode("4");
        t2.addNode("4");
        t1.addNode(t2);

        ArrayList<String> result = new ArrayList<>();
        for (var vertex : t1) {
            result.add(vertex.value);
        }
        assertEquals(result.toString(), "[0, 1, 2, 3, 4, 4]");
    }


    @Test
    void checkRemovingElemInCycle() {
        Tree<String> t1 = new Tree<>("0");
        var a = t1.addNode("1");
        a.addNode("2");
        Tree<String> t2 = new Tree<>("3");
        t2.addNode("4");
        t2.addNode("4");
        t1.addNode(t2);

        ArrayList<String> result = new ArrayList<>();
        TreeIterator<String> iterator = new TreeIterator<>(t1);
        while (iterator.hasNext()) {
            var elem = iterator.next();
            if (Objects.equals(elem.value, "2")) {
                iterator.remove();
            } else {
                result.add(elem.value);
            }
        }
        assertEquals(result.toString(), "[0, 1, 3, 4, 4]");
    }


    @Test
    void checkRemovingSameElemInCycle() {
        Tree<String> t1 = new Tree<>("0");
        var a = t1.addNode("1");
        a.addNode("2");
        Tree<String> t2 = new Tree<>("3");
        t2.addNode("4");
        t2.addNode("4");
        t1.addNode(t2);

        ArrayList<String> result = new ArrayList<>();
        TreeIterator<String> iterator = new TreeIterator<>(t1);
        while (iterator.hasNext()) {
            var elem = iterator.next();
            if (Objects.equals(elem.value, "4")) {
                iterator.remove();
            } else {
                result.add(elem.value);
            }
        }
        assertEquals(result.toString(), "[0, 1, 2, 3]");
    }

    @Test
    void checkRemovingSubtreeInCycle() {
        Tree<String> t1 = new Tree<>("0");
        var a = t1.addNode("1");
        a.addNode("2");
        Tree<String> t2 = new Tree<>("3");
        t2.addNode("4");
        t2.addNode("4");
        t1.addNode(t2);

        ArrayList<String> result = new ArrayList<>();
        TreeIterator<String> iterator = new TreeIterator<>(t1);
        while (iterator.hasNext()) {
            var elem = iterator.next();
            if (Objects.equals(elem.value, "1")) {
                iterator.remove();
            } else {
                result.add(elem.value);
            }
        }
        assertEquals(result.toString(), "[0, 3, 4, 4]");
    }

}