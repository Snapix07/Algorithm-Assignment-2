package test.java.algoritms;
import main.java.algoritms.insertionSort;
import org.junit.jupiter.api.Test;

import static main.java.algoritms.insertionSort.sort;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
class InsertionSortTest {

    @Test
    void testSortBasic() {
        int[] arr = {4, 3, 7, 1, 9, 6};
        insertionSort.sort(arr);
        assertArrayEquals(new int[]{1, 3, 4, 6, 7, 9}, arr);
    }

    @Test
    void testEmptyArray() {
        int[] arr = {};
        insertionSort.sort(arr);
        assertArrayEquals(new int[]{}, arr);
    }

    @Test
    void testSingleElement() {
        int[] arr = {5};
        insertionSort.sort(arr);
        assertArrayEquals(new int[]{5}, arr);
    }

    @Test
    void testSortedAlready() {
        int[] arr = {1, 2, 3, 4, 5};
        insertionSort.sort(arr);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);
    }

    @Test
    void testReverseArray() {
        int[] arr = {5, 4, 3, 2, 1};
        insertionSort.sort(arr);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);
    }
}
