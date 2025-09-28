package main.java.algoritms;

public class BoyerMooreMajority {

    public static Integer findMajority(int[] nums) {
        int candidate = 0;
        int count = 0;
        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }
        count = 0;
        for (int num : nums) {
            if (num == candidate) {
                count++;
            }
        }

        return (count > nums.length / 2) ? candidate : null;
    }

    public static void main(String[] args) {
        int[] arr = {2, 2, 1, 2, 3, 2, 2};
        Integer majority = findMajority(arr);
        if (majority != null) {
            System.out.println("Majority element: " + majority);
        } else {
            System.out.println("No majority element found.");
        }
    }
}
