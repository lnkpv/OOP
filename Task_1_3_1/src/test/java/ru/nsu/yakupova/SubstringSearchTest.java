package ru.nsu.yakupova;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Class for testing substring search (Task_1_3_1).
 */
class SubstringSearchTest {

    @Test
    void checkEmpty() {
        var search = new SubstringSearch();
        List<Integer> positions = search.find("empty.txt", "бра");
        assertEquals(positions.toString(), Arrays.toString(new int[0]));
    }

    @Test
    void checkBasic() {
        var search = new SubstringSearch();
        List<Integer> positions = search.find("basic.txt", "бра");
        var ans = new int[]{1, 8};
        assertEquals(positions.toString(), Arrays.toString(ans));
    }

    @Test
    void checkSameLetters() {
        var search = new SubstringSearch();
        List<Integer> positions = search.find("sameLetters.txt", "а");
        var ans = new int[]{0, 1, 2, 3, 4, 6, 7};
        assertEquals(positions.toString(), Arrays.toString(ans));
    }

    @Test
    void checkIntersection() {
        var search = new SubstringSearch();
        List<Integer> positions = search.find(
                "intersection.txt", "аба");
        var ans = new int[]{0, 2, 5, 10};
        assertEquals(positions.toString(), Arrays.toString(ans));
    }

    @Test
    void checkEnglish() {
        var search = new SubstringSearch();
        List<Integer> positions = search.find(
                "english.txt", "ab");
        var ans = new int[]{0, 5, 9, 12, 21, 26, 28};
        assertEquals(positions.toString(), Arrays.toString(ans));
    }

    @Test
    void checkJapanese() {
        var search = new SubstringSearch();
        List<Integer> positions = search.find(
                "japanese.txt", "橋");
        var ans = new int[]{8};
        assertEquals(positions.toString(), Arrays.toString(ans));
    }

    @Test
    void checkGenerateFile() {
        var search = new SubstringSearch();
        var filename = "random.txt";
        search.generateFile(filename);

        var ans = new long[200000];
        long j = 0;
        for (int i = 0; i < 200000; i += 2) {
            ans[i] = 1 + 28 * j;
            ans[i + 1] = 10 + 28 * j;
            j++;
        }
        List<Integer> positions = search.find(filename, "bac");
        assertEquals(positions.toString(), Arrays.toString(ans));
    }
}