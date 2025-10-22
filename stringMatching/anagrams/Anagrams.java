package stringMatching.anagrams;

import java.util.ArrayList;
import java.util.List;

public class Anagrams {
    public static List<Integer> findAnagrams(String text, String pattern) {
        List<Integer> resultIndices = new ArrayList<>();
        
        if (text.length() < pattern.length()) {
            return resultIndices;
        }

        int[] patternFreq = new int[26];
        int[] windowFreq = new int[26];
        int windowSize = pattern.length();

        for (char c : pattern.toCharArray()) {
            patternFreq[c - 'a']++;
        }

        for (int i = 0; i < windowSize; i++) {
            windowFreq[text.charAt(i) - 'a']++;
        }

        if (matches(patternFreq, windowFreq)) {
            resultIndices.add(0);
        }

        for (int i = windowSize; i < text.length(); i++) {
            windowFreq[text.charAt(i) - 'a']++;
            windowFreq[text.charAt(i - windowSize) - 'a']--;

            if (matches(patternFreq, windowFreq)) {
                resultIndices.add(i - windowSize + 1);
            }
        }

        return resultIndices;
    }

    private static boolean matches(int[] freq1, int[] freq2) {
        for (int i = 0; i < 26; i++) {
            if (freq1[i] != freq2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String text = "cbaebabacd";
        String pattern = "abc";

        List<Integer> anagramIndices = findAnagrams(text, pattern);
        System.out.println("Anagram starting indices: " + anagramIndices);
        
        String text2 = "abab";
        String pattern2 = "ab";
        List<Integer> anagramIndices2 = findAnagrams(text2, pattern2);
        System.out.println("Anagram starting indices for 'abab' with pattern 'ab': " + anagramIndices2);
    }
}

// Complexity Analysis:
// Time Complexity: O(n) where n is the length of text
// Space Complexity: O(1) since we use fixed-size arrays of size 26