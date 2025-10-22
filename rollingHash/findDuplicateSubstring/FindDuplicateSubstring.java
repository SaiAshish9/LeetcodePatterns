package rollingHash.findDuplicateSubstring;

import java.util.HashSet;
import java.util.Set;

public class FindDuplicateSubstring {
    private static final int BASE = 256; // Alphabet size (ASCII)
    private static final int MOD = 1000000007; // Large prime
    
    public static String longestDupSubstring(String s) {
        int n = s.length();
        int low = 1, high = n - 1;
        int start = -1, maxLength = 0;
        
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int idx = checkDuplicate(s, mid);
            if (idx != -1) {
                start = idx;
                maxLength = mid;
                low = mid + 1; // try for longer
            } else {
                high = mid - 1; // try shorter
            }
        }
        return start != -1 ? s.substring(start, start + maxLength) : "";
    }

    private static int checkDuplicate(String text, int m) {
        int n = text.length();
        long windowHash = 0;
        long highestPower = 1;
        
        // Compute BASE^(m-1) % MOD
        for (int i = 0; i < m - 1; i++) {
            highestPower = (highestPower * BASE) % MOD;
        }
        
        // Initial hash for the first window
        for (int i = 0; i < m; i++) {
            windowHash = (windowHash * BASE + text.charAt(i)) % MOD;
        }
        
        Set<Long> seen = new HashSet<>();
        seen.add(windowHash);
        
        for (int i = 1; i <= n - m; i++) {
            windowHash = (BASE * (windowHash - text.charAt(i - 1) * highestPower) + text.charAt(i + m - 1)) % MOD;
            
            if (windowHash < 0) {
                windowHash += MOD;
            }
            
            if (seen.contains(windowHash)) {
                return i;
            }
            seen.add(windowHash);
        }
        return -1;
    }

    public static void main(String[] args) {
        String s = "banana";
        System.out.println("Longest Duplicate Substring: " + longestDupSubstring(s));
        // Output should be: "ana"
    }
}

// Complexity Analysis:
// Time Complexity: O(n log n) due to binary search over substring lengths and
// O(n) for each hash check.
// Space Complexity: O(n) for the hash set storing seen hashes.