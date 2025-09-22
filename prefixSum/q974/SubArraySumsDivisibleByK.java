/*
Input:
nums = [23, 2, 4, 6, 7], k = 6
Output:
4
Explanation: The subarrays divisible by 6 are:
[23, 2, 4, 6, 7] → sum = 42
[2, 4] → sum = 6
[2, 4, 6] → sum = 12
[6] → sum = 6

Brute Force Approach:
- Generate all subarrays and check if sum % k == 0.
- Count the number of such subarrays.
Complexity:
- Time: O(n^2)
- Space: O(1)

Optimal Approach (Prefix Sum + HashMap):
- Use a prefix sum and track the count of each remainder when divided by k.
- For each prefix sum, calculate remainder = (prefixSum % k + k) % k
  → ensures remainder is non-negative.
- If the same remainder has occurred before, all subarrays between previous indices and current index are divisible by k.
- Increment count accordingly.
Complexity:
- Time: O(n)
- Space: O(k) → at most k different remainders.

Dry Run:
nums = [23, 2, 4, 6, 7], k = 6
prefixSum = 0, countMap = {0:1}, count = 0

1. num = 23 → prefixSum = 23 → remainder = 5 → countMap = {0:1,5:1}, count = 0
2. num = 2 → prefixSum = 25 → remainder = 1 → countMap = {0:1,5:1,1:1}, count = 0
3. num = 4 → prefixSum = 29 → remainder = 5 → countMap has 5 once → count += 1 → count = 1, countMap = {0:1,5:2,1:1}
4. num = 6 → prefixSum = 35 → remainder = 5 → countMap has 5 twice → count += 2 → count = 3, countMap = {0:1,5:3,1:1}
5. num = 7 → prefixSum = 42 → remainder = 0 → countMap has 0 once → count += 1 → count = 4, countMap = {0:2,5:3,1:1}

Return 4
*/

package prefixSum.q974;

import java.util.HashMap;

public class SubArraySumsDivisibleByK {

    private static int subarraysDivByK(int[] nums, int k) {
        HashMap<Integer, Integer> countMap = new HashMap<>();
        countMap.put(0, 1);

        int prefixSum = 0;
        int count = 0;

        for (int num : nums) {
            prefixSum += num;
            int remainder = (prefixSum % k + k) % k;
            count += countMap.getOrDefault(remainder, 0);
            countMap.put(remainder, countMap.getOrDefault(remainder, 0) + 1);
        }

        return count;
    }

    public static void main(String[] args) {
        int[] nums = { 23, 2, 4, 6, 7 };
        int k = 6;
        System.out.println(subarraysDivByK(nums, k)); // Output: 4
    }

}
