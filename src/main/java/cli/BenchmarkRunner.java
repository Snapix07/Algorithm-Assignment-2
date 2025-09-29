package main.java.cli;

import main.java.algoritms.insertionSort;
import main.java.algoritms.SelectionSort;
import main.java.metrics.PerformanceMetrics;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class BenchmarkRunner {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Sorting Algorithms Benchmark Runner ===");
        System.out.println("1. Insertion Sort");
        System.out.println("2. Selection Sort");
        System.out.println("3. Run Comprehensive Benchmark Suite");
        System.out.print("Choose option (1-3): ");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
            case 2:
                runSingleBenchmark(choice);
                break;
            case 3:
                runComprehensiveBenchmark();
                break;
            default:
                System.out.println("Invalid choice!");
        }

        scanner.close();
    }

    private static void runSingleBenchmark(int algorithm) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter array size: ");
        int size = scanner.nextInt();

        System.out.print("Data type (random/sorted/reverse/nearly-sorted/duplicates): ");
        String dataType = scanner.next();

        String algorithmName = (algorithm == 1) ? "InsertionSort" : "SelectionSort";

        System.out.println("\n=== Benchmark Results ===");
        System.out.println("Algorithm: " + algorithmName);
        System.out.println("Array size: " + size);
        System.out.println("Data type: " + dataType);

        int[] array = generateArray(size, dataType);
        int[] arrayCopy = Arrays.copyOf(array, array.length);

        long startTime = System.nanoTime();
        PerformanceMetrics metrics = null;

        if (algorithm == 1) {
            metrics = insertionSort.sortWithMetrics(array);
        } else {
            metrics = SelectionSort.sortWithMetrics(array);
        }

        long endTime = System.nanoTime();
        double durationMs = (endTime - startTime) / 1_000_000.0;

        boolean sortedCorrectly = isSorted(array);

        System.out.println("Time: " + durationMs + " ms");
        System.out.println("Sorted correctly: " + sortedCorrectly);
        System.out.println("Metrics: " + metrics);

        // CSV output
        System.out.println("\nCSV Format:");
        System.out.printf("%s,%d,%s,%.3f,%d,%d,%d%n",
                algorithmName, size, dataType, durationMs,
                metrics.getComparisons(), metrics.getSwaps(), metrics.getArrayAccesses());

        scanner.close();
    }

    private static void runComprehensiveBenchmark() {
        int[] sizes = {100, 1000, 10000};
        String[] dataTypes = {"random", "sorted", "reverse", "nearly-sorted", "duplicates"};
        String[] algorithms = {"InsertionSort", "SelectionSort"};

        System.out.println("Running comprehensive benchmark suite...");
        System.out.println("Algorithm,Size,DataType,Time(ms),Comparisons,Swaps,ArrayAccesses");

        for (String algorithm : algorithms) {
            for (int size : sizes) {
                for (String dataType : dataTypes) {
                    if (size > 10000 && dataType.equals("sorted")) {
                        continue;
                    }
                    benchmarkAlgorithm(algorithm, size, dataType);
                }
            }
        }
    }

    private static void benchmarkAlgorithm(String algorithm, int size, String dataType) {
        int[] array = generateArray(size, dataType);
        int[] arrayCopy = Arrays.copyOf(array, array.length);

        long startTime = System.nanoTime();
        PerformanceMetrics metrics = null;

        if ("InsertionSort".equals(algorithm)) {
            metrics = insertionSort.sortWithMetrics(array);
        } else if ("SelectionSort".equals(algorithm)) {
            metrics = SelectionSort.sortWithMetrics(array);
        }

        long endTime = System.nanoTime();
        double durationMs = (endTime - startTime) / 1_000_000.0;

        System.out.printf("%s,%d,%s,%.3f,%d,%d,%d%n",
                algorithm, size, dataType, durationMs,
                metrics.getComparisons(), metrics.getSwaps(), metrics.getArrayAccesses());
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
                    int idx1 = random.nextInt(size);
                    int idx2 = random.nextInt(size);
                    int temp = array[idx1];
                    array[idx1] = array[idx2];
                    array[idx2] = temp;
                }
                break;
            case "duplicates":
                for (int i = 0; i < size; i++) array[i] = random.nextInt(10);
                break;
            case "random":
            default:
                for (int i = 0; i < size; i++) array[i] = random.nextInt(size * 10);
                break;
        }
        return array;
    }
    private static boolean isSorted(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }
        return true;
    }
}