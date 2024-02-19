package ru.nsu.yakupova;


/**
 * Class for Global Method.
 */
public class ComparisonOfMethods {

    /**
     * Global Method for not prime numbers.
     */
    public static boolean NotPrime(int num) {
        if (num <= 1) {
            return true;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method for printing execution time.
     */
    public static void main(String[] args) {
        int[] arr = new int[150000];
        for (int i = 0; i < 150000; i += 2) {
            arr[i] = 1000003;
            arr[i + 1] = 1000033;
        }
        var start1 = System.nanoTime();
        SequentialPrimeCheck.hasComposite(arr);
        var end1 = System.nanoTime();
        long time1 = end1 - start1;

        var start2 = System.nanoTime();
        ParallelPrimeCheckThread.hasComposite(arr, 4);
        var end2 = System.nanoTime();
        long time2 = end2 - start2;

        var start3 = System.nanoTime();
        ParallelPrimeCheckThread.hasComposite(arr, 8);
        var end3 = System.nanoTime();
        long time3 = end3 - start3;

        var start4 = System.nanoTime();
        ParallelPrimeCheckThread.hasComposite(arr, 16);
        var end4 = System.nanoTime();
        long time4 = end4 - start4;

        var start5 = System.nanoTime();
        ParallelStreamPrimeCheck.hasComposite(arr);
        var end5 = System.nanoTime();
        long time5 = end5 - start5;

        System.out.println(time1 / 1000000);
        System.out.println(time2 / 1000000);
        System.out.println(time3 / 1000000);
        System.out.println(time4 / 1000000);
        System.out.println(time5 / 1000000);
    }
}
