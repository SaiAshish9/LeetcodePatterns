package rollingHash.implementation;

import java.util.*;

public class RollingHash {
    private static final int BASE = 256;
    private static final int MOD = 101;

    public static List<Integer> findPattern(String text, String pattern) {
        List<Integer> result = new ArrayList<>();
        int n = text.length();
        int m = pattern.length();
        if (n < m) {
            return result;
        }
        int patternHash = 0;
        int windowHash = 0;
        int highestPower = 1; // base^(m-1)

        // Precompute (base^(m-1)) % mod
        for (int i = 0; i < m - 1; i++) {
            highestPower = (highestPower * BASE) % MOD;
        }

        for (int i = 0; i < m; i++) {
            patternHash = (patternHash * BASE + pattern.charAt(i)) % MOD;
            windowHash = (windowHash * BASE + text.charAt(i)) % MOD;
        }

        for (int i = 0; i <= n - m; i++) {
            if (patternHash == windowHash) {
                if (text.substring(i, i + m).equals(pattern)) {
                    result.add(i);
                }
            }
            if (i < n - m) {
                windowHash = (BASE * (windowHash - text.charAt(i) * highestPower) + text.charAt(i + m)) % MOD;
                if (windowHash < 0) {
                    windowHash += MOD;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String text = "ababcabcababc";
        String pattern = "abc";
        List<Integer> indices = findPattern(text, pattern);
        System.out.println("Pattern found at indices: " + indices);
        // Output should be: Pattern found at indices: [2, 5, 9]
    }
}

// Complexity Analysis:
// Time Complexity: O(n + m) on average, where n is the length of the text and m
// is the length of the pattern.
// Space Complexity: O(1) additional space, not counting the output list.