package ru.nsu.yakupova;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
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
    private boolean resource = true;
    private BufferedReader reader = null;

    /**
     * Method for converting string to UTF_8.
     */
    private static String toUtf8(String substring) {
        var bytes = substring.getBytes();
        return new String(bytes, StandardCharsets.UTF_8);
    }

    /**
     * Method for reading file.
     */
    public void fileReader(String filename) throws FileNotFoundException {
        if (this.resource) {
            InputStream is = SubstringSearch.class.getClassLoader()
                    .getResourceAsStream(filename);
            this.reader = new BufferedReader(new InputStreamReader(
                    Objects.requireNonNull(is), StandardCharsets.UTF_8));
        } else {
            FileInputStream file = new FileInputStream(filename);
            this.reader = new BufferedReader(new InputStreamReader(
                    file, StandardCharsets.UTF_8));
        }
    }

    /**
     * Main method.
     */
    public List<Integer> find(String filename, String substring) {
        var target = toUtf8(substring);
        var result = findSubstring(filename, target);
        if (!this.resource) {
            this.resource = true;
            clearHugeFile(filename);
        }
        return result;
    }

    /**
     * Method for searching substring.
     */
    private List<Integer> findSubstring(String filename, String target) {

        List<Integer> positions = new ArrayList<>();
        int bufferSize = 4096;
        char[] buffer = new char[bufferSize + target.length() - 1];
        int overlap = target.length() - 1;
        int offset = 0;

        try {
            fileReader(filename);
            int charsRead;
            String prevData = "";

            while ((charsRead = reader.read(buffer, overlap, bufferSize)) != -1) {
                String data = prevData + new String(buffer, overlap, charsRead);
                int index = data.indexOf(target);
                while (index != -1) {
                    if (offset == 0) {
                        positions.add(index);
                    } else {
                        positions.add(offset - overlap + index);
                    }
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

    /**
     * Method for generating huge file.
     */
    public void generateFile(String filename) {
        this.resource = false;
        try {
            var writer = new FileWriter(filename);
            for (int i = 0; i < 100000; i++) {
                writer.write("abacaabcaabacaaaaaaaaaaabca\n");
                writer.flush();
            }
        } catch (IOException e) {
            System.out.println("Cannot generate the file.");
        }
    }

    /**
     * Method for clearing huge file.
     */
    public void clearHugeFile(String filename) {
        try {
            var file = new FileWriter(filename);
            file.flush();
            file.close();
        } catch (IOException e) {
            System.out.println("Cannot clear the file");
        }
    }
}