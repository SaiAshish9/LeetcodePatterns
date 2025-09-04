/*
Input: s = "babad"
Output: "bab"
Explanation: "aba" is also a valid answer.
PreProcess Initialize Expand Update Track

Complexity Analysis:
Time Complexity: O(n)
Space Complexity: O(n)
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char *preprocess(const char *s)
{
    int n = strlen(s);
    int len = 2 * n + 1;
    char *t = (char *)malloc((len + 1) * sizeof(char));

    int k = 0;
    for (int i = 0; i < n; i++)
    {
        t[k++] = '#';
        t[k++] = s[i];
    }
    t[k++] = '#';
    t[k] = '\0';
    return t;
}

char *longestPalindrome(const char *s)
{
    if (!s || s[0] == '\0')
        return strdup("");

    char *t = preprocess(s);
    int n = strlen(t);
    int *P = (int *)malloc(n * sizeof(int));

    for (int i = 0; i < n; i++)
        P[i] = 0;

    int C = 0, R = 0, maxLen = 0, centerIndex = 0;

    for (int i = 0; i < n; i++)
    {
        int mirror = 2 * C - i;

        if (i < R)
        {
            P[i] = (R - i < P[mirror]) ? (R - i) : P[mirror];
        }

        while (i + P[i] + 1 < n && i - P[i] - 1 >= 0 &&
               t[i + P[i] + 1] == t[i - P[i] - 1])
        {
            P[i]++;
        }

        if (i + P[i] > R)
        {
            C = i;
            R = i + P[i];
        }

        if (P[i] > maxLen)
        {
            maxLen = P[i];
            centerIndex = i;
        }
    }

    int start = (centerIndex - maxLen) / 2;
    char *result = (char *)malloc((maxLen + 1) * sizeof(char));
    strncpy(result, s + start, maxLen);
    result[maxLen] = '\0';

    free(P);
    free(t);

    return result;
}

int main()
{
    const char *s = "babad";
    char *ans = longestPalindrome(s);
    printf("%s\n", ans); // "bab"
    free(ans);
    return 0;
}
