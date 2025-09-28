package main.java.cli;

import main.java.algoritms.*;
import main.java.metrics.PerformanceMetrics;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class BenchmarkRunner {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Algorithm Benchmark Runner ===");
        System.out.println("1. Boyer-Moore Majority");
        System.out.println("2. Insertion Sort");
        System.out.println("3. Min Heap");
        System.out.println("4. Shell Sort");
        System.out.print("Choose algorithm (1-4): ");

        int choice = scanner.nextInt();

        System.out.print("Enter array size: ");
        int size = scanner.nextInt();

        System.out.print("Data type (random/sorted/reverse/nearly-sorted): ");
        String dataType = scanner.next();

        runBenchmark(choice, size, dataType);
    }

    private static void runBenchmark(int algorithm, int size, String dataType) {
        int[] array = generateArray(size, dataType);

        System.out.println("\n=== Benchmark Results ===");
        System.out.println("Algorithm: " + getAlgorithmName(algorithm));
        System.out.println("Array size: " + size);
        System.out.println("Data type: " + dataType);

        long startTime = System.nanoTime();
        PerformanceMetrics metrics = null;

        switch (algorithm) {
            case 1:
                metrics = BoyerMooreMajority.findMajorityWithMetrics(array);
                break;
            case 2:
                int[] sortArray = Arrays.copyOf(array, array.length);
                metrics = insertionSort.sortWithMetrics(sortArray);
                break;
            case 3:
                main.java.algoritms.MinHeap heap = new main.java.algoritms.MinHeap();
                for (int num : array) {
                    heap.insert(num);
                }
                // For heap, we'll create a simple metrics object
                metrics = new PerformanceMetrics();
                break;
            case 4:
                int[] shellArray = Arrays.copyOf(array, array.length);
                metrics = main.java.algoritms.ShellSort.sortWithMetrics(shellArray, "shell");
                break;
        }

        long endTime = System.nanoTime();
        double durationMs = (endTime - startTime) / 1_000_000.0;

        System.out.println("Time: " + durationMs + " ms");
        if (metrics != null) {
            System.out.println("Metrics: " + metrics);
        }

        // CSV output
        System.out.println("\nCSV Format:");
        System.out.printf("%s,%d,%s,%.3f",
                getAlgorithmName(algorithm), size, dataType, durationMs);
        if (metrics != null) {
            System.out.printf(",%d,%d,%d%n",
                    metrics.getComparisons(), metrics.getSwaps(), metrics.getArrayAccesses());
        }
    }

    private static String getAlgorithmName(int choice) {
        switch (choice) {
            case 1: return "BoyerMoore";
            case 2: return "InsertionSort";
            case 3: return "MinHeap";
            case 4: return "ShellSort";
            default: return "Unknown";
        }
    }

    private static int[] generateArray(int size, String dataType) {
        Random random = new Random(42);
        int[] array = new int[size];

        switch (dataType.toLowerCase()) {
            case "sorted":
                for (int i = 0; i < size; i++) array[i] = i;
                break;
            case "reverse":
                for (int i = 0; i < size; i++) array[i] = size - i;
                break;
            case "nearly-sorted":
                for (int i = 0; i < size; i++) array[i] = i;
                for (int i = 0; i < size / 10; i++) {
                    int idx = random.nextInt(size - 1);
                    int temp = array[idx];
                    array[idx] = array[idx + 1];
                    array[idx + 1] = temp;
                }
                break;
            case "random":
            default:
                for (int i = 0; i < size; i++) array[i] = random.nextInt(size * 10);
                break;
        }
        return array;
    }
}