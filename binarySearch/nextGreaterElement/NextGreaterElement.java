/*
Input:
nums = [1, 2, 3, 4], target = 2

Output:
3

Brute Force Approach:
- Iterate through the array linearly.
- Track the smallest element greater than the target.
- If no such element exists, return -1.

Complexity:
- Time: O(n)
- Space: O(1)

Optimal Approach:
- Since the array is sorted, use Binary Search.
- If nums[mid] > target, store it as a candidate and move left to find a smaller greater element.
- Else, move right.
- Return the stored candidate (or -1 if none found).

Complexity:
- Time: O(log n)
- Space: O(1)

Dry Run:
nums = [1, 2, 3, 4], target = 2
left = 0, right = 3, result = -1

Iteration 1:
mid = 1 → nums[1] = 2 → nums[mid] <= target → left = 2

Iteration 2:
mid = 2 → nums[2] = 3 → nums[mid] > target → result = 3, right = 1

Loop ends → return result = 3
*/

package binarySearch.nextGreaterElement;

public class NextGreaterElement {

    private static int nextGreaterElement(int[] nums, int target) {
        int result = -1;
        int n = nums.length;
        int left = 0;
        int right = n - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > target) {
                result = nums[mid];
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = { 1, 2, 3, 4 };
        int target = 2;
        int result = nextGreaterElement(nums, target);
        System.out.println(result); // Output: 3
    }

}
