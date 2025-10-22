package rollingHash.strStr;

import java.util.*;

public class StrStr {
    private static final int BASE = 256; // ASCII characters
    private static final int MOD = 1000000007; // A large prime for modulo

    public static List<Integer> findNeedle(String haystack, String needle) {
        List<Integer> result = new ArrayList<>();
        int n = haystack.length();
        int m = needle.length();
        if (m == 0 || n < m) return result;
        
        int needleHash = 0; // Hash of needle
        int windowHash = 0; // Hash of current window
        int highestPower = 1; // BASE^(m-1)
        
        for (int i = 0; i < m - 1; i++) {
            highestPower = (highestPower * BASE) % MOD;
        }
        
        for (int i = 0; i < m; i++) {
            needleHash = (needleHash * BASE + needle.charAt(i)) % MOD;
            windowHash = (windowHash * BASE + haystack.charAt(i)) % MOD;
        }
        
        for (int i = 0; i <= n - m; i++) {
            if (needleHash == windowHash) {
                if (haystack.substring(i, i + m).equals(needle)) {
                    result.add(i);
                }
            }
            if (i < n - m) {
                windowHash = (BASE * (windowHash - haystack.charAt(i) * highestPower) + haystack.charAt(i + m)) % MOD;
                if (windowHash < 0) {
                    windowHash += MOD;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String haystack = "ababcabcababc";
        String needle = "abc";
        List<Integer> indices = findNeedle(haystack, needle);
        System.out.println("Needle found at indices: " + indices);
        // Output should be: Needle found at indices: [2, 5, 9]
    }
}

// Complexity Analysis:
// Time Complexity: O(n + m) on average, where n is the length of the haystack and m
// is the length of the needle.
// Space Complexity: O(1) additional space, not counting the output list.