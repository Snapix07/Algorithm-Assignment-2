package main.java.algoritms;

import java.util.ArrayList;

public class MinHeap {
    private ArrayList<Integer> heap;

    public MinHeap() {
        heap = new ArrayList<>();
    }
    private int parent(int i) { return (i - 1) / 2; }
    private int left(int i) { return 2 * i + 1; }
    private int right(int i) { return 2 * i + 2; }
    public void insert(int key) {
        heap.add(key);
        int i = heap.size() - 1;
        while (i > 0 && heap.get(parent(i)) > heap.get(i)) {
            swap(i, parent(i));
            i = parent(i);
        }
    }
    public int extractMin() {
        if (heap.isEmpty()) throw new RuntimeException("Heap is empty");

        int root = heap.get(0);
        int last = heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) {
            heap.set(0, last);
            heapify(0);
        }
        return root;
    }
    public void decreaseKey(int i, int newVal) {
        heap.set(i, newVal);
        while (i > 0 && heap.get(parent(i)) > heap.get(i)) {
            swap(i, parent(i));
            i = parent(i);
        }
    }
    public void merge(MinHeap other) {
        for (int val : other.heap) {
            insert(val);
        }
    }

    private void heapify(int i) {
        int smallest = i;
        int l = left(i);
        int r = right(i);

        if (l < heap.size() && heap.get(l) < heap.get(smallest)) {
            smallest = l;
        }
        if (r < heap.size() && heap.get(r) < heap.get(smallest)) {
            smallest = r;
        }
        if (smallest != i) {
            swap(i, smallest);
            heapify(smallest);
        }
    }

    private void swap(int i, int j) {
        int tmp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, tmp);
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
        System.out.println("Extract min: " + h1.extractMin());
        h1.printHeap();
        h1.decreaseKey(1, 1);
        h1.printHeap();
        MinHeap h2 = new MinHeap();
        h2.insert(3);
        h2.insert(15);
        h1.merge(h2);
        h1.printHeap();
    }
}

