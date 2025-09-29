package test.java.algoritms;

import main.java.algoritms.SelectionSort;
import main.java.metrics.PerformanceMetrics;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SelectionSortTest {

    @Test
    void testEmptyArray() {
        int[] arr = {};
        PerformanceMetrics metrics = SelectionSort.sortWithMetrics(arr);
        assertArrayEquals(new int[]{}, arr);
        assertEquals(0, metrics.getComparisons());
    }

    @Test
    void testSingleElement() {
        int[] arr = {42};
        PerformanceMetrics metrics = SelectionSort.sortWithMetrics(arr);
        assertArrayEquals(new int[]{42}, arr);
    }

    @Test
    void testDuplicates() {
        int[] arr = {3, 1, 3, 1};
        SelectionSort.sort(arr);
        assertArrayEquals(new int[]{1, 1, 3, 3}, arr);
    }

    @Test
    void testAlreadySorted() {
        int[] arr = {1, 2, 3};
        SelectionSort.sort(arr);
        assertArrayEquals(new int[]{1, 2, 3}, arr);
    }

    @Test
    void testReverseSorted() {
        int[] arr = {5, 4, 3, 2, 1};
        SelectionSort.sort(arr);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);
    }

    @Test
    void testBasicSort() {
        int[] arr = {64, 25, 12, 22, 11};
        SelectionSort.sort(arr);
        assertArrayEquals(new int[]{11, 12, 22, 25, 64}, arr);
    }

    @Test
    void testNullArray() {
        assertThrows(IllegalArgumentException.class, () -> {
            SelectionSort.sort(null);
        });
    }

}