package ru.nsu.yakupova;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

/**
 *  Class for testing substring search (Task_1_3_1).
 */
class SubstringSearchTest {

    @Test
    void checkBasic() {
        List<Integer> positions = SubstringSearch.find("build/resources/main/basic.txt", "бра");
        var ans = new int[]{1, 8};
        assertEquals(positions.toString(), Arrays.toString(ans));
    }

    @Test
    void checkSameLetters() {
        List<Integer> positions = SubstringSearch.find("build/resources/main/sameLetters.txt", "а");
        var ans = new int[]{0, 1, 2, 3, 4, 6, 7};
        assertEquals(positions.toString(), Arrays.toString(ans));
    }

    @Test
    void checkIntersection() {
        List<Integer> positions = SubstringSearch.find("build/resources/main/intersection.txt", "аба");
        var ans = new int[]{0, 2, 5, 10};
        assertEquals(positions.toString(), Arrays.toString(ans));
    }
}