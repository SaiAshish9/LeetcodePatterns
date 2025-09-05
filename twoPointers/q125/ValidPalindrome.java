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

package twoPointers.q125;

public class ValidPalindrome {

    public static boolean isPalindrome(String s) {
        if (s == null || s.length() == 0)
            return true;

        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            char leftChar = s.charAt(left);
            char rightChar = s.charAt(right);

            if (!Character.isLetterOrDigit(leftChar)) {
                left++;
            } else if (!Character.isLetterOrDigit(rightChar)) {
                right--;
            } else {
                if (Character.toLowerCase(leftChar) != Character.toLowerCase(rightChar)) {
                    return false;
                }
                left++;
                right--;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String string = "abcba";
        System.out.println(isPalindrome(string));
    }

}
