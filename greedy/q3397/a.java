package greedy.q3397;

import java.util.Arrays;

class MaximumNumberOfDistinctElementsAfterOperations {
    public int maxDistinctElements(int[] nums, int k) {
        Arrays.sort(nums);
        int cnt = 0;
        int prev = Integer.MIN_VALUE;
        for (int num : nums) {
            int curr = Math.min(Math.max(num - k, prev + 1), num + k);
            if (curr > prev) {
                cnt++;
                prev = curr;
            }
        }
        return cnt;
    }
}

/*
Input: nums = [1,2,2,3,3,4], k = 2
Output: 6
Explanation:
nums changes to [-1, 0, 1, 2, 3, 4] after performing operations on the first four elements.
*/

// Complexity Analysis
// Time Complexity: O(NlogN), where N is the length of the input array nums. This
// is due to the sorting step. The subsequent iteration through the array takes O(N)
// time.
// Space Complexity: O(1), as we are using only a constant amount of extra space