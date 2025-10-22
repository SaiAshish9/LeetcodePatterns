package suffixArray.implementation;

import java.util.*;

public class SuffixArray {
    
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
        int[] suffixArr = buildSuffixArray(s);
        int[] lcp = buildLCP(s, suffixArr);
        
        System.out.println("Suffix Array:");
        for (int i = 0; i < suffixArr.length; i++) {
            System.out.println(suffixArr[i] + " : " + s.substring(suffixArr[i]));
        }
        
        System.out.println("\nLCP Array:");
        for (int i = 0; i < lcp.length; i++) {
            System.out.println("LCP[" + i + "] = " + lcp[i]);
        }
        
        int maxLcp = 0;
        int maxIndex = -1;
        for (int i = 0; i < lcp.length; i++) {
            if (lcp[i] > maxLcp) {
                maxLcp = lcp[i];
                maxIndex = i;
            }
        }
        
        if (maxLcp > 0) {
            String longestRepeated = s.substring(suffixArr[maxIndex], suffixArr[maxIndex] + maxLcp);
            System.out.println("\nLongest Repeated Substring: \"" + longestRepeated + "\" (length: " + maxLcp + ")");
            // Output: "ana" (length: 3)
        }
    }
}

// Complexity Analysis:
// Building the suffix array using the naive method takes O(n^2 log n) time due to sorting n suffixes each of length up to n.
// The LCP array can be built in O(n) time using Kasai's algorithm.
// Overall, this implementation is not optimal for large strings, but it is straightforward and easy to understand.
// Time Complexity: O(n^2 log n) for suffix array construction, O(n) for LCP array construction.
// Space Complexity: O(n) for storing suffixes, suffix array, and LCP array.