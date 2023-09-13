package ru.nsu.yakupova;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

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

    @Test
    void testAllNegativeArray() {
        int[] sampleArr = new int[]{-5, -2, -3, -1, -6};
        HeapSort.heapsort(sampleArr);
        int[] arr = new int[]{-6, -5, -3, -2, -1};
        assertArrayEquals(arr, sampleArr);
    }

    @Test
    void testMixedArray() {
        int[] sampleArr = new int[]{-5, 2, -3, 1, 6};
        HeapSort.heapsort(sampleArr);
        int[] arr = new int[]{-5, -3, 1, 2, 6};
        assertArrayEquals(arr, sampleArr);
    }
}
