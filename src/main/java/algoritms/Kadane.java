package main.java.algoritms;

import main.java.metrics.PerformanceMetrics;

public class Kadane {

    public static int maxSubArray(int[] arr, PerformanceMetrics metrics) {
        int maxSoFar = arr[0], maxEndingHere = arr[0];
        metrics.incrementArrayAccesses();

        for (int i = 1; i < arr.length; i++) {
            metrics.incrementComparisons();
            metrics.incrementArrayAccesses(2);
            maxEndingHere = Math.max(arr[i], maxEndingHere + arr[i]);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }
        return maxSoFar;
    }
}
