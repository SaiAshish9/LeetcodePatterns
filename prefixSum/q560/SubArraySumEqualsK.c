/*
Input:
nums = [1, 1, 1], k = 2

Output:
2
// Explanation: The subarrays [1,1] (indices 0–1) and [1,1] (indices 1–2) sum to 2.

---------------------------------------------------
Brute Force Approach:
- Generate all subarrays and calculate their sums.
- For each subarray, check if the sum equals k.
- Increment count if true.

Steps:
1. Initialize count = 0.
2. For i = 0 to n-1:
   - Initialize sum = 0.
   - For j = i to n-1:
     - sum += nums[j].
     - If sum == k → count++.
3. Return count.

Complexity:
- Time: O(n^2)  (nested loops for subarrays)
- Space: O(1)

---------------------------------------------------
Optimal Approach (Prefix Sum + HashMap):
- Use prefix sums and a hashmap to store frequencies of prefix sums.
- Idea: if prefixSum[j] - prefixSum[i] = k → subarray(i+1 ... j) has sum k.
- Rearranged: prefixSum[j] - k = prefixSum[i].
- So for each prefixSum, check if (prefixSum - k) exists in map.

Steps:
1. Initialize count = 0, prefixSum = 0.
2. HashMap with {0 → 1} (empty subarray).
3. For each num in nums:
   - prefixSum += num.
   - If (prefixSum - k) exists in map → add its frequency to count.
   - Update frequency of prefixSum in map.
4. Return count.

Complexity:
- Time: O(n)
- Space: O(n)

---------------------------------------------------
Dry Run:
nums = [1, 1, 1], k = 2

prefixSumMap = {0 : 1}
prefixSum = 0, count = 0

i=0 → num=1
  prefixSum = 1
  prefixSum-k = -1 (not in map)
  map = {0:1, 1:1}
  count = 0

i=1 → num=1
  prefixSum = 2
  prefixSum-k = 0 → found, freq=1 → count=1
  map = {0:1, 1:1, 2:1}
  count = 1

i=2 → num=1
  prefixSum = 3
  prefixSum-k = 1 → found, freq=1 → count=2
  map = {0:1, 1:1, 2:1, 3:1}
  count = 2

Final Answer: 2
*/

#include <stdio.h>
#include <stdlib.h>

int subarraySum(int* nums, int n, int k) {
    int offset = 10000; 
    int size = 2 * offset + 1;
    int* prefixCount = (int*)calloc(size, sizeof(int));

    prefixCount[offset] = 1; 

    int prefixSum = 0;
    int count = 0;

    for (int i = 0; i < n; i++) {
        prefixSum += nums[i];
        int idx = prefixSum - k + offset;
        if (idx >= 0 && idx < size) {
            count += prefixCount[idx];
        }

        prefixCount[prefixSum + offset]++;
    }

    free(prefixCount);
    return count;
}

int main() {
    int nums[] = {1, 1, 1};
    int n = sizeof(nums) / sizeof(nums[0]);
    int k = 2;

    int result = subarraySum(nums, n, k);
    printf("%d\n", result);  // Output: 2

    return 0;
}
