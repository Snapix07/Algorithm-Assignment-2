package main.java.algoritms;

import main.java.metrics.PerformanceMetrics;

public class SelectionSort {

    public static void sort(int[] arr, PerformanceMetrics metrics) {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            boolean swapped = false;

            for (int j = i + 1; j < n; j++) {
                metrics.incrementComparisons();
                metrics.incrementArrayAccesses(2);
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }

            if (minIdx != i) {
                swap(arr, i, minIdx, metrics);
                swapped = true;
            }


            if (!swapped) break;
        }
    }

    private static void swap(int[] arr, int i, int j, PerformanceMetrics metrics) {
        metrics.incrementArrayAccesses(4);
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        metrics.incrementSwaps();
    }
}
