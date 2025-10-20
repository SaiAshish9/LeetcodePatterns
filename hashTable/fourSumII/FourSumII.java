package hashTable.fourSumII;

import java.util.*;

public class FourSumII {
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer, Integer> sumMap = new HashMap<>();

        for (int a : nums1) {
            for (int b : nums2) {
                int sum = a + b;
                sumMap.put(sum, sumMap.getOrDefault(sum, 0) + 1);
            }
        }

        int count = 0;

        for (int c : nums3) {
            for (int d : nums4) {
                int target = -(c + d);
                count += sumMap.getOrDefault(target, 0);
            }
        }

        return count;
    }

    public static void main(String[] args) {
        FourSumII solution = new FourSumII();
        int[] nums1 = { 1, 2 };
        int[] nums2 = { -2, -1 };
        int[] nums3 = { -1, 2 };
        int[] nums4 = { 0, 2 };
        int result = solution.fourSumCount(nums1, nums2, nums3, nums4);
        System.out.println("Number of tuples: " + result); // 2
    }
}

// Complexity Analysis
// Time Complexity: O(n^2), where n is the length of each input array. We iterate through all pairs of elements from nums1 and nums2 to populate the sumMap, and
// and then through all pairs from nums3 and nums4 to count the valid tuples.
// Space Complexity: O(n^2) in the worst case, where all possible sums of pairs from nums1 and nums2 are unique and stored in the sumMap.
    