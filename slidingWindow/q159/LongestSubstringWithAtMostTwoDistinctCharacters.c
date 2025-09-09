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

#include <stdio.h>
#include <string.h>

int lengthOfLongestSubstringTwoDistinct(char* s) {
    int n = strlen(s);
    int freq[256] = {0};  
    int distinct = 0;    
    int left = 0, ans = 0;

    for (int right = 0; right < n; right++) {
        char c = s[right];
        if (freq[(unsigned char)c] == 0) {
            distinct++;
        }
        freq[(unsigned char)c]++;

        while (distinct > 2) {
            char t = s[left++];
            freq[(unsigned char)t]--;
            if (freq[(unsigned char)t] == 0) {
                distinct--;
            }
        }

        int currLen = right - left + 1;
        if (currLen > ans) {
            ans = currLen;
        }
    }

    return ans;
}

int main() {
    char s1[] = "eceba";
    char s2[] = "ccaabbb";
    printf("%d\n", lengthOfLongestSubstringTwoDistinct(s1));  // 3
    printf("%d\n", lengthOfLongestSubstringTwoDistinct(s2));  // 5
    return 0;
}
