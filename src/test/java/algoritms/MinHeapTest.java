package test.java.algoritms;

import main.java.algoritms.MinHeap;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MinHeapTest {

    @Test
    void testInsertAndExtract() {
        MinHeap heap = new MinHeap();
        heap.insert(10);
        heap.insert(5);
        heap.insert(20);
        heap.insert(2);

        assertEquals(2, heap.extractMin());
        assertEquals(5, heap.extractMin());
    }

    @Test
    void testDecreaseKey() {
        MinHeap heap = new MinHeap();
        heap.insert(10);
        heap.insert(15);
        heap.insert(30);
        heap.decreaseKey(2, 1);

        assertEquals(1, heap.extractMin());
    }

    @Test
    void testMerge() {
        MinHeap h1 = new MinHeap();
        h1.insert(5);
        h1.insert(20);

        MinHeap h2 = new MinHeap();
        h2.insert(3);
        h2.insert(15);

        h1.merge(h2);

        assertEquals(3, h1.extractMin());
        assertEquals(5, h1.extractMin());
    }
}