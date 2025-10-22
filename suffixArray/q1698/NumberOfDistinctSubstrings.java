package suffixArray.q1698;

import java.util.Arrays;
import java.util.Comparator;

public class NumberOfDistinctSubstrings {

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
            if (k > 0) {
                k--;
            }
        }
        return lcp;
    }

    public static int countDistinctSubstrings(String s) {
        int n = s.length();
        if (n == 0)
            return 0;

        int[] suffixArr = buildSuffixArray(s);
        int[] lcp = buildLCP(s, suffixArr);

        // Total possible substrings = n*(n+1)/2
        int totalSubstrings = n * (n + 1) / 2;

        int sumOfLCP = 0;
        for (int x : lcp) {
            sumOfLCP += x;
        }

        return totalSubstrings - sumOfLCP;
    }

    static class Suffix {
        String text;
        int index;

        Suffix(String text, int index) {
            this.text = text;
            this.index = index;
        }
    }

    public static void main(String[] args) {
        String s = "banana";
        int result = countDistinctSubstrings(s);
        System.out.println("Number of distinct substrings in \"" + s + "\" = " + result);

        String[] testCases = { "", "a", "ab", "abc", "aaa", "abab" };
        for (String test : testCases) {
            int count = countDistinctSubstrings(test);
            System.out.println("Distinct substrings in \"" + test + "\" = " + count);
            /*
             * Number of distinct substrings in "banana" = 15
             * Distinct substrings in "" = 0
             * Distinct substrings in "a" = 1
             * Distinct substrings in "ab" = 3
             * Distinct substrings in "abc" = 6
             * Distinct substrings in "aaa" = 3
             * Distinct substrings in "abab" = 7
             */
        }
    }
}

// Complexity Analysis:
// Time Complexity: O(n² log n) - The dominant factor is building the suffix
// array:
// - Creating n substrings: O(n²) total characters
// - Sorting n strings of average length n/2: O(n² log n)
// - LCP construction: O(n)
// - Overall: O(n² log n)
//
// Space Complexity: O(n²) - We store all suffixes explicitly, requiring O(n²)
// space
// - Suffix array: O(n)
// - LCP array: O(n)
// - Suffix objects: O(n²) due to storing all substrings