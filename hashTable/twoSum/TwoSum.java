package hashTable.twoSum;

import java.util.*;

public class TwoSum {
    private static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i }; 
            }
            map.put(nums[i], i); 
        }
        throw new IllegalArgumentException("No two sum solution found");
    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;

        int[] result = twoSum(nums, target);
        System.out.println("Indices: " + result[0] + ", " + result[1]); // Output: Indices: 0, 1
    }
}

// Complexity Analysis
// Time Complexity: O(n), where n is the number of elements in the array. We
// traverse the array once, and each lookup and insertion in the hash map takes
// O(1) time on average.