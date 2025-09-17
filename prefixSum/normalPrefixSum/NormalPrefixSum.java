/*
Input:
nums = [1, 2, 3, 4, 5]

Output:
prefixSum = [1, 3, 6, 10, 15]

Brute Force Approach:
- For each element at index i, sum all elements from index 0 to i.
- Store this sum in the prefix array at index i.
- Time Complexity: O(n^2) because for each element we iterate through all previous elements.
- Space Complexity: O(n) for storing the prefix sum array.

Optimal Approach:
- Use the idea that prefix[i] = prefix[i-1] + nums[i] (cumulative sum).
- Compute prefix sum in a single pass.
- Time Complexity: O(n)
- Space Complexity: O(n)

Dry Run:
nums = [1, 2, 3, 4, 5]
prefix[0] = 1
prefix[1] = 1 + 2 = 3
prefix[2] = 3 + 3 = 6
prefix[3] = 6 + 4 = 10
prefix[4] = 10 + 5 = 15
Result: [1, 3, 6, 10, 15]
*/

package prefixSum.normalPrefixSum;

public class NormalPrefixSum {

    private static int[] computePrefixSum(int nums[]) {
        int n = nums.length;
        int prefix[] = new int[n];
        prefix[0] = nums[0];
        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] + nums[i];
        }

        return prefix;
    }

    public static void main(String[] args) {
        int nums[] = { 1, 2, 3, 4, 5 };
        System.out.println(computePrefixSum(nums)); // [1, 3, 6, 10, 15]
    }

}
