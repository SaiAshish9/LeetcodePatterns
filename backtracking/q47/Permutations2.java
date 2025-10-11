package backtracking.q47;

import java.util.*;

class Permutations2 {

    private static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        if (nums == null || nums.length == 0) {
            return res;
        }
        Arrays.sort(nums);
        dfs(nums, res, new ArrayList<>(), visited);
        return res;
    }

    private static void dfs(int[] nums, List<List<Integer>> res, List<Integer> curr, boolean[] visited) {
        if (curr.size() == nums.length) {
            res.add(new ArrayList<>(curr));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }

            if (i != 0 && nums[i] == nums[i - 1] && !visited[i - 1]) {
                continue;
            }

            visited[i] = true;
            curr.add(nums[i]);
            dfs(nums, res, curr, visited);
            curr.remove(curr.size() - 1); // Backtrack
            visited[i] = false;

        }
    }

    public static void main(String[] args) {
        int[] nums = { 1, 1, 2 };
        List<List<Integer>> permutations = permuteUnique(nums);
        System.out.println(permutations); // Output: [[1, 1, 2], [1, 2, 1], [2, 1, 1]]
    }
}

// Complexity Analysis:
// Time Complexity: O(n * n!), where n is the length of the input array.
// This is because there are n! unique permutations and generating each permutation takes O(n) time.
// Space Complexity: O(n) for the recursion stack and the visited array.
// The output list can contain up to n! unique permutations in the worst case.

// Differences from Permutations.java:
// 1. Sorting the Input: The input array is sorted at the beginning to ensure that duplicates are adjacent.
// 2. Duplicate Check: Before adding an element to the current permutation, we check if it is a duplicate of the previous element and if the previous element has been used in the current permutation. 
// If both conditions are true, we skip the current element to avoid generating duplicate permutations.
// 3. Visited Array: A boolean array is used to track which elements have been included in the current permutation, ensuring that each element is used only once per permutation.   
