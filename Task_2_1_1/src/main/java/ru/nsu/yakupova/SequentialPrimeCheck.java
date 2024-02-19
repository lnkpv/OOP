package ru.nsu.yakupova;

/**
 * Class for Sequential search.
 */
public class SequentialPrimeCheck {

    /**
     * Method for finding.
     */
    public static boolean hasComposite(Integer[] arr) {
        for (int num : arr) {
            if (ComparisonOfMethods.notPrime(num)) {
                return true;
            }
        }
        return false;
    }
}