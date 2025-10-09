package backtracking.q17;

import java.util.*;

public class LetterCombinationsOfAPhoneNumber {

    private static final Map<Integer, String> map = Map.of(
        0, "",
        1, "",
        2, "abc",
        3, "def",
        4, "ghi",
        5, "jkl",
        6, "mno",
        7, "pqrs",
        8, "tuv",
        9, "wxyz"
    );

    private static void dfs(String s, List<String> result, StringBuilder curr, int index) {
        if (index == s.length()) {
            result.add(curr.toString());
            return;
        }

        int digit = s.charAt(index) - '0';
        String chars = map.get(digit);

        for (int i = 0; i < chars.length(); i++) {
            curr.append(chars.charAt(i));
            dfs(s, result, curr, index + 1);
            curr.deleteCharAt(curr.length() - 1); // backtrack
        }
    }

    public static void main(String[] args) {
        String s = "23";
        List<String> result = new ArrayList<>();
        dfs(s, result, new StringBuilder(), 0);
        System.out.println(result);
    }
}

// Complexity Analysis:
// Time Complexity: O(3^m * 4^n), where m is the number of digits that map to 3 letters (2, 3, 4, 5, 6, 8) and n is the number of digits that map to 4 letters (7, 9).
// Space Complexity: O(m + n) for the recursion stack and the output list.
// The output list can contain up to 3^m * 4^n combinations in the worst case.