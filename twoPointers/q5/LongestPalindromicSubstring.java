/*
Input: s = "babad"
Output: "bab"
Explanation: "aba" is also a valid answer.

Brute Force Approach: Check all substrings
- O(n^3) time complexity
- O(1) space complexity

Optimized Approach: Expand Around Center

Complexity Analysis:
- O(n^2) time complexity
- O(1) space complexity

Most Optimal Approach: Manacher's Algorithm
- O(n) time complexity
- O(n) space complexity

Dry Run:
s = "babad"
i = 0, len1 = 1, len2 = 0, maxLen = 1, start = 0
i = 1, len1 = 3, len2 = 0, maxLen = 3, start = 0
i = 2, len1 = 1, len2 = 0, maxLen = 3, start = 0
i = 3, len1 = 3, len2 = 0, maxLen = 3, start = 2 start = 0
Result: "bab"
*/

package twoPointers.q5;

public class LongestPalindromicSubstring {

    public static void main(String[] args) {
        System.out.println(longestPalindromicString("babad")); // bab
    }

    private static String longestPalindromicString(String s) {
        if (s == null || s.length() < 1)
            return "";
        int start = 0, maxLen = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > maxLen) {
                maxLen = len;
                start = i - (len - 1) / 2;
            }
        }
        return s.substring(start, start + maxLen);
    }

    private static int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return (right - 1) - (left + 1) + 1;
    }

}
