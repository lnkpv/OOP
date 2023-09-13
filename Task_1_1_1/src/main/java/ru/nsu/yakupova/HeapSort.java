package ru.nsu.yakupova;


public class HeapSort {
    public static void heapsort(int[] arr) {
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        for (int i = n - 1; i >= 0; i--) {
            int cur_root = arr[0];
            arr[0] = arr[i];
            arr[i] = cur_root;

            heapify(arr, i, 0);
        }
    }

    static public void heapify(int[] arr, int n, int i) {
        int root = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < n && arr[l] > arr[root])
            root = l;

        if (r < n && arr[r] > arr[root])
            root = r;

        if (root != i) {
            int tmp = arr[i];
            arr[i] = arr[root];
            arr[root] = tmp;

            heapify(arr, n, root);
        }
    }

    static void printArray(int[] arr) {
        for (int j : arr) System.out.print(j + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {5, 4, 3, 2, 1};
        heapsort(arr);

        printArray(arr);
    }
}
