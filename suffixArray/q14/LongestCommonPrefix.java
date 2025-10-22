package suffixArray.q14;

import java.util.Arrays;
import java.util.Comparator;
public class LongestCommonPrefix {

    static class Suffix {
        String text;
        int index;

        Suffix(String text, int index) {
            this.text = text;
            this.index = index;
        }
    }

    public static int[] buildSuffixArray(String s) {
        int n = s.length();
        Suffix[] suffixes = new Suffix[n];

        for (int i = 0; i < n; i++) {
            suffixes[i] = new Suffix(s.substring(i), i);
        }

        Arrays.sort(suffixes, Comparator.comparing(a -> a.text));

        int[] suffixArr = new int[n];
        for (int i = 0; i < n; i++) {
            suffixArr[i] = suffixes[i].index;
        }
        return suffixArr;
    }

    public static int[] buildLCP(String s, int[] suffixArr) {
        int n = s.length();
        int[] lcp = new int[n];
        int[] rank = new int[n];

        for (int i = 0; i < n; i++) {
            rank[suffixArr[i]] = i;
        }

        int k = 0;
        for (int i = 0; i < n; i++) {
            if (rank[i] == n - 1) {
                k = 0;
                continue;
            }

            int j = suffixArr[rank[i] + 1];
            while (i + k < n && j + k < n && s.charAt(i + k) == s.charAt(j + k)) {
                k++;
            }

            lcp[rank[i]] = k;
            if (k > 0) k--;
        }
        return lcp;
    }

    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        String prefix = strs[0];

        for (int i = 1; i < strs.length; i++) {
            prefix = commonPrefix(prefix, strs[i]);
            if (prefix.isEmpty()) break;
        }
        return prefix;
    }

    private static String commonPrefix(String a, String b) {
        int minLen = Math.min(a.length(), b.length());
        int i = 0;
        while (i < minLen && a.charAt(i) == b.charAt(i)) {
            i++;
        }
        return a.substring(0, i);
    }

    public static void main(String[] args) {
        String[] strs = { "flower", "flow", "flight" };

        String lcpAcrossStrings = longestCommonPrefix(strs);
        System.out.println("Longest Common Prefix among all strings: " + lcpAcrossStrings);
        // Output: "fl"

        String s = "banana";
        int[] suffixArr = buildSuffixArray(s);
        int[] lcp = buildLCP(s, suffixArr);

        int maxLCP = 0, index = 0;
        for (int i = 0; i < lcp.length; i++) {
            if (lcp[i] > maxLCP) {
                maxLCP = lcp[i];
                index = i;
            }
        }

        System.out.println("\n--- Single string demo ---");
        // Output: Suffix Array and LCP Array
        // Longest Repeated Substring in "banana"
        System.out.println("Suffix Array: " + Arrays.toString(suffixArr));
        // Output: LCP Array
        // Output: Longest Repeated Substring
        System.out.println("LCP Array: " + Arrays.toString(lcp));
        // Output:
        // LCP Array: [1, 3, 0, 0, 2, 0]
        System.out.println("Longest Repeated Substring in '" + s + "': " + s.substring(suffixArr[index], suffixArr[index] + maxLCP));
        // Output: "ana"
    }
}

// Complexity Analysis:
// Building the suffix array takes O(n log n) time due to sorting.
// Building the LCP array takes O(n) time.
// Time Complexity: O(n log n)
// Space Complexity: O(n) for suffix and LCP arrays.
