package test.java.algoritms;

import main.java.algoritms.ShellSort;
import main.java.metrics.PerformanceMetrics;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ShellSortTest {

    @Test
    void testShellSequence() {
        int[] arr = {9, 8, 3, 7, 5, 6, 4, 1};
        ShellSort.sort(arr, "shell");
        assertArrayEquals(new int[]{1, 3, 4, 5, 6, 7, 8, 9}, arr);
    }

    @Test
    void testShellSequenceWithMetrics() {
        int[] arr = {9, 8, 3, 7, 5, 6, 4, 1};
        PerformanceMetrics metrics = ShellSort.sortWithMetrics(arr, "shell");

        assertArrayEquals(new int[]{1, 3, 4, 5, 6, 7, 8, 9}, arr);
        assertNotNull(metrics);
        assertTrue(metrics.getComparisons() > 0);
        assertTrue(metrics.getSwaps() > 0);
        assertTrue(metrics.getArrayAccesses() > 0);
    }

    @Test
    void testKnuthSequence() {
        int[] arr = {10, -1, 2, 5, 0};
        ShellSort.sort(arr, "knuth");
        assertArrayEquals(new int[]{-1, 0, 2, 5, 10}, arr);
    }

    @Test
    void testKnuthSequenceWithMetrics() {
        int[] arr = {10, -1, 2, 5, 0};
        PerformanceMetrics metrics = ShellSort.sortWithMetrics(arr, "knuth");

        assertArrayEquals(new int[]{-1, 0, 2, 5, 10}, arr);
        assertTrue(metrics.getComparisons() > 0);
    }

    @Test
    void testSedgewickSequence() {
        int[] arr = {20, 15, 10, 5};
        ShellSort.sort(arr, "sedgewick");
        assertArrayEquals(new int[]{5, 10, 15, 20}, arr);
    }

    @Test
    void testSedgewickSequenceWithMetrics() {
        int[] arr = {20, 15, 10, 5};
        PerformanceMetrics metrics = ShellSort.sortWithMetrics(arr, "sedgewick");

        assertArrayEquals(new int[]{5, 10, 15, 20}, arr);
        assertTrue(metrics.getComparisons() > 0);
    }

    @Test
    void testInvalidSequence() {
        int[] arr = {1, 2, 3};
        assertThrows(IllegalArgumentException.class, () -> {
            ShellSort.sort(arr, "invalid_sequence");
        });
    }

    @Test
    void testNullArray() {
        assertThrows(IllegalArgumentException.class, () -> {
            ShellSort.sort(null, "shell");
        });
    }

    @Test
    void testNullSequence() {
        int[] arr = {1, 2, 3};

        assertThrows(IllegalArgumentException.class, () -> {
            ShellSort.sort(arr, null);
        });
    }

    @Test
    void testEmptyArray() {
        int[] arr = {};
        PerformanceMetrics metrics = ShellSort.sortWithMetrics(arr, "shell");

        assertArrayEquals(new int[]{}, arr);
        assertNotNull(metrics);
        assertEquals(0, metrics.getComparisons());
        assertEquals(0, metrics.getSwaps());
    }

    @Test
    void testSingleElement() {
        int[] arr = {5};
        PerformanceMetrics metrics = ShellSort.sortWithMetrics(arr, "shell");

        assertArrayEquals(new int[]{5}, arr);
        assertNotNull(metrics);
    }

    @Test
    void testDifferentInputTypes() {
        int[] sorted = {1, 2, 3, 4, 5};
        PerformanceMetrics sortedMetrics = ShellSort.sortWithMetrics(sorted, "shell");
        int[] reverse = {5, 4, 3, 2, 1};
        PerformanceMetrics reverseMetrics = ShellSort.sortWithMetrics(reverse, "shell");
        int[] duplicates = {3, 1, 2, 1, 3};
        PerformanceMetrics duplicatesMetrics = ShellSort.sortWithMetrics(duplicates, "shell");
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, sorted);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, reverse);
        assertArrayEquals(new int[]{1, 1, 2, 3, 3}, duplicates);
        assertTrue(sortedMetrics.getComparisons() > 0);
        assertTrue(reverseMetrics.getComparisons() > 0);
        assertTrue(duplicatesMetrics.getComparisons() > 0);
    }

    @Test
    void testPerformanceComparisonBetweenSequences() {
        int[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        int[] arrShell = arr.clone();
        int[] arrKnuth = arr.clone();
        int[] arrSedgewick = arr.clone();
        PerformanceMetrics shellMetrics = ShellSort.sortWithMetrics(arrShell, "shell");
        PerformanceMetrics knuthMetrics = ShellSort.sortWithMetrics(arrKnuth, "knuth");
        PerformanceMetrics sedgewickMetrics = ShellSort.sortWithMetrics(arrSedgewick, "sedgewick");
        int[] expected = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        assertArrayEquals(expected, arrShell);
        assertArrayEquals(expected, arrKnuth);
        assertArrayEquals(expected, arrSedgewick);
        assertTrue(shellMetrics.getComparisons() > 0);
        assertTrue(knuthMetrics.getComparisons() > 0);
        assertTrue(sedgewickMetrics.getComparisons() > 0);
    }
}