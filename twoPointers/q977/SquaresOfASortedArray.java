/*
Problem: Squares of a Sorted Array
-----------------------------------
You are given an integer array `nums` sorted in non-decreasing order. 
Return an array of the squares of each number sorted in non-decreasing order.

Example:
Input:  nums = [-4, -1, 0, 3, 10]
Output: [0, 1, 9, 16, 100]

-------------------------------------------------
Brute Force Approach:
- Square each element in the array.
- Sort the squared array.
- Time Complexity: O(n log n) due to sorting.
- Space Complexity: O(n) for storing the result.

-------------------------------------------------
Optimal Approach (Two Pointers):
- Since the array is sorted, the largest square will be from either 
  the most negative number (left side) or the largest positive number (right side).
- Use two pointers:
  - Left starts at 0, Right starts at n-1.
  - Compare absolute values of nums[left] and nums[right].
  - Place the larger square at the end of the result array (index i).
  - Move the respective pointer inward.
- Continue until all positions are filled.
- Time Complexity: O(n) (single pass).
- Space Complexity: O(n) for result array.

-------------------------------------------------
Dry Run Example:
Input: nums = [-7, -3, 2, 3, 11]
n = 5
result = [_, _, _, _, _]

Step 1: left=0 (-7), right=4 (11), abs(11) > abs(-7)
        result[4] = 121 → [_, _, _, _, 121]
        right = 3

Step 2: left=0 (-7), right=3 (3), abs(-7) > abs(3)
        result[3] = 49 → [_, _, _, 49, 121]
        left = 1

Step 3: left=1 (-3), right=3 (3), abs(-3) == abs(3)
        result[2] = 9 → [_, _, 9, 49, 121]
        right = 2

Step 4: left=1 (-3), right=2 (2), abs(-3) > abs(2)
        result[1] = 9 → [_, 9, 9, 49, 121]
        left = 2

Step 5: left=2 (2), right=2 (2)
        result[0] = 4 → [4, 9, 9, 49, 121]

Final Output: [4, 9, 9, 49, 121]
*/

package twoPointers.q977;

public class SquaresOfASortedArray {

    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        int left = 0, right = n - 1;

        for (int i = n - 1; i >= 0; i--) {
            if (Math.abs(nums[left]) > Math.abs(nums[right])) {
                result[i] = nums[left] * nums[left];
                left++;
            } else {
                result[i] = nums[right] * nums[right];
                right--;
            }
        }

        return result;
    }

}
