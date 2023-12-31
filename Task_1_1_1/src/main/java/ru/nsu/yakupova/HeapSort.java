package ru.nsu.yakupova;

/**
 * This is the class for heapsort (Task 1_1_1).
 */
public class HeapSort {

    /**
     * This is the method for heapsort.
     */
    public static void heapsort(int[] arr) {
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        for (int i = n - 1; i >= 0; i--) {
            int curRoot = arr[0];
            arr[0] = arr[i];
            arr[i] = curRoot;

            heapify(arr, i, 0);
        }
    }

    /**
     * This is the method for creating a heap.
     */
    static void heapify(int[] arr, int n, int i) {
        while (true) {
            int root = i;
            int leftSon = 2 * i + 1;
            int rightSon = 2 * i + 2;
            if (leftSon < n && arr[leftSon] > arr[root]) {
                root = leftSon;
            }

            if (rightSon < n && arr[rightSon] > arr[root]) {
                root = rightSon;
            }

            if (root != i) {
                int tmp = arr[i];
                arr[i] = arr[root];
                arr[root] = tmp;
                i = root;
                continue;
            }
            break;
        }
    }

    /**
     * This is the method for print array.
     */
    static void printArray(int[] arr) {
        for (int j : arr) {
            System.out.print(j + " ");
        }
        System.out.println();
    }

    /**
     * This is the main method.
     */
    public static void main(String[] args) {
        int[] arr = {5, 4, 3, 2, 1};
        heapsort(arr);

        printArray(arr);
    }
}
