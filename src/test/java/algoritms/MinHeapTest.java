package test.java.algoritms;

import main.java.algoritms.MinHeap;
import main.java.metrics.PerformanceMetrics;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MinHeapTest {

    @Test
    void testInsertAndExtract() {
        MinHeap heap = new MinHeap();
        heap.insertWithoutMetrics(10);
        heap.insertWithoutMetrics(5);
        heap.insertWithoutMetrics(20);
        heap.insertWithoutMetrics(2);

        assertEquals(2, heap.extractMinWithoutMetrics());
        assertEquals(5, heap.extractMinWithoutMetrics());
        assertEquals(10, heap.extractMinWithoutMetrics());
        assertEquals(20, heap.extractMinWithoutMetrics());
    }

    @Test
    void testInsertWithMetrics() {
        MinHeap heap = new MinHeap();
        PerformanceMetrics metrics = heap.insert(10);

        assertNotNull(metrics);
        assertTrue(metrics.getComparisons() >= 0);
        assertTrue(metrics.getArrayAccesses() > 0);
    }

    @Test
    void testExtractMinWithMetrics() {
        MinHeap heap = new MinHeap();
        heap.insertWithoutMetrics(10);
        heap.insertWithoutMetrics(5);
        PerformanceMetrics metrics = heap.extractMin();

        assertNotNull(metrics);
        assertEquals(10, heap.extractMinWithoutMetrics());
    }

    @Test
    void testDecreaseKey() {
        MinHeap heap = new MinHeap();
        heap.insertWithoutMetrics(10);
        heap.insertWithoutMetrics(15);
        heap.insertWithoutMetrics(30);

        // Decrease 30 to 1
        PerformanceMetrics metrics = heap.decreaseKey(2, 1);

        assertNotNull(metrics);
        assertEquals(1, heap.extractMinWithoutMetrics());
    }

    @Test
    void testDecreaseKeyWithInvalidIndex() {
        MinHeap heap = new MinHeap();
        heap.insertWithoutMetrics(10);

        assertThrows(IllegalArgumentException.class, () -> {
            heap.decreaseKey(5, 1);
        });
    }

    @Test
    void testMerge() {
        MinHeap h1 = new MinHeap();
        h1.insertWithoutMetrics(5);
        h1.insertWithoutMetrics(20);

        MinHeap h2 = new MinHeap();
        h2.insertWithoutMetrics(3);
        h2.insertWithoutMetrics(15);

        PerformanceMetrics metrics = h1.merge(h2);

        assertNotNull(metrics);
        assertEquals(3, h1.extractMinWithoutMetrics());
        assertEquals(5, h1.extractMinWithoutMetrics());
    }

    @Test
    void testEmptyHeapExtract() {
        MinHeap heap = new MinHeap();

        assertThrows(RuntimeException.class, () -> {
            heap.extractMinWithoutMetrics();
        });
    }

    @Test
    void testHeapOperationsMetrics() {
        MinHeap heap = new MinHeap();
        PerformanceMetrics insertMetrics = heap.insert(10);
        PerformanceMetrics extractMetrics = heap.extractMin();

        assertTrue(insertMetrics.getArrayAccesses() > 0);
        assertTrue(extractMetrics.getComparisons() >= 0);
    }

    @Test
    void testSingleElementHeap() {
        MinHeap heap = new MinHeap();
        heap.insertWithoutMetrics(42);
        assertEquals(42, heap.extractMinWithoutMetrics());
        assertTrue(heap.isEmpty());
    }
}