package ru.nsu.yakupova;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Class for reading data from file.
 */
public class Reader {
    /**
     * Method for reading data from file.
     */
    public static void readFromFile(Graph<String> graph, String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String[] cities = reader.readLine().split(" ");
            for (String city : cities) {
                graph.addVertex(city);
            }

            String line = reader.readLine();
            while (line != null) {
                String[] parts = line.split(" ");
                graph.addEdge(parts[0], parts[1], Integer.parseInt(parts[2]));
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
