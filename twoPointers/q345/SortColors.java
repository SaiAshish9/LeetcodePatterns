package twoPointers.q345;

public class SortColors {

    public static void moveZeroes(int[] nums) {
        int low = 0, mid = 0, high = nums.length - 1;

        while (low <= mid && mid <= high) {
            if (nums[low] == 0) {
                swap(nums, low, mid);
                low++;
                mid++;
            } else if (nums[mid] == 0) {
                mid++;
            } else {
                swap(nums, mid, high);
                high--;
            }
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = { 0, 1, 0, 3, 12 };
        moveZeroes(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }

}
