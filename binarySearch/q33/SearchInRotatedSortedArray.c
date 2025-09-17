/*
Input:
nums = [4, 5, 6, 7, 0, 1, 2], target = 0

Output:
4

Brute Force Approach:
- Linearly search through the array and return the index where target matches.
- If not found, return -1.

Complexity:
- Time: O(n)
- Space: O(1)

Optimal Approach:
- Use a modified Binary Search since the array is rotated but still partially sorted.
- At each step:
    - If left half is sorted, check if target lies in it.
    - Else, search in the right half.
    - Similarly, if right half is sorted, check if target lies in it.
- Narrow down search space accordingly.

Complexity:
- Time: O(log n)
- Space: O(1)

Dry Run:
nums = [4, 5, 6, 7, 0, 1, 2], target = 0
left = 0, right = 6

Iteration 1:
mid = 3 → nums[3] = 7
nums[left] <= nums[mid] → left side sorted
nums[left] <= target && target < nums[mid] → 4 <= 0 && 0 < 7 → false
So, left = mid + 1 = 4

Iteration 2:
mid = 5 → nums[5] = 1
nums[left] <= nums[mid] → nums[4] = 0, 0 <= 1 → left side sorted
nums[left] <= target && target < nums[mid] → 0 <= 0 && 0 < 1 → true
So, right = mid - 1 = 4

Iteration 3:
mid = 4 → nums[4] = 0 → matches target → return 4

Answer: 4
*/

#include <stdio.h>

int search(int nums[], int n, int target) {
    int left = 0;
    int right = n - 1;

    while (left <= right) {
        int mid = left + (right - left) / 2;

        if (nums[mid] == target) {
            return mid;
        }

        if (nums[left] <= nums[mid]) { // Left side is sorted
            if (nums[left] <= target && target < nums[mid]) {
                right = mid - 1; // Target is in the left sorted side
            } else {
                left = mid + 1; // Target is in the right side
            }
        } else { // Right side is sorted
            if (nums[mid] < target && target <= nums[right]) {
                left = mid + 1; // Target is in the right sorted side
            } else {
                right = mid - 1; // Target is in the left side
            }
        }
    }

    return -1;
}

int main() {
    int arr[] = {4, 5, 6, 7, 0, 1, 2};
    int n = sizeof(arr) / sizeof(arr[0]);
    int target = 0;

    int result = search(arr, n, target);
    printf("%d\n", result); // Output: 4

    return 0;
}
