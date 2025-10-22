package suffixArray.q1044;

import java.util.Arrays;

public class LongestDuplicateSubstring {
    
    public static String longestDupSubstring(String s) {
        int[] suffix = buildSuffixArray(s);
        int[] lcp = buildLCPArray(s, suffix);

        int maxLen = 0;
        int startIndex = -1;
        for (int i = 0; i < lcp.length; i++) {
            if (lcp[i] > maxLen) {
                maxLen = lcp[i];
                startIndex = suffix[i];
            }
        }

        return maxLen > 0 ? s.substring(startIndex, startIndex + maxLen) : "";
    }

    private static int[] buildSuffixArray(String s) {
        int n = s.length();
        Suffix[] suffixes = new Suffix[n];

        for (int i = 0; i < n; i++) {
            suffixes[i] = new Suffix(s.substring(i), i);
        }

        Arrays.sort(suffixes);

        int[] suffixArr = new int[n];
        for (int i = 0; i < n; i++) {
            suffixArr[i] = suffixes[i].index;
        }

        return suffixArr;
    }

    private static int[] buildLCPArray(String s, int[] suffix) {
        int n = s.length();
        int[] lcp = new int[n];
        int[] rank = new int[n];

        for (int i = 0; i < n; i++) {
            rank[suffix[i]] = i;
        }

        int k = 0;
        for (int i = 0; i < n; i++) {
            if (rank[i] == n - 1) {
                k = 0;
                continue;
            }
            
            int j = suffix[rank[i] + 1];
            while (i + k < n && j + k < n && s.charAt(i + k) == s.charAt(j + k)) {
                k++;
            }
            
            lcp[rank[i]] = k;

            if (k > 0) {
                k--;
            }
        }
        return lcp;
    }

    private static class Suffix implements Comparable<Suffix> {
        String str;
        int index;

        Suffix(String str, int index) {
            this.str = str;
            this.index = index;
        }

        @Override
        public int compareTo(Suffix other) {
            return this.str.compareTo(other.str);
        }
    }

    public static void main(String[] args) {
        String s = "banana";
        System.out.println(longestDupSubstring(s)); // Output: "ana"
        
        // Additional test cases
        System.out.println(longestDupSubstring("abcd")); // Output: ""
        System.out.println(longestDupSubstring("aaaa")); // Output: "aaa"
        System.out.println(longestDupSubstring("abcab")); // Output: "ab"
    }
}

// Complexity Analysis:
// Time Complexity: O(n^2 log n) in the worst case due to suffix array construction using sorting.
// Space Complexity: O(n) for storing suffixes and LCP array.   