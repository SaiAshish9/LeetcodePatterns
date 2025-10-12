package backtracking.q77;

import java.util.*;

public class Combinations {

    private static void dfs(List<List<Integer>> result, List<Integer> curr, int n, int k, int start) {
        if (curr.size() == k) {
            result.add(new ArrayList<>(curr));
            return;
        }

        for (int i = start; i <= n; i++) {
            curr.add(i);
            dfs(result, curr, n, k, i + 1);
            curr.remove(curr.size() - 1);
        }
    }

    public static void main(String[] args) {
        int n = 4;
        int k = 2;        
        List<List<Integer>> result = new ArrayList<>();
        dfs(result, new ArrayList<>(), n, k, 1);
        System.out.println(result);
    }
}

// Output: [[1, 2], [1, 3], [1, 4], [2, 3], [2, 4], [3, 4]]
// Complexity: O(n! / (k! * (n - k)!))  time | O(k) space
