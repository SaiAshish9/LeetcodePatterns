/*
Input: "abcba"
Output: true
Explanation: "abcba" is a palindrome.

Brute Force: O(n) time | O(n) space
Store all alphanumeric characters in a new string and check if it reads the same forwards and backwards.

Optimal: O(n) time | O(1) space
Use two pointers to compare characters from the start and end of the string, moving towards the center.

Dry Run:
Input: "abcba"
left = 0, right = 4
s[left] = 'a', s[right] = 'a' -> match, move inward
left = 1, right = 3
s[left] = 'b', s[right] = 'b' -> match, move inward
left = 2, right = 2
left >= right -> all characters matched, return true
*/

#include <stdio.h>
#include <ctype.h>
#include <stdbool.h>
#include <string.h>

bool isPalindrome(char *s)
{
    if (s == NULL || strlen(s) == 0)
    {
        return true;
    }

    int left = 0;
    int n = strlen(s);
    int right = n - 1;

    while (left < right)
    {
        char leftChar = s[left];
        char rightChar = s[right];

        if (!isalnum((unsigned char)leftChar))
        {
            left++;
        }
        else if (!isalnum((unsigned char)rightChar))
        {
            right--;
        }
        else
        {
            if (tolower((unsigned char)leftChar) != tolower((unsigned char)rightChar))
            {
                return false;
            }
            left++;
            right--;
        }
    }
    return true;
}

int main()
{
    char str[] = "abcba";
    if (isPalindrome(str))
    {
        printf("true\n");
    }
    else
    {
        printf("false\n");
    }
    return 0;
}
