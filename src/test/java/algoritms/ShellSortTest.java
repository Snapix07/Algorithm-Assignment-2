package test.java.algoritms;

import main.java.algoritms.ShellSort;
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
    void testKnuthSequence() {
        int[] arr = {10, -1, 2, 5, 0};
        ShellSort.sort(arr, "knuth");
        assertArrayEquals(new int[]{-1, 0, 2, 5, 10}, arr);
    }

    @Test
    void testSedgewickSequence() {
        int[] arr = {20, 15, 10, 5};
        ShellSort.sort(arr, "sedgewick");
        assertArrayEquals(new int[]{5, 10, 15, 20}, arr);
    }
}
