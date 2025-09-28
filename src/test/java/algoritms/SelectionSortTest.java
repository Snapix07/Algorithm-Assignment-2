package test.java.algoritms;

import main.java.algoritms.SelectionSort;
import main.java.metrics.PerformanceMetrics;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SelectionSortTest {

    @Test
    void testEmptyArray() {
        int[] arr = {};
        PerformanceMetrics metrics = new PerformanceMetrics();
        SelectionSort.sort(arr, metrics);
        assertArrayEquals(new int[]{}, arr);
        System.out.println(metrics);
    }

    @Test
    void testSingleElement() {
        int[] arr = {42};
        PerformanceMetrics metrics = new PerformanceMetrics();
        SelectionSort.sort(arr, metrics);
        assertArrayEquals(new int[]{42}, arr);
    }

    @Test
    void testDuplicates() {
        int[] arr = {3, 1, 3, 1};
        PerformanceMetrics metrics = new PerformanceMetrics();
        SelectionSort.sort(arr, metrics);
        assertArrayEquals(new int[]{1, 1, 3, 3}, arr);
    }

    @Test
    void testAlreadySorted() {
        int[] arr = {1, 2, 3};
        PerformanceMetrics metrics = new PerformanceMetrics();
        SelectionSort.sort(arr, metrics);
        assertArrayEquals(new int[]{1, 2, 3}, arr);
    }

    @Test
    void testReverseSorted() {
        int[] arr = {5, 4, 3, 2, 1};
        PerformanceMetrics metrics = new PerformanceMetrics();
        SelectionSort.sort(arr, metrics);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);
    }
}
