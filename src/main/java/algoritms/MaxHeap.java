package main.java.algoritms;

import main.java.metrics.PerformanceMetrics;
import java.util.Arrays;

public class MaxHeap {
    private int[] heap;
    private int size;
    private PerformanceMetrics metrics;

    public MaxHeap(int capacity, PerformanceMetrics metrics) {
        this.heap = new int[capacity];
        this.size = 0;
        this.metrics = metrics;
        metrics.incrementMemoryAllocations(capacity * Integer.BYTES);
    }

    public int getSize() { return size; }

    private int parent(int i) { return (i - 1) / 2; }
    private int left(int i)   { return 2 * i + 1; }
    private int right(int i)  { return 2 * i + 2; }


    public void insert(int key) {
        if (size == heap.length) expand();
        heap[size] = key;
        metrics.incrementArrayAccesses();
        size++;
        siftUp(size - 1);
    }


    public void increaseKey(int i, int newKey) {
        metrics.incrementComparisons();
        metrics.incrementArrayAccesses(2);
        if (newKey < heap[i])
            throw new IllegalArgumentException("New key is smaller");
        heap[i] = newKey;
        metrics.incrementArrayAccesses();
        siftUp(i);
    }


    public int extractMax() {
        if (size <= 0) throw new IllegalStateException("Heap empty");
        if (size == 1) {
            size--;
            metrics.incrementArrayAccesses();
            return heap[0];
        }
        int root = heap[0];
        heap[0] = heap[size - 1];
        metrics.incrementArrayAccesses(3);
        size--;
        heapify(0);
        return root;
    }


    private void heapify(int i) {
        int largest = i;
        int l = left(i);
        int r = right(i);

        if (l < size) {
            metrics.incrementComparisons();
            metrics.incrementArrayAccesses(2);
            if (heap[l] > heap[largest]) largest = l;
        }

        if (r < size) {
            metrics.incrementComparisons();
            metrics.incrementArrayAccesses(2);
            if (heap[r] > heap[largest]) largest = r;
        }

        if (largest != i) {
            swap(i, largest);
            heapify(largest);
        }
    }


    private void siftUp(int i) {
        while (i > 0) {
            metrics.incrementComparisons();
            metrics.incrementArrayAccesses(2);
            if (heap[parent(i)] < heap[i]) {
                swap(i, parent(i));
                i = parent(i);
            } else break;
        }
    }

    private void swap(int i, int j) {
        metrics.incrementSwaps();
        metrics.incrementArrayAccesses(4);
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }


    private void expand() {
        heap = Arrays.copyOf(heap, heap.length * 2);
        metrics.incrementMemoryAllocations(heap.length * Integer.BYTES);
    }


    public void buildHeap(int[] arr) {
        heap = Arrays.copyOf(arr, arr.length);
        size = arr.length;
        metrics.incrementMemoryAllocations(size * Integer.BYTES);
        for (int i = size / 2 - 1; i >= 0; i--) heapify(i);
    }


    public int[] toArray() {
        return Arrays.copyOf(heap, size);
    }
}
