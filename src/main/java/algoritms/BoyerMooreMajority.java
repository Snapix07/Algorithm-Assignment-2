package main.java.algoritms;

import main.java.metrics.PerformanceMetrics;

public class BoyerMooreMajority {

    public static PerformanceMetrics findMajorityWithMetrics(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Input array cannot be null");
        }

        PerformanceMetrics metrics = new PerformanceMetrics();
        metrics.incrementMemoryAllocations(8 + 8);

        if (nums.length == 0) {
            return metrics;
        }

        int candidate = 0;
        int count = 0;
        for (int num : nums) {
            metrics.incrementArrayAccesses();
            metrics.incrementComparisons();

            if (count == 0) {
                candidate = num;
                count = 1;
            } else {
                metrics.incrementComparisons();
                if (num == candidate) {
                    count++;
                } else {
                    count--;
                }
            }
        }
        count = 0;
        for (int num : nums) {
            metrics.incrementArrayAccesses();
            metrics.incrementComparisons();
            if (num == candidate) {
                count++;
            }
        }

        return metrics;
    }

    public static Integer findMajority(int[] nums) {
        if (nums == null) throw new IllegalArgumentException("Input array cannot be null");
        if (nums.length == 0) return null;

        int candidate = 0;
        int count = 0;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
                count = 1;
            } else if (num == candidate) {
                count++;
            } else {
                count--;
            }
        }
        count = 0;
        for (int num : nums) {
            if (num == candidate) count++;
        }

        return (count > nums.length / 2) ? candidate : null;
    }

    public static void main(String[] args) {
        int[] arr = {2, 2, 1, 2, 3, 2, 2};
        PerformanceMetrics metrics = findMajorityWithMetrics(arr);
        Integer majority = findMajority(arr);

        if (majority != null) {
            System.out.println("Majority element: " + majority);
        } else {
            System.out.println("No majority element found.");
        }
        System.out.println("Metrics: " + metrics);
    }
}