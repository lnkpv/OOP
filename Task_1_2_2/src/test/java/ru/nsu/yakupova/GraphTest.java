package ru.nsu.yakupova;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

/**
 * Tests for Graph.
 */
class GraphTest {
    private List<Map.Entry<String, Integer>> resultMap() {
        var dist = new HashMap<String, Integer>();
        dist.put("C", 0);
        dist.put("D", 2);
        dist.put("E", 4);
        dist.put("F", 5);
        dist.put("G", 9);
        dist.put("B", 10);
        dist.put("A", 14);
        List<Map.Entry<String, Integer>> sorted = new ArrayList<>(dist.entrySet());
        sorted.sort(Map.Entry.comparingByValue());
        return sorted;
    }

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
        var dist = graph.sortVerticesByDistance("C");
        var ans = resultMap();
        assertEquals(dist, ans);
    }

    @Test
    void checkSortingAdjMatrix() {
        var graph = new AdjMatrix<String>();
        Reader.readFromFile(graph, "input.txt");
        var dist = graph.sortVerticesByDistance("C");
        var ans = resultMap();
        assertEquals(dist, ans);
    }

    @Test
    void checkSortingIncMatrix() {
        var graph = new IncMatrix<String>();
        Reader.readFromFile(graph, "input.txt");
        var dist = graph.sortVerticesByDistance("C");
        var ans = resultMap();
        assertEquals(dist, ans);
    }

    @Test
    void checkConstructAdjList() {
        var graph = new AdjList<String>();
        graph.addEdge("A", "B", 3);
        graph.addEdge("B", "C", 1);
        graph.addEdge("D", "C", 2);
        graph.addEdge("E", "B", 1);
        graph.addEdge("E", "D", 2);

        assertEquals(graph.getVertices().size(), 5);
        var res = graph.sortVerticesByDistance("A");
        int[] ans = new int[]{0, 3, 4, 4, 6};
        for (int i = 0; i < res.size(); i++) {
            assertEquals(res.get(i).getValue(), ans[i]);
        }
    }

    @Test
    void checkConstructAdjMatrix() {
        var graph = new AdjMatrix<String>();
        graph.addEdge("A", "B", 3);
        graph.addEdge("B", "C", 1);
        graph.addEdge("D", "C", 2);
        graph.addEdge("E", "B", 1);
        graph.addEdge("E", "D", 2);

        assertEquals(graph.getVertices().size(), 5);
        var res = graph.sortVerticesByDistance("A");
        int[] ans = new int[]{0, 3, 4, 4, 6};
        for (int i = 0; i < res.size(); i++) {
            assertEquals(res.get(i).getValue(), ans[i]);
        }
    }

    @Test
    void checkConstructIncMatrix() {
        var graph = new IncMatrix<String>();
        graph.addEdge("A", "B", 3);
        graph.addEdge("B", "C", 1);
        graph.addEdge("D", "C", 2);
        graph.addEdge("E", "B", 1);
        graph.addEdge("E", "D", 2);

        assertEquals(graph.getVertices().size(), 5);
        var res = graph.sortVerticesByDistance("A");
        int[] ans = new int[]{0, 3, 4, 4, 6};
        for (int i = 0; i < res.size(); i++) {
            assertEquals(res.get(i).getValue(), ans[i]);
        }
    }

    @Test
    void checkRemoveEdgeAdjList() {
        var graph = new AdjList<String>();
        graph.addEdge("A", "B", 3);
        graph.addEdge("B", "C", 1);
        graph.addEdge("D", "C", 2);
        graph.addEdge("E", "B", 1);
        graph.addEdge("E", "D", 2);

        assertEquals(graph.getVertices().size(), 5);
        graph.removeEdge("B", "C");

        var res = graph.sortVerticesByDistance("A");
        int[] ans = new int[]{0, 3, 4, 6, 8};
        for (int i = 0; i < res.size(); i++) {
            assertEquals(res.get(i).getValue(), ans[i]);
        }
    }

    @Test
    void checkRemoveEdgeAdjMatrix() {
        var graph = new AdjMatrix<String>();
        graph.addEdge("A", "B", 3);
        graph.addEdge("B", "C", 1);
        graph.addEdge("D", "C", 2);
        graph.addEdge("E", "B", 1);
        graph.addEdge("E", "D", 2);

        assertEquals(graph.getVertices().size(), 5);
        graph.removeEdge("B", "C");

        var res = graph.sortVerticesByDistance("A");
        int[] ans = new int[]{0, 3, 4, 6, 8};
        for (int i = 0; i < res.size(); i++) {
            assertEquals(res.get(i).getValue(), ans[i]);
        }
    }

    @Test
    void checkRemoveEdgeIncMatrix() {
        var graph = new IncMatrix<String>();
        graph.addEdge("A", "B", 3);
        graph.addEdge("B", "C", 1);
        graph.addEdge("D", "C", 2);
        graph.addEdge("E", "B", 1);
        graph.addEdge("E", "D", 2);

        assertEquals(graph.getVertices().size(), 5);
        graph.removeEdge("B", "C");

        var res = graph.sortVerticesByDistance("A");
        int[] ans = new int[]{0, 3, 4, 6, 8};
        for (int i = 0; i < res.size(); i++) {
            assertEquals(res.get(i).getValue(), ans[i]);
        }
    }

    @Test
    void checkRemoveVertexAdjList() {
        var graph = new AdjList<String>();
        graph.addEdge("A", "B", 3);
        graph.addEdge("B", "C", 1);
        graph.addEdge("D", "C", 2);
        graph.addEdge("E", "B", 1);
        graph.addEdge("E", "D", 2);

        assertEquals(graph.getVertices().size(), 5);
        graph.removeVertex("C");
        assertEquals(graph.getVertices().size(), 4);

        var res = graph.sortVerticesByDistance("A");
        int[] ans = new int[]{0, 3, 4, 6};
        for (int i = 0; i < res.size(); i++) {
            assertEquals(res.get(i).getValue(), ans[i]);
        }
    }

    @Test
    void checkRemoveVertexAdjMatrix() {
        var graph = new AdjMatrix<String>();
        graph.addEdge("A", "B", 3);
        graph.addEdge("B", "C", 1);
        graph.addEdge("D", "C", 2);
        graph.addEdge("E", "B", 1);
        graph.addEdge("E", "D", 2);

        assertEquals(graph.getVertices().size(), 5);
        graph.removeVertex("C");
        assertEquals(graph.getVertices().size(), 4);

        var res = graph.sortVerticesByDistance("A");
        int[] ans = new int[]{0, 3, 4, 6};
        for (int i = 0; i < res.size(); i++) {
            assertEquals(res.get(i).getValue(), ans[i]);
        }
    }

    @Test
    void checkRemoveVertexIncMatrix() {
        var graph = new IncMatrix<String>();
        graph.addEdge("A", "B", 3);
        graph.addEdge("B", "C", 1);
        graph.addEdge("D", "C", 2);
        graph.addEdge("E", "B", 1);
        graph.addEdge("E", "D", 2);

        assertEquals(graph.getVertices().size(), 5);
        graph.removeVertex("C");
        assertEquals(graph.getVertices().size(), 4);

        var res = graph.sortVerticesByDistance("A");
        int[] ans = new int[]{0, 3, 4, 6};
        for (int i = 0; i < res.size(); i++) {
            assertEquals(res.get(i).getValue(), ans[i]);
        }
    }
}
