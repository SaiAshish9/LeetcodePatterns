/*
Input: s = "babad"
Output: "bab"
Explanation: "aba" is also a valid answer.
PreProcess Initialize Expand Update Track

Complexity Analysis:
Time Complexity: O(n)
Space Complexity: O(n)

Dry Run:
s = "babad"
i = 0, len1 = 1, len2 = 0, maxLen = 1, start = 0
i = 1, len1 = 3, len2 = 0, maxLen = 3, start = 0
i = 2, len1 = 1, len2 = 0, maxLen = 3, start = 0
i = 3, len1 = 3, len2 = 0, maxLen = 3, start = 2 start = 0
Result: "bab"
*/

package twoPointers.q5;

public class ManachersAlgorithm {

    private static String preprocess(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            sb.append('#');
            sb.append(s.charAt(i));
        }
        sb.append("#");
        return sb.toString();
    }

    private static String longestPalindrome(String s) {
        String t = preprocess(s);
        int n = t.length();
        int[] P = new int[n];
        int C = 0, R = 0, maxLen = 0,
                centerIndex = 0;

        for (int i = 0; i < n; i++) {
            int mirror = 2 * C - i;

            if (R > i) {
                P[i] = Math.min(R - i, P[mirror]);
            }

            while (i + P[i] + 1 < n && i - P[i] - 1 >= 0 && t.charAt(i + 1 + P[i]) == t.charAt(i - 1 - P[i])) {
                P[i]++;
            }

            if (i + P[i] > R) {
                C = i;
                R = i + P[i];
            }

            if (P[i] > maxLen) {
                maxLen = P[i];
                centerIndex = i;
            }
        }
        int start = (centerIndex - maxLen) / 2;
        return s.substring(start, start + maxLen);
    }

    public static void main(String[] args) {
        String s = "babad";
        System.out.println(longestPalindrome(s));
    }

}
