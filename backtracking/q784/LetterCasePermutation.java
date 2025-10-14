package backtracking.q784;

import java.util.*;

public class LetterCasePermutation {
    private static List<String> letterCasePermutation(String S) {
        List<String> ans = new ArrayList<>();
        dfs(new StringBuilder(S), 0, ans);
        return ans;
    }

    private static void dfs(StringBuilder sb, int i, List<String> ans) {
        if (i == sb.length()) {
            ans.add(sb.toString());
            return;
        }
        if (Character.isDigit(sb.charAt(i))) {
            dfs(sb, i + 1, ans);
            return;
        }
        sb.setCharAt(i, Character.toLowerCase(sb.charAt(i)));
        dfs(sb, i + 1, ans);
        sb.setCharAt(i, Character.toUpperCase(sb.charAt(i)));
        dfs(sb, i + 1, ans);
    }

    public static void main(String[] args) {
        String S = "a1b2";
        System.out.println(letterCasePermutation(S)); // Output: ["a1b2", "a1B2", "A1b2", "A1B2"]
    }
}

// Time Complexity: O(2^N) where N is the number of letters in the input string S. Each letter can be either lowercase or uppercase, leading to 2^N combinations.
// Space Complexity: O(N) for the recursion stack and the space used to store the current permutation in the StringBuilder. The output list also takes O(2^N) space in the worst case.