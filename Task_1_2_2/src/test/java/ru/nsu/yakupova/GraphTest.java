package ru.nsu.yakupova;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

/**
 * Tests for Graph.
 */
class GraphTest {
    static class TestArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of(new AdjList<String>(false)),
                    Arguments.of(new AdjMatrix<String>(false)),
                    Arguments.of(new IncMatrix<String>(false))
            );
        }
    }

    static class TestArgumentsProviderExtra implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of(new AdjList<String>(true)),
                    Arguments.of(new AdjMatrix<String>(true)),
                    Arguments.of(new IncMatrix<String>(true))
            );
        }
    }


    private List<Map.Entry<Vertex<String>, Integer>> resultMap() {
        var dist = new HashMap<Vertex<String>, Integer>();
        dist.put(new Vertex<>("C", 0), 0);
        dist.put(new Vertex<>("D", 1), 2);
        dist.put(new Vertex<>("E", 2), 4);
        dist.put(new Vertex<>("F", 3), 5);
        dist.put(new Vertex<>("G", 4), 9);
        dist.put(new Vertex<>("B", 5), 10);
        dist.put(new Vertex<>("A", 6), 14);
        List<Map.Entry<Vertex<String>, Integer>> sorted = new ArrayList<>(dist.entrySet());
        sorted.sort(Map.Entry.comparingByValue());
        return sorted;
    }

    @ParameterizedTest
    @ArgumentsSource(TestArgumentsProviderExtra.class)
    void checkSortingExtra(Graph<String> graph) {
        graph.addEdge("A", "B", 3);
        graph.addEdge("B", "C", -1);
        graph.addEdge("D", "B", 2);
        graph.addEdge("C", "D", 1);

        var res = graph.sortVerticesByDistance("A");
        int[] ans = new int[]{0, 2, 3, 3};
        for (int i = 0; i < res.size(); i++) {
            assertEquals(res.get(i).getValue(), ans[i]);
        }
    }

    @ParameterizedTest
    @ArgumentsSource(TestArgumentsProvider.class)
    void checkReading(Graph<String> graph) {
        Reader.readFromFile(graph, "input.txt");
        assertEquals(graph.getVertices().size(), 7);
    }

    @ParameterizedTest
    @ArgumentsSource(TestArgumentsProvider.class)
    void checkSorting(Graph<String> graph) {
        Reader.readFromFile(graph, "input.txt");
        var dist = graph.sortVerticesByDistance("C");
        var ans = resultMap();
        assertEquals(dist, ans);
    }

    @ParameterizedTest
    @ArgumentsSource(TestArgumentsProvider.class)
    void checkConstruct(Graph<String> graph) {
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

    @ParameterizedTest
    @ArgumentsSource(TestArgumentsProvider.class)
    void checkChangeVertex(Graph<String> graph) {
        graph.addEdge("A", "B", 3);
        graph.addEdge("B", "C", 1);
        graph.addEdge("D", "C", 2);
        graph.addEdge("E", "B", 1);
        graph.addEdge("E", "D", 2);

        assertEquals(graph.getVertices().size(), 5);
        graph.changeVertex("B", "X");
        var res = graph.sortVerticesByDistance("A");
        int[] ans = new int[]{0, 3, 4, 4, 6};
        String[] ansVert = new String[]{"A", "X", "C", "E", "D"};
        for (int i = 0; i < res.size(); i++) {
            assertEquals(res.get(i).getKey().getVertValue(), ansVert[i]);
            assertEquals(res.get(i).getValue(), ans[i]);
        }
    }

    @ParameterizedTest
    @ArgumentsSource(TestArgumentsProvider.class)
    void checkRemoveEdge(Graph<String> graph) {
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

    @ParameterizedTest
    @ArgumentsSource(TestArgumentsProvider.class)
    void checkRemoveVertex(Graph<String> graph) {
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

    @ParameterizedTest
    @ArgumentsSource(TestArgumentsProvider.class)
    void checkChangeStartVert(Graph<String> graph) {
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

    @ParameterizedTest
    @ArgumentsSource(TestArgumentsProvider.class)
    void checkForest(Graph<String> graph) {
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

    @ParameterizedTest
    @ArgumentsSource(TestArgumentsProvider.class)
    void checkOneVert(Graph<String> graph) {
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
        var graph = new AdjList<String>(false);
        graph.addEdge("A", "B", 3);
        graph.addEdge("B", "C", 1);

        assertEquals(graph.toString(), "A: [A -(3)-> B]\n"
                + "B: [B -(3)-> A, B -(1)-> C]\n"
                + "C: [C -(1)-> B]\n");
    }

    @Test
    void checkToStringAdjMatrix() {
        var graph = new AdjMatrix<String>(false);
        graph.addEdge("A", "B", 3);
        graph.addEdge("B", "C", 1);

        assertEquals(graph.toString(), "    |   A|   B|   C\n"
                + "--------------------\n"
                + "   A|   0|   3|   0\n"
                + "   B|   3|   0|   1\n"
                + "   C|   0|   1|   0\n");
    }

    @Test
    void checkToStringIncMatrix() {
        var graph = new IncMatrix<String>(false);
        graph.addEdge("A", "B", 3);
        graph.addEdge("B", "C", 1);

        assertEquals(graph.toString(), "    | A-B| B-C\n"
                + "---------------\n"
                + "   A|   3|   0\n"
                + "   B|   3|   1\n"
                + "   C|   0|   1\n");
    }
}
