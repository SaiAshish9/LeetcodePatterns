/*
Input:
An integer array nums[] and an integer k representing the number of subarrays.

Output:
The minimum largest sum among k non-empty continuous subarrays.

Brute Force Approach:
Try all possible ways to split the array into k subarrays and compute the largest sum for each split. Keep track of the minimum among all largest sums.

Complexity:
Time: O(n^(k-1)) — Exponential, as we need to explore all splits
Space: O(n) — For storing subarray sums during computation

Optimal Approach (Binary Search + Prefix Sum):
1. Compute prefix sums of the array for quick subarray sum calculations.
2. Use binary search on the possible largest subarray sum range:
   - Lower bound = max element (at least one element per subarray)
   - Upper bound = sum of all elements (one subarray)
3. For each mid value in binary search, check if the array can be split into <= k subarrays such that each subarray sum ≤ mid using the canSplit helper.
4. Narrow the search range based on feasibility until the minimum largest sum is found.

Complexity:
Time: O(n * log(S)) — n for checking splits, log(S) for binary search over sum range S = sum of array
Space: O(n) — For prefix sum array

Dry Run:
nums = [7, 2, 5, 10, 8], k = 2
prefix = [0, 7, 9, 14, 24, 32], maxNum = 10, totalSum = 32
Binary search range: [10, 32]
- mid = 21 → canSplit returns true (split: [7,2,5], [10,8]) → answer = 21, high = 20
- mid = 15 → canSplit returns false → low = 16
- mid = 18 → canSplit returns true (split: [7,2,5], [10,8]) → answer = 18, high = 17
Binary search ends → minimum largest sum = 18
*/

#include <stdio.h>
#include <stdlib.h>

int canSplit(long *prefix, int n, int k, long maxSum)
{
    int count = 0;
    int start = 0;

    for (int end = 1; end <= n; end++)
    {
        long subarraySum = prefix[end] - prefix[start];
        if (subarraySum > maxSum)
        {
            start = end - 1;
            count++;
            if (prefix[end] - prefix[start] > maxSum)
                return 0; // false
        }
    }
    count++;
    return count <= k; // true if splits <= k
}

int splitArray(int *nums, int n, int k)
{
    long *prefix = (long *)malloc((n + 1) * sizeof(long));
    prefix[0] = 0;
    int maxNum = 0;
    long totalSum = 0;

    for (int i = 0; i < n; i++)
    {
        prefix[i + 1] = prefix[i] + nums[i];
        if (nums[i] > maxNum)
            maxNum = nums[i];
        totalSum += nums[i];
    }

    long low = maxNum, high = totalSum;
    long answer = totalSum;

    while (low <= high)
    {
        long mid = low + (high - low) / 2;
        if (canSplit(prefix, n, k, mid))
        {
            answer = mid;
            high = mid - 1;
        }
        else
        {
            low = mid + 1;
        }
    }

    free(prefix);
    return (int)answer;
}

int main()
{
    int nums[] = {7, 2, 5, 10, 8};
    int n = sizeof(nums) / sizeof(nums[0]);
    int k = 2;

    int result = splitArray(nums, n, k);
    printf("%d\n", result); // Output: 18

    return 0;
}
