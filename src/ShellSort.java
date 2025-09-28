public class ShellSort {
    public static void sort(int[] arr, String sequence) {
        int n = arr.length;
        if (n <= 1) return;
        if ("shell".equalsIgnoreCase(sequence)) {
            for (int gap = n / 2; gap > 0; gap /= 2) {
                insertionGapSort(arr, n, gap);
            }
        } else if ("knuth".equalsIgnoreCase(sequence)) {
            int gap = 1;
            while (gap < n / 3) {
                gap = 3 * gap + 1;
            }
            while (gap > 0) {
                insertionGapSort(arr, n, gap);
                gap /= 3;
            }

        } else if ("sedgewick".equalsIgnoreCase(sequence)) {
            int[] gaps = {701, 301, 132, 57, 23, 10, 4, 1};
            for (int gap : gaps) {
                if (gap < n) {
                    insertionGapSort(arr, n, gap);
                }
            }

        } else {
            throw new IllegalArgumentException("Unknown gap sequence: " + sequence);
        }
    }
    private static void insertionGapSort(int[] arr, int n, int gap) {
        for (int i = gap; i < n; i++) {
            int temp = arr[i];
            int j = i;
            while (j >= gap && arr[j - gap] > temp) {
                arr[j] = arr[j - gap];
                j -= gap;
            }
            arr[j] = temp;
        }
    }
    public static void main(String[] args) {
        int[] arr = {9, 8, 3, 7, 5, 6, 4, 1};
        sort(arr, "sedgewick");

        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}
