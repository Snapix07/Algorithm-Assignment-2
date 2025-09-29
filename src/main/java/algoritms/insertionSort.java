package main.java.algoritms;

import main.java.metrics.PerformanceMetrics;

public class insertionSort {

    private static int binarySearch(int[] arr, int key, int low, int high, PerformanceMetrics metrics) {
        while (low <= high) {
            int mid = low + (high - low) / 2;
            metrics.incrementComparisons();
            metrics.incrementArrayAccesses();

            if (arr[mid] == key) {
                return mid + 1;
            } else if (arr[mid] < key) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    public static PerformanceMetrics sortWithMetrics(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Input array cannot be null");
        }

        PerformanceMetrics metrics = new PerformanceMetrics();

        if (arr.length <= 1) {
            return metrics;
        }

        for (int i = 1; i < arr.length; i++) {
            metrics.incrementArrayAccesses();
            int key = arr[i];
            int j = i - 1;

            int pos = binarySearch(arr, key, 0, j, metrics);

            while (j >= pos) {
                arr[j + 1] = arr[j];
                metrics.incrementArrayAccesses(2);
                metrics.incrementSwaps();
                j--;
            }

            arr[j + 1] = key;
            metrics.incrementArrayAccesses();
        }

        return metrics;
    }

    public static void sort(int[] arr) {
        if (arr == null) throw new IllegalArgumentException("Input array cannot be null");
        sortWithMetrics(arr);
    }

    public static void main(String[] args) {
        int[] arr = {4, 3, 7, 1, 9, 6};
        PerformanceMetrics metrics = sortWithMetrics(arr);
        System.out.print("Sorted array: ");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println("\nMetrics: " + metrics);
    }
}