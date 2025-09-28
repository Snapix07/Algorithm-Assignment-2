package main.java.algoritms;
public class insertionSort {
    private static int binarySearch(int[] arr, int key, int low, int high) {
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] == key) {
                return mid + 1;
            } else if (arr[mid] < key) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }
    public static void sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            int pos = binarySearch(arr, key, 0, j);
            while (j >= pos) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }
    public static void main(String[] args) {
        int[] arr = {4, 3, 7, 1, 9, 6};
        sort(arr);
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}

