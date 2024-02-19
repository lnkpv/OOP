package ru.nsu.yakupova;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for Parallel Stream search.
 */
public class ParallelStreamPrimeCheck {

    /**
     * Method for finding.
     */
    public static boolean hasComposite(int[] arr) {
        List<Integer> list = new ArrayList<>(arr.length);
        for (int i : arr) {
            list.add(i);
        }
        return list.parallelStream().anyMatch(ComparisonOfMethods::NotPrime);
    }

}
