/*
Input:
target = 7
nums = [2, 3, 1, 2, 4, 3]

Output:
2
(Explanation: The subarray [4,3] has the minimal length = 2 with sum ≥ 7.)

Brute Force:
- Generate all possible subarrays.
- For each subarray, calculate its sum and check if it’s ≥ target.
- Track the minimum length among all valid subarrays.
- Time Complexity: O(n^2).
- Space Complexity: O(1).

Optimal (Sliding Window):
- Use two pointers (left, right) to maintain a sliding window.
- Expand `right` and add to the running sum.
- While sum ≥ target:
    - Update the minimum length.
    - Shrink the window by moving `left` forward and subtracting nums[left].
- Return the minimum length found, or 0 if no subarray meets the condition.
- Time Complexity: O(n).
- Space Complexity: O(1).

Dry Run:
target = 7, nums = [2, 3, 1, 2, 4, 3]

Step 1: right=0 → sum=2 (<7) → no update
Step 2: right=1 → sum=5 (<7) → no update
Step 3: right=2 → sum=6 (<7) → no update
Step 4: right=3 → sum=8 (≥7)
    - minLength = 4 (subarray [2,3,1,2])
    - Shrink: remove nums[left]=2 → sum=6, left=1
Step 5: right=4 → sum=10 (≥7)
    - minLength = min(4, 4) = 4 (subarray [3,1,2,4])
    - Shrink: remove nums[left]=3 → sum=7, left=2 → minLength=3 (subarray [1,2,4])
    - Shrink: remove nums[left]=1 → sum=6, left=3
Step 6: right=5 → sum=9 (≥7)
    - minLength = min(3, 3) = 3 (subarray [2,4,3])
    - Shrink: remove nums[left]=2 → sum=7, left=4 → minLength=2 (subarray [4,3])
    - Shrink: remove nums[left]=4 → sum=3, left=5

End: minLength = 2 → result = 2
*/

#include <stdio.h>
#include <limits.h>

int minSubArrayLen(int target, int nums[], int n) {
    int minLength = INT_MAX;
    int left = 0;
    int sum = 0;

    for (int right = 0; right < n; right++) {
        sum += nums[right];

        while (sum >= target) {
            int windowSize = right - left + 1;
            if (windowSize < minLength) {
                minLength = windowSize;
            }
            sum -= nums[left];
            left++;
        }
    }

    return (minLength == INT_MAX) ? 0 : minLength;
}

int main() {
    int nums[] = {2, 3, 1, 2, 4, 3};
    int n = sizeof(nums) / sizeof(nums[0]);
    int target = 7;

    printf("%d\n", minSubArrayLen(target, nums, n)); // 2
    return 0;
}
