/*
Input:
nums = [1, 2, 1, 3, 5, 6, 4]

Output:
5 (index of a peak element, nums[5] = 6)

Brute Force Approach:
- Traverse the array and for each element check if it is greater than its neighbors.
- If it is, return its index.
- Time: O(n), Space: O(1)

Optimal Approach (Binary Search):
- Use binary search since we only need any peak (not all peaks).
- Compare mid element with mid+1:
    - If nums[mid] > nums[mid+1], a peak must be on the left (including mid)
    - Else, a peak must be on the right (excluding mid)
- Time: O(log n), Space: O(1)

Dry Run:
nums = [1, 2, 1, 3, 5, 6, 4]
left = 0, right = 6
mid = 3, nums[3] = 3, nums[4] = 5 → nums[mid] < nums[mid+1], left = mid+1 = 4
left = 4, right = 6
mid = 5, nums[5] = 6, nums[6] = 4 → nums[mid] > nums[mid+1], right = mid = 5
left = 4, right = 5
mid = 4, nums[4] = 5, nums[5] = 6 → nums[mid] < nums[mid+1], left = mid+1 = 5
left = right = 5 → return 5 (peak element)

*/

package binarySearch.q162;

public class FindPeakElement {

    private static int findPeakElement(int[] nums) {
        int left = 0, right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[mid + 1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    public static void main(String[] args) {
        int[] nums = { 1, 2, 1, 3, 5, 6, 4 };
        System.out.println(findPeakElement(nums)); // Output: 5
    }

}
