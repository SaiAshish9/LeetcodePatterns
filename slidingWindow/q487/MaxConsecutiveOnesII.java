
/*
Input:
nums = [1, 0, 1, 1, 0]

Output:
4

Brute Force Approach:
- For each index, try flipping one `0` into a `1`, and count the maximum consecutive `1`s.
- For each element, we may scan the array → O(n^2) time.
- Space Complexity: O(1).

Optimal Approach (Sliding Window):
- Maintain a sliding window that allows at most 1 zero (`k = 1`).
- Expand the window by moving `right`. 
- If a zero is encountered, decrement `k`.
- If `k < 0`, shrink the window from the left until the window has at most 1 zero again.
- The maximum window length during this process is the answer.
- Time Complexity: O(n) (each element processed at most twice).
- Space Complexity: O(1).

Dry Run:
nums = [1, 0, 1, 1, 0], k=1

right=0 → nums[0]=1 → k=1 → window=[1], length=1
right=1 → nums[1]=0 → k=0 → window=[1,0], length=2
right=2 → nums[2]=1 → k=0 → window=[1,0,1], length=3
right=3 → nums[3]=1 → k=0 → window=[1,0,1,1], length=4
right=4 → nums[4]=0 → k=-1 → shrink from left:
    left=0 (nums[0]=1) → still k=-1
    left=1 (nums[1]=0) → now k=0
→ window=[1,1,0], length=3
Max length observed = 4

Answer = 4
*/

package slidingWindow.q487;

public class MaxConsecutiveOnesII {

    private static int findMaxConsecutiveOnes(int[] nums) {
        int left = 0, right = 0;
        int k = 1;
        while (right < nums.length) {
            if (nums[right++] == 0) {
                --k;
            }
            if (k < 0 && nums[left++] == 0) {
                ++k;
            }
        }
        return right - left;
    }

    public static void main(String[] args) {
        System.out.println(findMaxConsecutiveOnes(new int[] { 1, 0, 1, 1, 0 })); // 4
    }

}
