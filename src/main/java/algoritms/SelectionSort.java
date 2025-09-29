package main.java.algoritms;

import main.java.metrics.PerformanceMetrics;

public class SelectionSort {

    public static PerformanceMetrics sortWithMetrics(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Input array cannot be null");
        }

        PerformanceMetrics metrics = new PerformanceMetrics();
        int n = arr.length;

        if (n <= 1) {
            return metrics;
        }

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
        return metrics;
    }

    private static void swap(int[] arr, int i, int j, PerformanceMetrics metrics) {
        metrics.incrementArrayAccesses(4);
        metrics.incrementSwaps();
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void sort(int[] arr) {
        if (arr == null) throw new IllegalArgumentException("Input array cannot be null");
        sortWithMetrics(arr);
    }

    public static void main(String[] args) {
        int[] arr = {64, 25, 12, 22, 11};
        PerformanceMetrics metrics = sortWithMetrics(arr);
        System.out.print("Sorted array: ");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println("\nMetrics: " + metrics);
    }
}