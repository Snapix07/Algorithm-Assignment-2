package main.java.algoritms;

import main.java.metrics.PerformanceMetrics;

public class ShellSort {

    public static PerformanceMetrics sortWithMetrics(int[] arr, String sequence) {
        if (arr == null) {
            throw new IllegalArgumentException("Input array cannot be null");
        }
        if (sequence == null) {
            throw new IllegalArgumentException("Sequence cannot be null");
        }

        PerformanceMetrics metrics = new PerformanceMetrics();
        int n = arr.length;

        if (n <= 1) return metrics;

        int[] gaps;
        switch (sequence.toLowerCase()) {
            case "shell":
                gaps = generateShellSequence(n);
                break;
            case "knuth":
                gaps = generateKnuthSequence(n);
                break;
            case "sedgewick":
                gaps = generateSedgewickSequence(n);
                break;
            default:
                throw new IllegalArgumentException("Unknown gap sequence: " + sequence);
        }

        for (int gap : gaps) {
            if (gap >= n) continue;
            insertionGapSort(arr, n, gap, metrics);
        }

        return metrics;
    }

    private static int[] generateShellSequence(int n) {
        // Original Shell sequence: n/2, n/4, ..., 1
        int count = (int) (Math.log(n) / Math.log(2));
        int[] gaps = new int[count];
        for (int i = 0; i < count; i++) {
            gaps[i] = n / 2;
            n /= 2;
        }
        return gaps;
    }

    private static int[] generateKnuthSequence(int n) {
        // Knuth sequence: (3^k - 1)/2
        int count = 0;
        int gap = 1;
        while (gap < n) {
            count++;
            gap = gap * 3 + 1;
        }

        int[] gaps = new int[count];
        gap = 1;
        for (int i = 0; i < count; i++) {
            gaps[i] = gap;
            gap = gap * 3 + 1;
        }

        // Reverse to descending order
        for (int i = 0; i < count / 2; i++) {
            int temp = gaps[i];
            gaps[i] = gaps[count - 1 - i];
            gaps[count - 1 - i] = temp;
        }
        return gaps;
    }

    private static int[] generateSedgewickSequence(int n) {
        // Sedgewick sequence
        int[] sedgewickGaps = {1, 5, 19, 41, 109, 209, 505, 929, 2161, 3905, 8929, 16001, 36289, 64769, 146305, 260609};
        int count = 0;
        for (int gap : sedgewickGaps) {
            if (gap < n) count++;
            else break;
        }

        int[] gaps = new int[count];
        for (int i = 0; i < count; i++) {
            gaps[i] = sedgewickGaps[count - 1 - i];
        }
        return gaps;
    }

    private static void insertionGapSort(int[] arr, int n, int gap, PerformanceMetrics metrics) {
        for (int i = gap; i < n; i++) {
            metrics.incrementArrayAccesses();
            int temp = arr[i];
            int j = i;

            while (j >= gap) {
                metrics.incrementComparisons();
                metrics.incrementArrayAccesses();
                if (arr[j - gap] > temp) {
                    metrics.incrementArrayAccesses();
                    arr[j] = arr[j - gap];
                    metrics.incrementSwaps();
                    j -= gap;
                } else {
                    break;
                }
            }
            metrics.incrementArrayAccesses();
            arr[j] = temp;
        }
    }

    public static void sort(int[] arr, String sequence) {
        if (arr == null || sequence == null) {
            throw new IllegalArgumentException("Input parameters cannot be null");
        }
        sortWithMetrics(arr, sequence);
    }

    public static void main(String[] args) {
        int[] arr = {9, 8, 3, 7, 5, 6, 4, 1};
        PerformanceMetrics metrics = sortWithMetrics(arr, "sedgewick");

        System.out.print("Sorted array: ");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println("\nMetrics: " + metrics);
    }
}