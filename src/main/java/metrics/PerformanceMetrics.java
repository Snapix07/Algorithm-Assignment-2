package main.java.metrics;

public class PerformanceMetrics {
    private long comparisons;
    private long swaps;
    private long arrayAccesses;
    private long memoryAllocations;

    public PerformanceMetrics() {
        this.comparisons = 0;
        this.swaps = 0;
        this.arrayAccesses = 0;
        this.memoryAllocations = 0;
    }

    public void incrementComparisons() { comparisons++; }
    public void incrementComparisons(int count) { comparisons += count; }
    public void incrementSwaps() { swaps++; }
    public void incrementSwaps(int count) { swaps += count; }
    public void incrementArrayAccesses() { arrayAccesses++; }
    public void incrementArrayAccesses(int count) { arrayAccesses += count; }
    public void incrementMemoryAllocations(long bytes) { memoryAllocations += bytes; }

    public long getComparisons() { return comparisons; }
    public long getSwaps() { return swaps; }
    public long getArrayAccesses() { return arrayAccesses; }
    public long getMemoryAllocations() { return memoryAllocations; }

    public void reset() {
        comparisons = 0;
        swaps = 0;
        arrayAccesses = 0;
        memoryAllocations = 0;
    }

    @Override
    public String toString() {
        return String.format("Comparisons: %d, Swaps: %d, Array Accesses: %d, Memory: %d bytes",
                comparisons, swaps, arrayAccesses, memoryAllocations);
    }
}
