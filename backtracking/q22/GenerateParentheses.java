package backtracking.q22;

import java.util.*;

public class GenerateParentheses {

    private static void dfs(String curr, int left, int right, List<String> result) {
        if (left == 0 && right == 0) {
            result.add(curr);
            return;
        }

        if (left > 0) {
            dfs(curr + "(", left - 1, right, result);
        }

        if (left < right) {
            dfs(curr + ")", left, right - 1, result);
        }
    }

    public static void main(String[] args) {
        int n = 3;
        List<String> result = new ArrayList<>();
        dfs("", n, n, result);
        System.out.println(result);
    }
}

// Complexity Analysis:
// Time Complexity: O(4^n / sqrt(n)), which is the nth Catalan number. This is because the number of valid parentheses combinations is given by the nth Catalan number.
// Space Complexity: O(n) for the recursion stack and the output list.
// The output list can contain up to O(4^n / sqrt(n)) combinations in the worst case.