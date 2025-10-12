package backtracking.q131;

import java.util.*;

public class PalindromePartitioning {

    private static void dfs(String s, int start, List<String> current, List<List<String>> result) {
        if (start == s.length()) {
            result.add(new ArrayList<>(current));
            return;
        }
        for (int end = start; end < s.length(); end++) {
            String substring = s.substring(start, end + 1);
            if (isPalindrome(substring)) {
                current.add(substring);
                dfs(s, end + 1, current, result);
                current.remove(current.size() - 1);
            }
        }
    }

    private static boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "aab";
        List<List<String>> result = new ArrayList<>();
        dfs(s, 0, new ArrayList<>(), result);
        System.out.println(result); // Output: [["a","a","b"],["aa","b"]]
    }
}

// Complexity Analysis
// Time Complexity: O(N * 2^N), where N is the length of the string. In the worst case, we may have to explore all possible partitions of the string, which is 2^(N-1). For each partition, we may need to check if the substring is a palindrome, which takes O(N) time.
// Space Complexity: O(N), where N is the length of the string. This space is used for the recursion stack and the current partition list.  The result list may also take up to O(N) space in the worst case when all characters are distinct.