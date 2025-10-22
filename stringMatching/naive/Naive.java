package stringMatching.naive;

public class Naive {
    public static int strStr(String haystack, String needle) {
        int n = haystack.length();
        int m = needle.length();

        if (m == 0) {
            return 0; // Empty needle
        }

        for (int i = 0; i <= n - m; i++) {
            int j;
            for (j = 0; j < m; j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    break; // mismatch found
                }
            }
            if (j == m) {
                return i; // match found at index i
            }
        }
        return -1; // no match found
    }

    public static void main(String[] args) {
        String haystack = "abxabcabcaby";
        String needle = "abcaby";
        int result = strStr(haystack, needle);
        System.out.println("First Occurrence Index: " + result);
        // First Occurrence Index: 6 for the given test case, as "abcaby" is found starting at index 6 in "abxabcabcaby".
    }
}

// Time Complexity: O(n*m) where n is haystack length, m is needle length
// Space Complexity: O(1)


