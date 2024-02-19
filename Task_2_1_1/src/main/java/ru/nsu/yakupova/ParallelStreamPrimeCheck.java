package ru.nsu.yakupova;

import java.util.Arrays;
import java.util.List;

/**
 * Class for Parallel Stream search.
 */
public class ParallelStreamPrimeCheck {

    /**
     * Method for finding.
     */
    public static boolean hasComposite(Integer[] arr) {
        List<Integer> list = Arrays.asList(arr);
        return list.parallelStream().anyMatch(ComparisonOfMethods::notPrime);
    }
}
