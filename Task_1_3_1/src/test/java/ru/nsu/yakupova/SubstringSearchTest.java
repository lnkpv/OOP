package ru.nsu.yakupova;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.Test;

/**
 * Class for testing substring search (Task_1_3_1).
 */
class SubstringSearchTest {

    @Test
    void checkEmpty() {
        List<Integer> positions = SubstringSearch.find("empty.txt", "бра");
        assertEquals(positions.toString(), Arrays.toString(new int[0]));
    }

    @Test
    void checkBasic() {
        List<Integer> positions = SubstringSearch.find("basic.txt", "бра");
        var ans = new int[]{1, 8};
        assertEquals(positions.toString(), Arrays.toString(ans));
    }

    @Test
    void checkSameLetters() {
        List<Integer> positions = SubstringSearch.find("sameLetters.txt", "а");
        var ans = new int[]{0, 1, 2, 3, 4, 6, 7};
        assertEquals(positions.toString(), Arrays.toString(ans));
    }

    @Test
    void checkIntersection() {
        List<Integer> positions = SubstringSearch.find(
                "intersection.txt", "аба");
        var ans = new int[]{0, 2, 5, 10};
        assertEquals(positions.toString(), Arrays.toString(ans));
    }

    @Test
    void checkGenerateFile() {
        SubstringSearch.generateFile("random.txt");

        var ans = new long[200000];
        long j = 0;
        for (int i = 0; i < 200000; i += 2) {
            ans[i] = 1 + 28 * j;
            ans[i + 1] = 10 + 28 * j;
            j++;
        }
        List<Integer> positions = SubstringSearch.find("random.txt", "bac");
        assertEquals(positions.toString(), Arrays.toString(ans));

        // clear huge file
        File file;
        URL resource = SubstringSearch.class.getClassLoader().getResource("random.txt");
        file = new File(Objects.requireNonNull(resource).getFile());
        try {
            FileWriter fwOb = new FileWriter(file, false);
            PrintWriter pwOb = new PrintWriter(fwOb, false);
            pwOb.flush();
            pwOb.close();
            fwOb.close();
        } catch (IOException e) {
            System.out.println("Cannot clear the file");
        }
    }
}