package backtracking.q78;

import java.util.ArrayList;
import java.util.List;

public class Subsets {

    private static void dfs(List<List<Integer>> result, List<Integer> current, int[] nums, int start) {
        result.add(new ArrayList<>(current));
        for (int i = start; i < nums.length; i++) {
            current.add(nums[i]);
            dfs(result, current, nums, i + 1);
            current.remove(current.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] nums = { 1, 2, 3 };
        List<List<Integer>> result = new ArrayList<>();
        dfs(result, new ArrayList<>(), nums, 0);
        System.out.println(result);
    }
    // Output: [[], [1], [1, 2], [1, 2, 3], [1, 3], [2], [2, 3], [3]]
    // Complexity: O(n * 2^n) time | O(n) space
}
