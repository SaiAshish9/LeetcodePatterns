package hashTable.ransomNote;

import java.util.HashMap;

public class RansomNote {
    public boolean canConstructUsingArray(String ransomNote, String magazine) {
        if (ransomNote.length() > magazine.length()) {
            return false;
        }

        int[] count = new int[26];

        for (char c : magazine.toCharArray()) {
            count[c - 'a']++;
        }

        for (char c : ransomNote.toCharArray()) {
            if (count[c - 'a'] == 0) {
                return false;
            }
            count[c - 'a']--;
        }

        return true;
    }

    public boolean canConstructUsingHashMap(String ransomNote, String magazine) {
        HashMap<Character, Integer> map = new HashMap<>();

        for (char c : magazine.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        for (char c : ransomNote.toCharArray()) {
            if (!map.containsKey(c) || map.get(c) == 0) {
                return false;
            }
            map.put(c, map.get(c) - 1);
        }

        return true;
    }

    public static void main(String[] args) {
        RansomNote rn = new RansomNote();

        String ransomNote1 = "aa";
        String magazine1 = "aab";
        System.out.println(
                "Can construct ransomNote using frequency array: " + rn.canConstructUsingArray(ransomNote1, magazine1));
        // true
        // Expected Output: true

        String ransomNote2 = "a";
        String magazine2 = "b";
        System.out.println(
                "Can construct ransomNote using frequency array: " + rn.canConstructUsingArray(ransomNote2, magazine2));
        // false
        // Expected Output: false

        String ransomNote3 = "rat";
        String magazine3 = "car";
        System.out.println(
                "Can construct ransomNote using HashMap: " + rn.canConstructUsingHashMap(ransomNote3, magazine3));
        // false
        // Expected Output: false

        String ransomNote4 = "aabb";
        String magazine4 = "aabbbc";
        System.out.println(
                "Can construct ransomNote using HashMap: " + rn.canConstructUsingHashMap(ransomNote4, magazine4));
        // true
    }
}

// Time Complexity for both methods: O(m + n), where m is the length of magazine
// and n is the length of ransomNote.
// Space Complexity for canConstructUsingArray: O(1), since the size of the
// count array is fixed (26).
// Space Complexity for canConstructUsingHashMap: O(k), where k is the number of
// unique characters in the magazine.