/*
Input: 
s = "abcabcbb"

Output:
3

Brute Force Approach:
- Generate all substrings and check if they contain repeating characters.
- For each substring, use a HashSet to check uniqueness.
- Time Complexity: O(n^3) (O(n^2) substrings, O(n) to check uniqueness).
- Space Complexity: O(n).

Optimal Approach (Sliding Window):
- Use two pointers (left, right) to form a window of non-repeating characters.
- Maintain an array `charIndex[128]` to store the last index (plus one) of each character.
- When a duplicate character is found, move the `left` pointer to the right of its last seen index.
- Update maxLength during each iteration.
- Time Complexity: O(n).
- Space Complexity: O(1) (fixed 128 array for ASCII).

Dry Run:
s = "abcabcbb"

Step 1: right=0, char='a'
left=0, maxLength=1, charIndex['a']=1

Step 2: right=1, char='b'
left=0, maxLength=2, charIndex['b']=2

Step 3: right=2, char='c'
left=0, maxLength=3, charIndex['c']=3

Step 4: right=3, char='a' (already seen at index 0+1=1)
left = max(0, 1) = 1
maxLength = 3, charIndex['a']=4

Step 5: right=4, char='b' (seen at index 1+1=2)
left = max(1, 2) = 2
maxLength = 3, charIndex['b']=5

Step 6: right=5, char='c' (seen at index 2+1=3)
left = max(2, 3) = 3
maxLength = 3, charIndex['c']=6

Step 7: right=6, char='b' (seen at index 5)
left = max(3, 5) = 5
maxLength = 3, charIndex['b']=7

Step 8: right=7, char='b' (seen at index 6+1=7)
left = max(5, 7) = 7
maxLength = 3, charIndex['b']=8

Final Answer = 3
*/

#include <stdio.h>
#include <string.h>

int lengthOfLongestSubstring(char *s)
{
    int charIndex[128] = {0}; // stores last index + 1 of characters
    int left = 0, right = 0, maxLength = 0;
    int n = strlen(s);

    while (right < n)
    {
        char currentChar = s[right];
        if (charIndex[(int)currentChar] > left)
        {
            left = charIndex[(int)currentChar];
        }
        int currentLength = right - left + 1;
        if (currentLength > maxLength)
        {
            maxLength = currentLength;
        }
        charIndex[(int)currentChar] = right + 1;
        right++;
    }

    return maxLength;
}

int main()
{
    char s[] = "abcabcbb";
    printf("%d\n", lengthOfLongestSubstring(s)); // 3
    return 0;
}
