package ru.nsu.yakupova;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeapSortTest {

    @Test
    void checkMain() {
        HeapSort.main(new String[]{});
        assertTrue(true);
    }

    @Test
    void testDefaultArray() {
        int[] sampleArr = new int[]{5, 4, 3, 2, 1};
        HeapSort.heapsort(sampleArr);
        int[] arr = new int[]{1, 2, 3, 4, 5};
        assertArrayEquals(arr, sampleArr);
    }
}
