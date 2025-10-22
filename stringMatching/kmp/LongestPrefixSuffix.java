package stringMatching.kmp;

public class LongestPrefixSuffix {

    private static void computeLPSArray(String str, int n, int[] lps) {
        int length = 0;
        int i = 1;
        lps[0] = 0;

        while (i < n) {
            if (str.charAt(i) == str.charAt(length)) {
                lps[i++] = ++length;
            } else {
                if (length != 0) {
                    length = lps[length - 1];
                } else {
                    lps[i++] = 0;
                }
            }
        }
    }

    public static void main(String[] args) {
        String str = "ababcababc";
        int n = str.length();
        int[] lps = new int[n];
        computeLPSArray(str, n, lps);
        System.out.println("Length of Longest Prefix which is also Suffix: " + lps[n - 1]);
    }

}

// Knuth–Morris–Pratt algorithm
// Time Complexity: O(n)
// Space Complexity: O(n)