package ru.nsu.yakupova;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Class for Parallel search with Thread.
 */
public class ParallelPrimeCheckThread implements Runnable {
    private final Integer[] arr;
    private final int start;
    private final int end;
    private static final AtomicBoolean foundComposite = new AtomicBoolean(false);

    /**
     * Constructor.
     */
    public ParallelPrimeCheckThread(Integer[] arr, int start, int end) {
        this.arr = arr;
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        for (int i = start; i < end && !foundComposite.get(); i++) {
            if (ComparisonOfMethods.notPrime(arr[i])) {
                foundComposite.set(true);
                break;
            }
        }
    }

    /**
     * Method for finding.
     */
    public static boolean hasComposite(Integer[] arr, int numberOfThreads)
            throws InterruptedException {
        if (arr == null || arr.length == 0) {
            return false;
        }

        foundComposite.set(false);

        int chunkSize = (int) Math.ceil(arr.length / (double) numberOfThreads);
        Thread[] threads = new Thread[numberOfThreads];

        for (int i = 0; i < numberOfThreads; i++) {
            int start = i * chunkSize;
            int end = Math.min(start + chunkSize, arr.length);
            threads[i] = new Thread(new ParallelPrimeCheckThread(arr, start, end));
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        return foundComposite.get();
    }
}
