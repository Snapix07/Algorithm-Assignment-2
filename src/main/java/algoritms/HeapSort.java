package main.java.algoritms;

import main.java.metrics.PerformanceMetrics;

public class HeapSort {

    public static void sort(int[] arr, PerformanceMetrics metrics) {
        int n = arr.length;


        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i, metrics);
        }


        for (int i = n - 1; i > 0; i--) {
            swap(arr, 0, i, metrics);
            heapify(arr, i, 0, metrics);
        }
    }

    private static void heapify(int[] arr, int n, int i, PerformanceMetrics metrics) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < n) {
            metrics.incrementComparisons();
            metrics.incrementArrayAccesses(2);
            if (arr[l] > arr[largest]) largest = l;
        }

        if (r < n) {
            metrics.incrementComparisons();
            metrics.incrementArrayAccesses(2);
            if (arr[r] > arr[largest]) largest = r;
        }

        if (largest != i) {
            swap(arr, i, largest, metrics);
            heapify(arr, n, largest, metrics);
        }
    }

    private static void swap(int[] arr, int i, int j, PerformanceMetrics metrics) {
        metrics.incrementSwaps();
        metrics.incrementArrayAccesses(4);
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
