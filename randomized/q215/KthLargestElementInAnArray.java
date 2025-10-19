package randomized.q215;

import java.util.Random;

public class KthLargestElementInAnArray {
    private Random rand = new Random();
    
    public int findKthLargest(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, nums.length - k);
    }

    private int quickSelect(int[] nums, int left, int right, int index) {
        if (left == right) return nums[left];
        int pivotIndex = partition(nums, left, right);
        if (pivotIndex == index) return nums[pivotIndex];
        else if (pivotIndex < index) return quickSelect(nums, pivotIndex + 1, right, index);
        else return quickSelect(nums, left, pivotIndex - 1, index);
    }

    private int partition(int[] nums, int left, int right) {
        int pivotIndex = left + rand.nextInt(right - left + 1);
        int pivot = nums[pivotIndex];
        swap(nums, pivotIndex, right);
        int i = left;
        for (int j = left; j < right; j++) {
            if (nums[j] < pivot) {
                swap(nums, i, j);
                i++;
            }
        }
        swap(nums, i, right);
        return i;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        KthLargestElementInAnArray solver = new KthLargestElementInAnArray();
        int[] nums = {3, 2, 1, 5, 6, 4};
        int k = 2;
        int result = solver.findKthLargest(nums, k);
        System.out.println("The " + k + "th largest element is: " + result); // Output: 5
    }
}

// Complexity Analysis
// Time Complexity: O(n) on average, O(n^2) in the worst case.
// Space Complexity: O(1)