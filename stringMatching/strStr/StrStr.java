package stringMatching.strStr;

public class StrStr {
    public static int strStr(String haystack, String needle) {
        if (needle.isEmpty()) {
            return 0;
        }

        int[] lps = buildLPS(needle);
        int i = 0; // pointer for haystack
        int j = 0; // pointer for needle

        while (i < haystack.length()) {
            if (haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
            }

            if (j == needle.length()) {
                return i - j; // match found
            } else if (i < haystack.length() && haystack.charAt(i) != needle.charAt(j)) {
                if (j != 0) {
                    j = lps[j - 1]; // use LPS to shift pattern
                } else {
                    i++;
                }
            }
        }
        return -1; // no match found
    }

    private static int[] buildLPS(String pattern) {
        int[] lps = new int[pattern.length()];
        int len = 0; // length of the previous longest prefix suffix
        int i = 1;

        while (i < pattern.length()) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1]; // try previous prefix
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }

    public static void main(String[] args) {
        String haystack = "abxabcabcaby";
        String needle = "abcaby";
        int result = strStr(haystack, needle);
        System.out.println("First Occurrence Index: " + result);
    }
}

// Time Complexity: O(n + m) where n is haystack length, m is needle length
// Space Complexity: O(m) for the LPS array