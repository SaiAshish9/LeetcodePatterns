package backtracking.q40;

import java.util.*;

public class CombinationSum2 {

    private static void dfs(int[] nums, int target, int start, List<Integer> curr, List<List<Integer>> res) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            res.add(new ArrayList<>(curr));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            if (i == start || (i > 0 && nums[i] != nums[i - 1])) {
                curr.add(nums[i]);
                dfs(nums, target - nums[i], i + 1, curr, res);
                curr.remove(curr.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = { 2, 3, 6, 7 };
        int target = 7;
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        dfs(nums, target, 0, new ArrayList<>(), res);
        System.out.println(res);
        // [[2, 2, 3], [7]]
    }
}

// Complexity Analysis:
// Time Complexity: O(2^(t/m)), where t is the target and m is the minimum number in candidates.
// This is because the maximum depth of the recursion tree can be t/m and each node can have at most 2 children (include or exclude the current number).
// Space Complexity: O(t/m) for the recursion stack.
// The output list can contain up to O(t/m) combinations in the worst case. 
