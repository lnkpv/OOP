package ru.nsu.yakupova;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Class for Tests (Task_2_1_1).
 */
public class NotPrimeFinderTest {

    /**
     * Method for true checks.
     */
    void checkTrueAsserts(Integer[] arr) {
        assertTrue(SequentialPrimeCheck.hasComposite(arr));
        try {
            assertTrue(ParallelPrimeCheckThread.hasComposite(arr, 4));
            assertTrue(ParallelPrimeCheckThread.hasComposite(arr, 8));
            assertTrue(ParallelPrimeCheckThread.hasComposite(arr, 16));
        } catch (Exception e) {
            System.out.print("found it");
        }
        assertTrue(ParallelStreamPrimeCheck.hasComposite(arr));
    }

    /**
     * Method for false checks.
     */
    void checkFalseAsserts(Integer[] arr) {
        assertFalse(SequentialPrimeCheck.hasComposite(arr));
        try {
            assertFalse(ParallelPrimeCheckThread.hasComposite(arr, 4));
            assertFalse(ParallelPrimeCheckThread.hasComposite(arr, 8));
            assertFalse(ParallelPrimeCheckThread.hasComposite(arr, 16));
        } catch (Exception e) {
            System.out.print("found it");
        }
        assertFalse(ParallelStreamPrimeCheck.hasComposite(arr));
    }

    @Test
    void checkEmpty() {
        Integer[] arr = {};
        checkFalseAsserts(arr);
    }

    @Test
    void checkCommon() {
        Integer[] arr = {3, 0, 10, 45, 7, 19};
        checkTrueAsserts(arr);
    }

    @Test
    void checkNoTargets() {
        Integer[] arr = {2, 3, 13, 7, 19};
        checkFalseAsserts(arr);
    }

    @Test
    void checkLargeWithNotPrime_begin() {
        Integer[] arr2 = {1000850, 1000859, 1000861, 1000889, 1000907,
            1000919, 1000921, 1000931, 1000969,
            1000973, 1000981, 1000999, 1001003,
            1001017, 1001023, 1001027, 1001041, 1001069};
        checkTrueAsserts(arr2);
    }

    @Test
    void checkLargeWithNotPrime_end() {
        Integer[] arr = {1000849, 1000859, 1000861, 1000889, 1000907,
            1000919, 1000921, 1000931, 1000969,
            1000973, 1000981, 1000999, 1001003,
            1001017, 1001023, 1001027, 1001041, 1001070};
        checkTrueAsserts(arr);
    }

    @Test
    void checkLargeWithoutNotPrime() {
        Integer[] arr = {1000849, 1000859, 1000861, 1000889, 1000907,
            1000919, 1000921, 1000931, 1000969,
            1000973, 1000981, 1000999, 1001003,
            1001017, 1001023, 1001027, 1001041, 1001069};
        checkFalseAsserts(arr);
    }

    @Test
    void checkCompare() {
        try {
            assertTrue(ComparisonOfMethods.compare());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}