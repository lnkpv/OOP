package ru.nsu.yakupova;

/**
 * Class for Sequential search.
 */
public class SequentialPrimeCheck {

    /**
     * Method for finding.
     */
    public static boolean hasComposite(int[] arr) {
        for (int num : arr) {
            if (ComparisonOfMethods.NotPrime(num)) {
                return true;
            }
        }
        return false;
    }
}