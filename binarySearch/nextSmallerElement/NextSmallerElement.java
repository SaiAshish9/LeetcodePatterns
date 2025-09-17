/*
Input:
nums = [1, 2, 3, 4], target = 2

Output:
1

Brute Force Approach:
- Iterate through the array linearly.
- Track the largest element smaller than the target.
- If no such element exists, return -1.

Complexity:
- Time: O(n)
- Space: O(1)

Optimal Approach:
- Since the array is sorted, use Binary Search.
- If nums[mid] < target, store it as a candidate and move right to find a larger smaller element.
- Else, move left.
- Return the stored candidate (or -1 if none found).

Complexity:
- Time: O(log n)
- Space: O(1)

Dry Run:
nums = [1, 2, 3, 4], target = 2
left = 0, right = 3, result = -1

Iteration 1:
mid = 1 → nums[1] = 2 → nums[mid] >= target → right = 0

Iteration 2:
mid = 0 → nums[0] = 1 → nums[mid] < target → result = 1, left = 1

Loop ends → return result = 1
*/

package binarySearch.nextSmallerElement;

public class NextSmallerElement {

    private static int nextSmallerElement(int[] nums, int target) {
        int result = -1;
        int n = nums.length;
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                result = nums[mid];
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = { 1, 2, 3, 4 };
        int target = 2;
        int result = nextSmallerElement(nums, target);
        System.out.println(result); // Output: 1
    }

}
