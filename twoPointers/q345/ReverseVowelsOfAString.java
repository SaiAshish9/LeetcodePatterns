/*
Reverse Vowels of a String

Input: s = "hello"
Output: "holle" 

Brute Force:
1. Create a list to store the vowels in the string.
2. Traverse the string and add the vowels to the list.
3. Traverse the string again and replace the vowels with the vowels from the list in reverse order
4. Return the modified string   
Time Complexity: O(n)
Space Complexity: O(n)

Optimized:  
1. Use two pointers, one starting from the beginning and the other from the end of the string.
2. Move the left pointer to the right until it points to a vowel.
3. Move the right pointer to the left until it points to a vowel.
4. Swap the vowels at the left and right pointers.
5. Move both pointers towards the center and repeat until they meet.
6. Return the modified string.
Time Complexity: O(n)
Space Complexity: O(1)

Dry Run:
s = "hello"
left = 0, right = 4
arr = ['h', 'e', 'l', 'l', 'o'] 
vowels = {'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'}
1. left = 0, right = 4
2. arr[0] = 'h' (not a vowel, move left to 1)
3. arr[4] = 'o' (is a vowel)
4. Swap arr[1] and arr[4] -> arr = ['h', 'o', 'l', 'l', 'e']
5. left = 1, right = 4
6. arr = ['h', 'o', 'l', 'l', 'e']      
Time Complexity: O(n)
Space Complexity: O(1)
 */

package twoPointers.q345;

import java.util.*;

public class ReverseVowelsOfAString {

    private static String reverseVowels(String s) {
        int left = 0, right = s.length() - 1;

        char[] arr = s.toCharArray();
        String vowelsString = "aeiouAEIOU";
        HashSet<Character> vowels = new HashSet<>();
        for (char c : vowelsString.toCharArray()) {
            vowels.add(c);
        }

        while (left < right) {
            while (left < right && !vowels.contains(arr[left])) {
                left++;
            }
            while (left < right && !vowels.contains(arr[right])) {
                right--;
            }
            if (left < right) {
                char temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
                left++;
                right--;
            }
        }

        return new String(arr);
    }

    public static void main(String[] args) {
        String s = reverseVowels("hello");
        System.out.println(s);
    }

}
