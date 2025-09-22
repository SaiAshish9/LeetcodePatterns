/*
Input:
Output:

Brute Force Approach:
Complexity:

Optimal Approach:
Complexity:

Dry Run:
*/

package prefixSum.q525;

import java.util.HashMap;

public class ContiguousArray {

    private static int findMaxLength(int[] nums) {
        HashMap<Integer, Integer> prefixSumMap = new HashMap<>();
        prefixSumMap.put(0, -1);

        int prefixSum = 0;
        int maxLength = 0;

        for (int i = 0; i < nums.length; i++) {
            prefixSum += (nums[i] == 1) ? 1 : -1;

            if (prefixSumMap.containsKey(prefixSum)) {
                maxLength = Math.max(maxLength, i - prefixSumMap.get(prefixSum));
            } else {
                prefixSumMap.put(prefixSum, i);
            }
        }

        return maxLength;
    }

    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 1, 1, 0};
        System.out.println(findMaxLength(nums));
    }

}