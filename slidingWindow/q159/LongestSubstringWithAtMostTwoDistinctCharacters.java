/*
Input:
s = "eceba"

Output:
3

Brute Force:
- Generate all substrings and check if they contain at most 2 distinct characters.
- For each substring, count distinct characters using a Set or frequency map.
- Track the maximum length among valid substrings.
- Time Complexity: O(n^3) → O(n^2) substrings * O(n) to check distinct count.
- Space Complexity: O(n).

Optimal (Sliding Window with HashMap):
- Maintain a sliding window [left, right] that contains at most 2 distinct characters.
- Use a HashMap<Character, Integer> to store the frequency of characters in the window.
- Expand `right` to add characters.
- If distinct characters exceed 2, shrink from the left until the condition is met.
- Track the maximum window length during the process.
- Time Complexity: O(n).
- Space Complexity: O(1) → at most 2 distinct characters in the map.

Dry Run:
s = "eceba"

Step 1: right=0, char='e'
count = {e=1}, ans=1

Step 2: right=1, char='c'
count = {e=1, c=1}, ans=2

Step 3: right=2, char='e'
count = {e=2, c=1}, ans=3

Step 4: right=3, char='b'
count = {e=2, c=1, b=1} → size=3 (invalid)
Shrink from left:
 left=0 → 'e', count={e=1, c=1, b=1}
 left=1 → 'c', count={e=1, b=1}
Valid again (2 distinct).
ans = max(3, right-left+1) = 3

Step 5: right=4, char='a'
count = {e=1, b=1, a=1} → size=3 (invalid)
Shrink from left:
 left=2 → 'e', count={b=1, a=1}
Valid (2 distinct).
ans = max(3, right-left+1) = 3

Final Answer = 3
*/

 package slidingWindow.q159;

 import java.util.HashMap;
 import java.util.Map;
 
 public class LongestSubstringWithAtMostTwoDistinctCharacters {
 
     private static int lengthOfLongestSubstringTwoDistinct(String s) {
         Map<Character, Integer> count = new HashMap<>();
         int n = s.length();
         int ans = 0;
         int left = 0;
         for (int right = 0; right < n; ++right) {
             char c = s.charAt(right);
             count.put(c, count.getOrDefault(c, 0) + 1);
 
             while (count.size() > 2) {
                 char t = s.charAt(left++);
                 count.put(t, count.get(t) - 1);
                 if (count.get(t) == 0) {
                     count.remove(t);
                 }
             }
             ans = Math.max(ans, right - left + 1);
         }
         return ans;
     }
 
     public static void main(String[] args) {
         System.out.println(lengthOfLongestSubstringTwoDistinct("eceba")); // 3
     }
 
 }
 