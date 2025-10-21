package hashTable.groupAnagrams;

import java.util.*;

public class GroupAnagrams {

    private static List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }

        Map<String, List<String>> map = new HashMap<>();

        for (String s : strs) {
            char[] charArray = s.toCharArray();
            Arrays.sort(charArray);
            String sorted = new String(charArray);

            if (!map.containsKey(sorted)) {
                map.put(sorted, new ArrayList<>());
            }
            map.get(sorted).add(s);
        }

        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};

        List<List<String>> result = groupAnagrams(strs);
        for (List<String> group : result) {
            System.out.println(group);
        }
        // Expected Output:
        // ["bat"]
        // ["nat","tan"]
        // ["ate","eat","tea"]
    }
}

// Time Complexity: O(N * K log K), where N is the number of strings and K is the maximum length of a string. Sorting each string takes O(K log K) time.
// Space Complexity: O(N * K), the space used to store the grouped anagrams in the hashmap.