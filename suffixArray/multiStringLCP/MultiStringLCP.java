package suffixArray.multiStringLCP;
    

import java.util.*;

public class MultiStringLCP {

    static class Suffix implements Comparable<Suffix> {
        int index;
        String suffix;

        Suffix(int index, String suffix) {
            this.index = index;
            this.suffix = suffix;
        }

        public int compareTo(Suffix other) {
            return this.suffix.compareTo(other.suffix);
        }
    }

    static int getSourceIndex(int index, int[] boundaries) {
        for (int i = 0; i < boundaries.length - 1; i++) {
            if (index >= boundaries[i] && index < boundaries[i + 1]) {
                return i;
            }
        }
        return -1;
    }

    public static int[] buildSuffixArray(String fullString) {
        int n = fullString.length();
        Suffix[] suffixes = new Suffix[n];
        for (int i = 0; i < n; i++) {
            suffixes[i] = new Suffix(i, fullString.substring(i));
        }
        Arrays.sort(suffixes);

        int[] suffixArr = new int[n];
        for (int i = 0; i < n; i++) {
            suffixArr[i] = suffixes[i].index;
        }
        return suffixArr;
    }

    public static int[] buildLCPArray(String fullString, int[] suffixArr) {
        int n = fullString.length();
        int[] rank = new int[n];
        for (int i = 0; i < n; i++) {
            rank[suffixArr[i]] = i;
        }
        
        int[] lcp = new int[n];
        int k = 0;
        
        for (int i = 0; i < n; i++) {
            if (rank[i] == n - 1) {
                k = 0;
                continue;
            }
            
            int j = suffixArr[rank[i] + 1];
            while (i + k < n && j + k < n && fullString.charAt(i + k) == fullString.charAt(j + k)) {
                k++;
            }
            
            lcp[rank[i]] = k;
            if (k > 0) k--;
        }
        return lcp;
    }

    public static String longestCommonPrefix(String[] strings) {
        if (strings == null || strings.length == 0) return "";
        if (strings.length == 1) return strings[0];

        StringBuilder combined = new StringBuilder();
        List<Character> delimiters = new ArrayList<>(Arrays.asList('#', '$', '@', '%', '*', '&', '+', '!', '<', '|'));
        int[] boundaries = new int[strings.length + 1];
        boundaries[0] = 0;

        for (int i = 0; i < strings.length; i++) {
            combined.append(strings[i]);
            if (i < strings.length - 1) {
                combined.append(delimiters.get(i));
            }
            boundaries[i + 1] = combined.length();
        }

        String fullString = combined.toString();
        int[] suffixArr = buildSuffixArray(fullString);
        int[] lcp = buildLCPArray(fullString, suffixArr);

        int maxLen = 0;
        int startIndex = -1;
        
        for (int i = 1; i < fullString.length(); i++) {
            int src1 = getSourceIndex(suffixArr[i - 1], boundaries);
            int src2 = getSourceIndex(suffixArr[i], boundaries);
            
            if (src1 != src2 && src1 != -1 && src2 != -1) {
                if (lcp[i - 1] > maxLen) {
                    maxLen = lcp[i - 1];
                    startIndex = suffixArr[i - 1];
                }
            }
        }

        return (startIndex != -1 && maxLen > 0) ? fullString.substring(startIndex, startIndex + maxLen) : "";
    }

    public static void main(String[] args) {
        String[] strings = {"interspecies", "interstellar", "interstate"};
        String result = longestCommonPrefix(strings);
        System.out.println("Longest Common Prefix: " + result); // Output: "inters"
        
        String[] test1 = {"apple", "applet", "application"};
        System.out.println("Test 1 LCP: " + longestCommonPrefix(test1)); // Output: "appl"
        
        String[] test2 = {"flower", "flow", "flight"};
        System.out.println("Test 2 LCP: " + longestCommonPrefix(test2)); // Output: "fl"
        
        String[] test3 = {"dog", "racecar", "car"};
        System.out.println("Test 3 LCP: " + longestCommonPrefix(test3)); // Output: ""
    }
}

// Complexity Analysis:
// Time Complexity: O(N log N) due to suffix array construction and sorting, where N is the total length of all strings combined.
// Space Complexity: O(N) for storing suffixes, suffix array, LCP array, and boundaries.