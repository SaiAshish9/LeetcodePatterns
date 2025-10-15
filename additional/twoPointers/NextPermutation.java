package additional.twoPointers;

public class NextPermutation {
    private static void nextPermutation(int[] nums) {
        int n = nums.length;
        int i = n - 2;
        
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        
        if (i >= 0) {
            int j = n - 1;
            while (nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }
        
        reverse(nums, i + 1, n - 1);
    }
    
    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    private static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        nextPermutation(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }
        // Expected output: 1 3 2
    }
}

// Complexity Analysis:
// Time Complexity: O(n) where n is the length of the array.
// Space Complexity: O(1) as we are modifying the array in place.