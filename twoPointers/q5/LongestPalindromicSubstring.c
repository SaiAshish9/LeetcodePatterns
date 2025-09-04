#include <stdio.h>
#include <string.h>
#include <stdlib.h>

/*
Input: s = "babad"
Output: "bab"
Explanation: "aba" is also a valid answer.
*/
int expandAroundCenter(const char *s, int left, int right)
{
    int n = strlen(s);
    while (left >= 0 && right < n && s[left] == s[right])
    {
        left--;
        right++;
    }
    return (right - 1) - (left + 1) + 1;
}

char *longestPalindrome(const char *s)
{
    int n = strlen(s);
    if (n == 0)
    {
        char *empty = (char *)malloc(1);
        empty[0] = '\0';
        return empty;
    }

    int start = 0, maxLen = 0;
    for (int i = 0; i < n; i++)
    {
        int len1 = expandAroundCenter(s, i, i);
        int len2 = expandAroundCenter(s, i, i + 1);
        int len = (len1 > len2) ? len1 : len2;
        if (len > maxLen)
        {
            maxLen = len;
            start = i - (len - 1) / 2;
        }
    }

    char *result = (char *)malloc(maxLen + 1);
    strncpy(result, s + start, maxLen);
    result[maxLen] = '\0';
    return result;
}

int main()
{
    char s[] = "babad";
    char *res = longestPalindrome(s);
    printf("%s\n", res); // "bab"
    free(res);
    return 0;
}
