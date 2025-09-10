/*
Input: 
nums = [1, 2, 4], k = 5

Output: 
3

Brute Force Approach:
- Try to make every element the most frequent by incrementing smaller elements to match it.
- For each target element, calculate the total cost required to make others equal to it.
- If the cost ≤ k, update the maximum frequency.
- This involves nested loops and repeated calculations.

Complexity:
Time: O(n^2)  
Space: O(1)

Optimal Approach (Sliding Window + Sorting):
1. Sort the array so that we only need to consider increasing smaller numbers to match larger ones.  
2. Use a sliding window with two pointers (left and right).  
3. Maintain the sum of the current window.  
4. For each `right`, check if the total cost to make all elements in the window equal to `nums[right]` is ≤ k:  
   - Cost = (windowSize * nums[right]) - sum.  
5. If cost > k, shrink the window from the left until it becomes valid.  
6. Track the maximum valid window size.

Complexity:
Time: O(n log n)  (due to sorting, window traversal is O(n))  
Space: O(1)

Dry Run:
nums = [1, 2, 4], k = 5  
Sorted nums = [1, 2, 4]

Step 1: right = 0 → sum = 1  
Window = [1], cost = 1*1 - 1 = 0 ≤ 5 → maxFreq = 1  

Step 2: right = 1 → sum = 3  
Window = [1,2], cost = 2*2 - 3 = 1 ≤ 5 → maxFreq = 2  

Step 3: right = 2 → sum = 7  
Window = [1,2,4], cost = 3*4 - 7 = 5 ≤ 5 → maxFreq = 3  

Final Answer = 3
*/


package slidingWindow.q1838;

import java.util.*;

public class FrequencyOfTheMostFrequentElement {

    private static int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);
        int left = 0;
        long sum = 0;
        int maxFreq = 0;

        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            while ((right - left + 1) * (long) nums[right] - sum > k) {
                sum -= nums[left];
                left++;
            }
            int windowSize = right - left + 1;
            maxFreq = Math.max(maxFreq, windowSize);
        }

        return maxFreq;
    }

    public static void main(String[] args) {
        int[] nums = { 1, 2, 4 };
        int k = 5;
        System.out.println(maxFrequency(nums, k)); // Output: 3
    }

}
