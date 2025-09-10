/*
Input:
nums = [2, 1, 3], k = 4

Output:
3

Brute Force Approach:
- Generate all possible subarrays.
- Calculate their sums and store them in a list.
- Sort the list and return the k-th smallest sum.
Example: nums = [2, 1, 3]
    Subarray sums = [2, 3, 6, 1, 4, 3]
    Sorted = [1, 2, 3, 3, 4, 6]
    4th smallest = 3
    
Complexity:
Time: O(n^2 log(n^2))  (O(n^2) subarrays, sorting them takes O(n^2 log n))  
Space: O(n^2) to store sums

Optimal Approach (Binary Search + Sliding Window):
1. The smallest subarray sum is the minimum element in nums.  
2. The largest subarray sum is the sum of the entire array.  
3. Use binary search between [min, totalSum] to find the k-th smallest subarray sum.  
4. For each mid, count how many subarray sums are ≤ mid using a sliding window:  
   - Expand the right pointer, adding nums[right] to the sum.  
   - Shrink from the left while sum > mid.  
   - Count valid subarrays ending at `right`.  
5. If the count < k, move low = mid + 1. Otherwise, move high = mid.  
6. At the end, low will be the k-th smallest sum.

Complexity:
Time: O(n log(sum(nums)))  
    - O(log(sum(nums))) for binary search range  
    - O(n) for counting subarrays each time  
Space: O(1)

Dry Run:
nums = [2, 1, 3], k = 4
min = 1, sum = 6
Binary search range: [1, 6]

mid = 3 → count subarrays ≤ 3  
    Subarrays ≤ 3 = [2], [1], [3], [2,1] → count = 4  
    Since count >= k, high = mid = 3  

Range: [1, 3]  
mid = 2 → count subarrays ≤ 2  
    Subarrays ≤ 2 = [2], [1] → count = 2  
    Since count < k, low = mid + 1 = 3  

Now low = high = 3 → answer = 3
*/

package slidingWindow.q1918;

public class KthSmallestSubArraySum {

    private static int kthSmallestSubarraySum(int[] nums, int k) {
        int min = Integer.MAX_VALUE, sum = 0;
        for (int num : nums) {
            min = Math.min(min, num);
            sum += num;
        }
        int low = min, high = sum;
        while (low < high) {
            int mid = (high - low) / 2 + low;
            int count = countSubarrays(nums, mid);
            if (count < k)
                low = mid + 1;
            else
                high = mid;
        }
        return low;
    }

    private static int countSubarrays(int[] nums, int threshold) {
        int count = 0;
        int sum = 0;
        int length = nums.length;
        int left = 0, right = 0;
        while (right < length) {
            sum += nums[right];
            while (sum > threshold) {
                sum -= nums[left];
                left++;
            }
            count += right - left + 1;
            right++;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(kthSmallestSubarraySum(new int[] { 2, 1, 3 }, 4)); // 3
    }

}
