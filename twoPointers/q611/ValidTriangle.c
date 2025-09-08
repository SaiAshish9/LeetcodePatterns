/*
Input: [2, 2, 3, 4]
Output: 3

Brute Force Approach:
1. Sort the array.
2. Pick any triplet (i, j, k) with i < j < k and check the triangle condition:
   nums[i] + nums[j] > nums[k].
3. Count such triplets.
Time Complexity: O(n^3)

Optimal Approach (Two Pointers):
1. Sort the array in ascending order.
2. Fix the largest side (nums[i]) and use two pointers (left = 0, right = i-1) 
   to find how many pairs (left, right) form a valid triangle with nums[i].
3. If nums[left] + nums[right] > nums[i], then all elements from left to right-1
   will also form a triangle with nums[i] (because the array is sorted).
   → Add (right - left) to the count.
   → Move right pointer one step left.
4. Else, move left pointer one step right.
Time Complexity: O(n^2)

Dry Run:
nums = [2, 2, 3, 4]
Sorted = [2, 2, 3, 4]

i = 3 (nums[i] = 4), left = 0, right = 2
  nums[left] + nums[right] = 2 + 3 = 5 > 4 → valid
  count += (2 - 0) = 2 → (2,3,4) and (2,3,4) using different 2's
  right--

i = 2 (nums[i] = 3), left = 0, right = 1
  nums[left] + nums[right] = 2 + 2 = 4 > 3 → valid
  count += (1 - 0) = 1 → (2,2,3)
  right--

Total count = 3
*/

#include <stdio.h>
#include <stdlib.h>

int cmp(const void *a, const void *b) {
    return (*(int*)a - *(int*)b);
}

int countValidTriangles(int nums[], int n) {
    qsort(nums, n, sizeof(int), cmp);

    int count = 0;

    for (int i = n - 1; i >= 2; i--) {
        int left = 0;
        int right = i - 1;
        while (left < right) {
            if (nums[left] + nums[right] > nums[i]) {
                // If nums[left] + nums[right] > nums[i],
                // then all elements from left..(right-1) also work
                count += right - left;
                right--;
            } else {
                left++;
            }
        }
    }

    return count;
}

int main() {
    int nums[] = {2, 2, 3, 4};
    int n = sizeof(nums) / sizeof(nums[0]);
    int result = countValidTriangles(nums, n);
    printf("%d\n", result);  // Output: 3

    return 0;
}
