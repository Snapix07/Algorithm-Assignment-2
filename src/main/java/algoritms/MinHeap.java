package main.java.algoritms;

import main.java.metrics.PerformanceMetrics;
import java.util.ArrayList;
import java.util.List;

public class MinHeap {
    private List<Integer> heap;

    public MinHeap() {
        heap = new ArrayList<>();
    }

    public MinHeap(List<Integer> elements) {
        heap = new ArrayList<>(elements);
        buildHeap();
    }

    private int parent(int i) { return (i - 1) / 2; }
    private int left(int i) { return 2 * i + 1; }
    private int right(int i) { return 2 * i + 2; }

    private void buildHeap() {
        for (int i = heap.size() / 2 - 1; i >= 0; i--) {
            heapify(i);
        }
    }

    public PerformanceMetrics insert(int key) {
        PerformanceMetrics metrics = new PerformanceMetrics();
        metrics.incrementMemoryAllocations(4); // int allocation

        heap.add(key);
        metrics.incrementArrayAccesses();

        int i = heap.size() - 1;
        while (i > 0) {
            metrics.incrementComparisons();
            metrics.incrementArrayAccesses(2);
            int parent = parent(i);
            if (heap.get(parent) > heap.get(i)) {
                swap(i, parent, metrics);
                i = parent;
            } else {
                break;
            }
        }
        return metrics;
    }

    public PerformanceMetrics extractMin() {
        PerformanceMetrics metrics = new PerformanceMetrics();

        if (heap.isEmpty()) {
            throw new RuntimeException("Heap is empty");
        }

        if (heap.size() == 1) {
            heap.remove(0);
            metrics.incrementArrayAccesses();
            return metrics;
        }

        int root = heap.get(0);
        metrics.incrementArrayAccesses();

        int last = heap.remove(heap.size() - 1);
        metrics.incrementArrayAccesses();

        heap.set(0, last);
        metrics.incrementArrayAccesses();

        heapify(0, metrics);
        return metrics;
    }

    public int getMin() {
        if (heap.isEmpty()) {
            throw new RuntimeException("Heap is empty");
        }
        return heap.get(0);
    }

    public PerformanceMetrics decreaseKey(int i, int newVal) {
        PerformanceMetrics metrics = new PerformanceMetrics();

        if (i < 0 || i >= heap.size()) {
            throw new IllegalArgumentException("Invalid index");
        }

        if (newVal > heap.get(i)) {
            throw new IllegalArgumentException("New value is greater than current value");
        }

        heap.set(i, newVal);
        metrics.incrementArrayAccesses();

        while (i > 0) {
            metrics.incrementComparisons();
            metrics.incrementArrayAccesses(2);
            int parent = parent(i);
            if (heap.get(parent) > heap.get(i)) {
                swap(i, parent, metrics);
                i = parent;
            } else {
                break;
            }
        }
        return metrics;
    }

    public PerformanceMetrics merge(MinHeap other) {
        PerformanceMetrics metrics = new PerformanceMetrics();
        metrics.incrementMemoryAllocations(other.heap.size() * 4);

        for (int val : other.heap) {
            insert(val);
        }
        return metrics;
    }

    private void heapify(int i) {
        heapify(i, new PerformanceMetrics());
    }

    private void heapify(int i, PerformanceMetrics metrics) {
        int smallest = i;
        int left = left(i);
        int right = right(i);

        if (left < heap.size()) {
            metrics.incrementComparisons();
            metrics.incrementArrayAccesses(2);
            if (heap.get(left) < heap.get(smallest)) {
                smallest = left;
            }
        }

        if (right < heap.size()) {
            metrics.incrementComparisons();
            metrics.incrementArrayAccesses(2);
            if (heap.get(right) < heap.get(smallest)) {
                smallest = right;
            }
        }

        if (smallest != i) {
            swap(i, smallest, metrics);
            heapify(smallest, metrics);
        }
    }

    private void swap(int i, int j, PerformanceMetrics metrics) {
        metrics.incrementArrayAccesses(4); // 2 reads + 2 writes
        metrics.incrementSwaps();
        int temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public int size() {
        return heap.size();
    }

    // Methods for testing without metrics
    public void insertWithoutMetrics(int key) {
        insert(key);
    }

    public int extractMinWithoutMetrics() {
        if (heap.isEmpty()) throw new RuntimeException("Heap is empty");
        int min = getMin();
        extractMin();
        return min;
    }

    public void printHeap() {
        System.out.println(heap);
    }

    public static void main(String[] args) {
        MinHeap h1 = new MinHeap();
        h1.insert(10);
        h1.insert(5);
        h1.insert(20);
        h1.insert(2);
        h1.printHeap();

        PerformanceMetrics metrics = h1.extractMin();
        System.out.println("Extract min metrics: " + metrics);
        h1.printHeap();
    }
}