/*
Input: s = "babad"
Output: "bab"
Explanation: "aba" is also a valid answer.
*/

#include <iostream>
using namespace std;

int main()
{
    cout << longestPalindrome("babad") << endl; // bab
}

int expandAroundCenter(string s, int left, int right)
{
    while (left >= 0 && right < s.length() && s[left] == s[right])
    {
        left--;
        right++;
    }
    return (right - 1) - (left + 1) + 1;
}

int longestPalindrome(string s)
{
    if (s.length() == 0)
        return 0;

    int start = 0, maxLen = 0;
    for (int i = 0; i < s.length(); i++)
    {
        int len1 = expandAroundCenter(s, i, i);
        int len2 = expandAroundCenter(s, i, i + 1);
        int len = max(len1, len2);
        if (len > maxLen)
        {
            maxLen = len;
            start = i - (len - 1) / 2;
        }
    }
    return s.substr(start, maxLen).length();
}
