package backtracking.q46;

import java.util.*;

public class Permutations {

    private static void dfs(int[] nums, boolean[] visited, List<Integer> curr, List<List<Integer>> res) {
        if (curr.size() == nums.length) {
            res.add(new ArrayList<>(curr));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                curr.add(nums[i]);
                dfs(nums, visited, curr, res);
                curr.remove(curr.size() - 1);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = { 1, 2, 3 };
        List<List<Integer>> res = new ArrayList<>();
        dfs(nums, new boolean[nums.length], new ArrayList<>(), res);
        System.out.println(res);
        // [[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]]
    }

}

// Complexity Analysis:
// Time Complexity: O(n * n!), where n is the length of the input array.
// This is because there are n! permutations and generating each permutation takes O(n) time.
// Space Complexity: O(n) for the recursion stack and the visited array.
// The output list can contain up to n! permutations in the worst case. 
