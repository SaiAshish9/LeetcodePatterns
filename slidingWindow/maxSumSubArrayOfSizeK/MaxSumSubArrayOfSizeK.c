/*
Input: nums = [2,2,3,4], k = 2
Output: 7

Brute Force Approach:
- For every possible subarray of size k, calculate the sum.
- Keep track of the maximum sum found so far.
- Time Complexity: O(n^2), since for each starting index we compute the sum of k elements.

Optimal Approach (Sliding Window):
- Compute the sum of the first window of size k.
- Slide the window by one element at a time:
    -> Add the new element entering the window.
    -> Remove the element leaving the window.
- Update the maximum sum accordingly.
- Time Complexity: O(n), since each element is added and removed once.

Dry Run:
nums = [2, 2, 3, 4], k = 2

Brute Force:
- Subarray [2, 2] => sum = 4
- Subarray [2, 3] => sum = 5
- Subarray [3, 4] => sum = 7  -> max = 7

Sliding Window:
Initial window [2, 2] => sum = 4, max = 4
Slide to [2, 3]:
    sum = (prev sum 4) + 3 - 2 = 5, max = 5
Slide to [3, 4]:
    sum = (prev sum 5) + 4 - 2 = 7, max = 7

Final Answer = 7
*/

#include <stdio.h>
#include <stdlib.h>

// Brute Force O(n^2)
int maxSubArrayBruteForce(int arr[], int n, int k) {
    int max_sum = 0;

    for (int i = 0; i <= n - k; i++) {
        int sum = 0;
        for (int j = 0; j < k; j++) {
            sum += arr[i + j];
        }
        if (sum > max_sum) {
            max_sum = sum;
        }
    }
    return max_sum;
}

// Sliding Window O(n)
int maxSubArray(int arr[], int n, int k) {
    int windowSum = 0;

    // sum of first window
    for (int i = 0; i < k; i++) {
        windowSum += arr[i];
    }
    int maxSum = windowSum;

    // slide the window
    for (int i = k; i < n; i++) {
        windowSum += arr[i] - arr[i - k];
        if (windowSum > maxSum) {
            maxSum = windowSum;
        }
    }
    return maxSum;
}

int main() {
    int nums[] = {2, 2, 3, 4};
    int n = sizeof(nums) / sizeof(nums[0]);
    int k = 2;

    printf("%d\n", maxSubArrayBruteForce(nums, n, k)); // 7
    printf("%d\n", maxSubArray(nums, n, k));           // 7

    return 0;
}
