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

#include <bits/stdc++.h>
using namespace std;

class ValidPalindrome
{
public:
    static bool isPalindrome(string s)
    {
        if (s.empty())
        {
            return true;
        }

        int left = 0;
        int right = s.size() - 1;

        while (left < right)
        {
            char leftChar = s[left];
            char rightChar = s[right];

            if (!isalnum(leftChar))
            {
                left++;
            }
            else if (!isalnum(rightChar))
            {
                right--;
            }
            else
            {
                if (tolower(leftChar) != tolower(rightChar))
                {
                    return false;
                }
                left++;
                right--;
            }
        }
        return true;
    }
};

int main()
{
    string str = "abcba";
    cout << (ValidPalindrome::isPalindrome(str) ? "true" : "false") << endl;
    return 0;
}
