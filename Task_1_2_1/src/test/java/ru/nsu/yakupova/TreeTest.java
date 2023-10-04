package ru.nsu.yakupova;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

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
        String result = "{R1 -> (A, B, {R1 -> (A, B)})}";
        assertEquals(t1.toString(), result);
    }

}