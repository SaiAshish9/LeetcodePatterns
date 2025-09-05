package twoPointers.q75;

import java.util.Arrays;

public class SortColors {

    public static void main(String[] args) {
        int[] nums = { 2, 0, 2, 1, 1, 0 };

        int low = 0, mid = 0, high = nums.length - 1;
        while (low <= high && mid <= high) {
            if (nums[mid] == 0) {
                swap(nums, low, mid);
                low++;
                mid++;
            } else if (nums[mid] == 1) {
                mid++;
            } else {
                swap(nums, mid, high);
                high--;
            }
        }
        System.out.println(Arrays.toString(nums));
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
