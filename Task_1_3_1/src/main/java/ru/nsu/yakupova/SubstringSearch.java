package ru.nsu.yakupova;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 *  Class for substring search (Task_1_3_1).
 */
public class SubstringSearch {

    /**
     *  Method for converting string to UTF_8.
     */
    public static String toUTF8(String substring) {
        var bytes = substring.getBytes();
        return new String(bytes, StandardCharsets.UTF_8);
    }

    /**
     *  Method for searching substring.
     */
    public static List<Integer> find(String filename, String substring) {
        var target = toUTF8(substring);
        List<Integer> positions = new ArrayList<>();

        int bufferSize = 4096;
        char[] buffer = new char[bufferSize + target.length() - 1];

        int overlap = target.length() - 1;
        int offset = 0;

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(filename), StandardCharsets.UTF_8))) {
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
