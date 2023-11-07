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
}