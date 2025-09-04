#include <iostream>
using namespace std;

string longestPalindrome(string s)
{
    string t = preprocess(s);
    int center = 0, R = 0, C = 0, maxLen = 0, start = 0;
    int n = t.size();
    int *P = new int[n];
    for (int i = 0; i < n; i++)
        P[i] = 0;

    for (int i = 1; i < n - 1; i++)
    {
    }

    delete[] P;
}

string preprocess(string s)
{
    int n = s.size();
    if (n == 0)
        return "##";
    string temp = "#";
    for (int i = 0; i < n; i++)
    {
        temp += "#" + s[i];
    }
    temp += "#";
    return temp;
}

int main()
{
    string s = "babad";
    cout << longestPalindrome(s) << endl;
}
