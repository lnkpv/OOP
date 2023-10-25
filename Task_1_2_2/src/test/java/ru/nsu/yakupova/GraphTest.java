package ru.nsu.yakupova;


import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 * Tests for Graph.
 */
class GraphTest {

    @Test
    void checkReadingAdjList() {
        var graph = new AdjList<String>();
        Reader.readFromFile(graph, "input.txt");
        assertEquals(graph.getVertices().size(), 7);
    }

    @Test
    void checkReadingAdjMatrix() {
        var graph = new AdjMatrix<String>();
        Reader.readFromFile(graph, "input.txt");
        assertEquals(graph.getVertices().size(), 7);
    }

    @Test
    void checkReadingIncMatrix() {
        var graph = new IncMatrix<String>();
        Reader.readFromFile(graph, "input.txt");
        assertEquals(graph.getVertices().size(), 7);
    }

    @Test
    void checkSortingAdjList() {
        var graph = new AdjList<String>();
        Reader.readFromFile(graph, "input.txt");
        var distances = graph.sortVerticesByDistance("C");
        int[] ans = new int[]{0, 2, 4, 5, 9, 10, 14};
        int n = graph.getVertices().size();
        for (int i = 0; i < n; i++) {
            assertEquals(distances.get(i).getValue(), ans[i]);
        }
    }

    @Test
    void checkSortingAdjMatrix() {
        var graph = new AdjMatrix<String>();
        Reader.readFromFile(graph, "input.txt");
        var distances = graph.sortVerticesByDistance("C");
        int[] ans = new int[]{0, 2, 4, 5, 9, 10, 14};
        int n = graph.getVertices().size();
        for (int i = 0; i < n; i++) {
            assertEquals(distances.get(i).getValue(), ans[i]);
        }
    }

    @Test
    void checkSortingIncMatrix() {
        var graph = new IncMatrix<String>();
        Reader.readFromFile(graph, "input.txt");
        var distances = graph.sortVerticesByDistance("C");
        int[] ans = new int[]{0, 2, 4, 5, 9, 10, 14};
        int n = graph.getVertices().size();
        for (int i = 0; i < n; i++) {
            assertEquals(distances.get(i).getValue(), ans[i]);
        }
    }


}
