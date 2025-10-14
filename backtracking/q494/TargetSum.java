package backtracking.q494;

import java.util.*;

public class TargetSum {

    private static int findTargetSumWays(int[] nums, int target) {
        return dfs(nums, 0, 0, target, new HashMap<>());
    }

    private static int dfs(int[] nums, int index, int currentSum, int target, Map<String, Integer> memo) {
        if (index == nums.length) {
            return currentSum == target ? 1 : 0;
        }
        String key = index + "," + currentSum;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        int add = dfs(nums, index + 1, currentSum + nums[index], target, memo);
        int subtract = dfs(nums, index + 1, currentSum - nums[index], target, memo);
        memo.put(key, add + subtract);
        return add + subtract;
    }

    public static void main(String[] args) {
        int[] nums = { 1, 1, 1, 1, 1 };
        int target = 3;
        System.out.println(findTargetSumWays(nums, target)); // Output: 5
    }

}

// Complexity Analysis
// Time Complexity: O(n * m) where n is the number of elements in nums and m is the range of possible sums.
// Space Complexity: O(n * m) for the memoization map and recursion stack.
