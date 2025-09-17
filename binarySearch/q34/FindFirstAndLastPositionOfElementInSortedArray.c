/*
Input:
nums = [5, 7, 7, 8, 8, 10], target = 8

Output:
[3, 4]

Brute Force Approach:
- Iterate through the array to find the first index of target.
- Iterate again (or continue) to find the last index of target.
- If target is not found, return [-1, -1].

Complexity:
- Time: O(n)
- Space: O(1)

Optimal Approach:
- Use Binary Search to find:
    1. The first occurrence of target.
    2. The last occurrence of target.
- Combine results and return as [firstPos, lastPos].

Complexity:
- Time: O(log n)  (two binary searches)
- Space: O(1)

Dry Run:
nums = [5, 7, 7, 8, 8, 10], target = 8

binarySearchFirst():
    left=0, right=5
    mid=2 → nums[2]=7 < 8 → left=3
    mid=4 → nums[4]=8 == target → firstPos=4, right=3
    mid=3 → nums[3]=8 == target → firstPos=3, right=2
    End → firstPos=3

binarySearchLast():
    left=0, right=5
    mid=2 → nums[2]=7 < 8 → left=3
    mid=4 → nums[4]=8 == target → lastPos=4, left=5
    mid=5 → nums[5]=10 > 8 → right=4
    End → lastPos=4

Result = [3, 4]
*/

#include <stdio.h>

int binarySearchFirst(int nums[], int n, int target) {
    int left = 0, right = n - 1;
    int firstPos = -1;

    while (left <= right) {
        int mid = left + (right - left) / 2;

        if (nums[mid] == target) {
            firstPos = mid;
            right = mid - 1; // search left
        } else if (nums[mid] < target) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }
    return firstPos;
}

int binarySearchLast(int nums[], int n, int target) {
    int left = 0, right = n - 1;
    int lastPos = -1;

    while (left <= right) {
        int mid = left + (right - left) / 2;

        if (nums[mid] == target) {
            lastPos = mid;
            left = mid + 1; // search right
        } else if (nums[mid] < target) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }
    return lastPos;
}

int main() {
    int nums[] = {5, 7, 7, 8, 8, 10};
    int n = sizeof(nums) / sizeof(nums[0]);
    int target = 8;

    int firstPos = binarySearchFirst(nums, n, target); // Output: 3
    int lastPos = binarySearchLast(nums, n, target); // Output: 4

    printf("[ firstPos: %d, lastPos: %d ]\n", firstPos, lastPos);
    return 0;
}
