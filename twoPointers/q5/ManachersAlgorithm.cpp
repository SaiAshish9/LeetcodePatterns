/*
Input: s = "babad"
Output: "bab"
Explanation: "aba" is also a valid answer.
PreProcess Initialize Expand Update Track

Complexity Analysis:
Time Complexity: O(n)
Space Complexity: O(n)

Dry Run:
s = "babad"
i = 0, len1 = 1, len2 = 0, maxLen = 1, start = 0
i = 1, len1 = 3, len2 = 0, maxLen = 3, start = 0
i = 2, len1 = 1, len2 = 0, maxLen = 3, start = 0
i = 3, len1 = 3, len2 = 0, maxLen = 3, start = 2 start = 0
Result: "bab"   
*/

#include <iostream>
#include <string>
#include <vector>
using namespace std;

string preprocess(const string &s) {
    string t;
    for (size_t i = 0; i < s.size(); i++) {
        t.push_back('#');
        t.push_back(s[i]);
    }
    t.push_back('#');
    return t;
}

string longestPalindrome(const string &s) {
    string t = preprocess(s);
    int n = t.size();
    vector<int> P(n, 0);

    int C = 0, R = 0, maxLen = 0, centerIndex = 0;

    for (int i = 0; i < n; i++) {
        int mirror = 2 * C - i;

        if (i < R) {
            P[i] = min(R - i, P[mirror]);
        }

        while (i + P[i] + 1 < n && i - P[i] - 1 >= 0 && t[i + P[i] + 1] == t[i - P[i] - 1]) {
            P[i]++;
        }

        if (i + P[i] > R) {
            C = i;
            R = i + P[i];
        }

        if (P[i] > maxLen) {
            maxLen = P[i];
            centerIndex = i;
        }
    }

    int start = (centerIndex - maxLen) / 2;
    return s.substr(start, maxLen);
}

int main() {
    string s = "babad";
    cout << longestPalindrome(s) << endl; // "bab"
}
