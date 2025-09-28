package test.java.algoritms;

import main.java.algoritms.BoyerMooreMajority;
import main.java.metrics.PerformanceMetrics;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BoyerMooreMajorityTest {

    @Test
    void testMajorityExists() {
        int[] arr = {2, 2, 1, 2, 3, 2, 2};
        assertEquals(2, BoyerMooreMajority.findMajority(arr));
    }

    @Test
    void testNoMajority() {
        int[] arr = {1, 2, 3, 4};
        assertNull(BoyerMooreMajority.findMajority(arr));
    }

    @Test
    void testSingleElement() {
        int[] arr = {7};
        assertEquals(7, BoyerMooreMajority.findMajority(arr));
    }
    @Test
    void testMajorityExistsWithMetrics() {
        int[] arr = {2, 2, 1, 2, 3, 2, 2};
        PerformanceMetrics metrics = BoyerMooreMajority.findMajorityWithMetrics(arr);
        assertNotNull(metrics);
        assertTrue(metrics.getComparisons() > 0);
        assertTrue(metrics.getArrayAccesses() > 0);
    }

    @Test
    void testNullInput() {
        assertThrows(IllegalArgumentException.class, () -> {
            BoyerMooreMajority.findMajority(null);
        });
    }
}
