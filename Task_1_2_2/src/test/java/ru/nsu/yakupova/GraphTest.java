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

    @Test
    void checkChangeWeightAdjList() {
        var graph = new AdjList<String>();
        graph.addEdge("A", "B", 3);
        graph.addEdge("B", "C", 1);
        graph.addEdge("D", "C", 2);
        graph.addEdge("E", "B", 1);
        graph.addEdge("E", "D", 2);

        assertEquals(graph.getVertices().size(), 5);
        graph.setWeight("D", "E", 1);
        var res = graph.sortVerticesByDistance("A");
        int[] ans = new int[]{0, 3, 4, 4, 5};
        for (int i = 0; i < res.size(); i++) {
            assertEquals(res.get(i).getValue(), ans[i]);
        }
    }

    @Test
    void checkChangeWeightAdjMatrix() {
        var graph = new AdjMatrix<String>();
        graph.addEdge("A", "B", 3);
        graph.addEdge("B", "C", 1);
        graph.addEdge("D", "C", 2);
        graph.addEdge("E", "B", 1);
        graph.addEdge("E", "D", 2);

        assertEquals(graph.getVertices().size(), 5);
        graph.setWeight("D", "E", 1);
        var res = graph.sortVerticesByDistance("A");
        int[] ans = new int[]{0, 3, 4, 4, 5};
        for (int i = 0; i < res.size(); i++) {
            assertEquals(res.get(i).getValue(), ans[i]);
        }
    }

    @Test
    void checkChangeWeightIncMatrix() {
        var graph = new IncMatrix<String>();
        graph.addEdge("A", "B", 3);
        graph.addEdge("B", "C", 1);
        graph.addEdge("D", "C", 2);
        graph.addEdge("E", "B", 1);
        graph.addEdge("E", "D", 2);

        assertEquals(graph.getVertices().size(), 5);
        graph.setWeight("D", "E", 1);
        var res = graph.sortVerticesByDistance("A");
        int[] ans = new int[]{0, 3, 4, 4, 5};
        for (int i = 0; i < res.size(); i++) {
            assertEquals(res.get(i).getValue(), ans[i]);
        }
    }

    @Test
    void checkChangeStartVertAdjList() {
        var graph = new AdjList<String>();
        graph.addEdge("A", "B", 3);
        graph.addEdge("B", "C", 1);
        graph.addEdge("D", "C", 2);
        graph.addEdge("D", "A", 1);

        assertEquals(graph.getVertices().size(), 4);

        var res = graph.sortVerticesByDistance("A");
        int[] ans = new int[]{0, 1, 3, 3};
        for (int i = 0; i < res.size(); i++) {
            assertEquals(res.get(i).getValue(), ans[i]);
        }

        res = graph.sortVerticesByDistance("B");
        ans = new int[]{0, 1, 3, 3};
        for (int i = 0; i < res.size(); i++) {
            assertEquals(res.get(i).getValue(), ans[i]);
        }

        res = graph.sortVerticesByDistance("C");
        ans = new int[]{0, 1, 2, 3};
        for (int i = 0; i < res.size(); i++) {
            assertEquals(res.get(i).getValue(), ans[i]);
        }

        res = graph.sortVerticesByDistance("D");
        ans = new int[]{0, 1, 2, 3};
        for (int i = 0; i < res.size(); i++) {
            assertEquals(res.get(i).getValue(), ans[i]);
        }
    }

    @Test
    void checkChangeStartVertAdjMatrix() {
        var graph = new AdjMatrix<String>();
        graph.addEdge("A", "B", 3);
        graph.addEdge("B", "C", 1);
        graph.addEdge("D", "C", 2);
        graph.addEdge("D", "A", 1);

        assertEquals(graph.getVertices().size(), 4);

        var res = graph.sortVerticesByDistance("A");
        int[] ans = new int[]{0, 1, 3, 3};
        for (int i = 0; i < res.size(); i++) {
            assertEquals(res.get(i).getValue(), ans[i]);
        }

        res = graph.sortVerticesByDistance("B");
        ans = new int[]{0, 1, 3, 3};
        for (int i = 0; i < res.size(); i++) {
            assertEquals(res.get(i).getValue(), ans[i]);
        }

        res = graph.sortVerticesByDistance("C");
        ans = new int[]{0, 1, 2, 3};
        for (int i = 0; i < res.size(); i++) {
            assertEquals(res.get(i).getValue(), ans[i]);
        }

        res = graph.sortVerticesByDistance("D");
        ans = new int[]{0, 1, 2, 3};
        for (int i = 0; i < res.size(); i++) {
            assertEquals(res.get(i).getValue(), ans[i]);
        }
    }

    @Test
    void checkChangeStartVertIncMatrix() {
        var graph = new IncMatrix<String>();
        graph.addEdge("A", "B", 3);
        graph.addEdge("B", "C", 1);
        graph.addEdge("D", "C", 2);
        graph.addEdge("D", "A", 1);

        assertEquals(graph.getVertices().size(), 4);

        var res = graph.sortVerticesByDistance("A");
        int[] ans = new int[]{0, 1, 3, 3};
        for (int i = 0; i < res.size(); i++) {
            assertEquals(res.get(i).getValue(), ans[i]);
        }

        res = graph.sortVerticesByDistance("B");
        ans = new int[]{0, 1, 3, 3};
        for (int i = 0; i < res.size(); i++) {
            assertEquals(res.get(i).getValue(), ans[i]);
        }

        res = graph.sortVerticesByDistance("C");
        ans = new int[]{0, 1, 2, 3};
        for (int i = 0; i < res.size(); i++) {
            assertEquals(res.get(i).getValue(), ans[i]);
        }

        res = graph.sortVerticesByDistance("D");
        ans = new int[]{0, 1, 2, 3};
        for (int i = 0; i < res.size(); i++) {
            assertEquals(res.get(i).getValue(), ans[i]);
        }
    }

    @Test
    void checkForestAdjList() {
        var graph = new AdjList<String>();
        graph.addEdge("A", "B", 3);
        graph.addEdge("B", "C", 1);
        graph.addEdge("D", "C", 2);
        graph.addEdge("E", "F", 2);

        assertEquals(graph.getVertices().size(), 6);
        var res = graph.sortVerticesByDistance("A");
        int[] ans = new int[]{0, 3, 4, 6, 2147483647, 2147483647};
        for (int i = 0; i < res.size(); i++) {
            assertEquals(res.get(i).getValue(), ans[i]);
        }

        res = graph.sortVerticesByDistance("E");
        ans = new int[]{0, 2, 2147483647, 2147483647, 2147483647, 2147483647};
        for (int i = 0; i < res.size(); i++) {
            assertEquals(res.get(i).getValue(), ans[i]);
        }
    }

    @Test
    void checkForestAdjMatrix() {
        var graph = new AdjMatrix<String>();
        graph.addEdge("A", "B", 3);
        graph.addEdge("B", "C", 1);
        graph.addEdge("D", "C", 2);
        graph.addEdge("E", "F", 2);

        assertEquals(graph.getVertices().size(), 6);
        var res = graph.sortVerticesByDistance("A");
        int[] ans = new int[]{0, 3, 4, 6, 2147483647, 2147483647};
        for (int i = 0; i < res.size(); i++) {
            assertEquals(res.get(i).getValue(), ans[i]);
        }

        res = graph.sortVerticesByDistance("E");
        ans = new int[]{0, 2, 2147483647, 2147483647, 2147483647, 2147483647};
        for (int i = 0; i < res.size(); i++) {
            assertEquals(res.get(i).getValue(), ans[i]);
        }
    }

    @Test
    void checkForestIncMatrix() {
        var graph = new IncMatrix<String>();
        graph.addEdge("A", "B", 3);
        graph.addEdge("B", "C", 1);
        graph.addEdge("D", "C", 2);
        graph.addEdge("E", "F", 2);

        assertEquals(graph.getVertices().size(), 6);
        var res = graph.sortVerticesByDistance("A");
        int[] ans = new int[]{0, 3, 4, 6, 2147483647, 2147483647};
        for (int i = 0; i < res.size(); i++) {
            assertEquals(res.get(i).getValue(), ans[i]);
        }

        res = graph.sortVerticesByDistance("E");
        ans = new int[]{0, 2, 2147483647, 2147483647, 2147483647, 2147483647};
        for (int i = 0; i < res.size(); i++) {
            assertEquals(res.get(i).getValue(), ans[i]);
        }
    }

    @Test
    void checkOneVertAdjList() {
        var graph = new AdjList<String>();
        graph.addEdge("A", "B", 3);
        graph.addEdge("B", "C", 1);
        graph.addEdge("D", "C", 2);
        graph.addVertex("E");

        assertEquals(graph.getVertices().size(), 5);
        var res = graph.sortVerticesByDistance("A");
        int[] ans = new int[]{0, 3, 4, 6, 2147483647};
        for (int i = 0; i < res.size(); i++) {
            assertEquals(res.get(i).getValue(), ans[i]);
        }

        res = graph.sortVerticesByDistance("E");
        ans = new int[]{0, 2147483647, 2147483647, 2147483647, 2147483647};
        for (int i = 0; i < res.size(); i++) {
            assertEquals(res.get(i).getValue(), ans[i]);
        }
    }

    @Test
    void checkOneVertAdjMatrix() {
        var graph = new AdjMatrix<String>();
        graph.addEdge("A", "B", 3);
        graph.addEdge("B", "C", 1);
        graph.addEdge("D", "C", 2);
        graph.addVertex("E");

        assertEquals(graph.getVertices().size(), 5);
        var res = graph.sortVerticesByDistance("A");
        int[] ans = new int[]{0, 3, 4, 6, 2147483647};
        for (int i = 0; i < res.size(); i++) {
            assertEquals(res.get(i).getValue(), ans[i]);
        }

        res = graph.sortVerticesByDistance("E");
        ans = new int[]{0, 2147483647, 2147483647, 2147483647, 2147483647};
        for (int i = 0; i < res.size(); i++) {
            assertEquals(res.get(i).getValue(), ans[i]);
        }
    }

    @Test
    void checkOneVertIncMatrix() {
        var graph = new IncMatrix<String>();
        graph.addEdge("A", "B", 3);
        graph.addEdge("B", "C", 1);
        graph.addEdge("D", "C", 2);
        graph.addVertex("E");

        assertEquals(graph.getVertices().size(), 5);
        var res = graph.sortVerticesByDistance("A");
        int[] ans = new int[]{0, 3, 4, 6, 2147483647};
        for (int i = 0; i < res.size(); i++) {
            assertEquals(res.get(i).getValue(), ans[i]);
        }

        res = graph.sortVerticesByDistance("E");
        ans = new int[]{0, 2147483647, 2147483647, 2147483647, 2147483647};
        for (int i = 0; i < res.size(); i++) {
            assertEquals(res.get(i).getValue(), ans[i]);
        }
    }

    @Test
    void checkToStringAdjList() {
        var graph = new AdjList<String>();
        graph.addEdge("A", "B", 3);
        graph.addEdge("B", "C", 1);

        assertEquals(graph.toString(), """
                A: [A -(3)-> B]
                B: [B -(3)-> A, B -(1)-> C]
                C: [C -(1)-> B]
                """);
    }

    @Test
    void checkToStringAdjMatrix() {
        var graph = new AdjMatrix<String>();
        graph.addEdge("A", "B", 3);
        graph.addEdge("B", "C", 1);

        assertEquals(graph.toString(), """
                    |   A|   B|   C
                --------------------
                   A|   0|   3|   0
                   B|   3|   0|   1
                   C|   0|   1|   0
                """);
    }

    @Test
    void checkToStringIncMatrix() {
        var graph = new IncMatrix<String>();
        graph.addEdge("A", "B", 3);
        graph.addEdge("B", "C", 1);

        assertEquals(graph.toString(), """
                    | A-B| B-C
                ---------------
                   A|   3|   0
                   B|   3|   1
                   C|   0|   1
                """);
    }
}
