package ru.nsu.yakupova;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class for substring search (Task_1_3_1).
 */
public class SubstringSearch {

    /**
     * Method for converting string to UTF_8.
     */
    public static String toUtf8(String substring) {
        var bytes = substring.getBytes();
        return new String(bytes, StandardCharsets.UTF_8);
    }

    /**
     * Method for reading file.
     */
    public static InputStreamReader fileReader(String name) {
        InputStream is = SubstringSearch.class.getClassLoader().getResourceAsStream(name);
        return new InputStreamReader(Objects.requireNonNull(is), StandardCharsets.UTF_8);
    }

    /**
     * Method for searching substring.
     */
    public static List<Integer> find(String filename, String substring) {
        var target = toUtf8(substring);
        List<Integer> positions = new ArrayList<>();

        int bufferSize = 4096;
        char[] buffer = new char[bufferSize + target.length() - 1];

        int overlap = target.length() - 1;
        int offset = 0;

        try (BufferedReader reader = new BufferedReader(fileReader(filename))) {
            int charsRead;
            String prevData = "";

            while ((charsRead = reader.read(buffer, overlap, bufferSize)) != -1) {
                String data = prevData + new String(buffer, overlap, charsRead);
                int index = data.indexOf(target);
                while (index != -1) {
                    positions.add(offset + index);
                    index = data.indexOf(target, index + 1);
                }

                offset += charsRead;
                if (charsRead >= overlap) {
                    prevData = new String(buffer, bufferSize, overlap);
                }
            }
        } catch (IOException e) {
            System.out.println("Cannot read the file.");
        }
        return positions;
    }
}
