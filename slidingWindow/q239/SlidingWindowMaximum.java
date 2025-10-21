package slidingWindow.q239;

/*
Input:
nums = [1,3,-1,-3,5,3,6,7], k = 3

Output:
[3,3,5,5,6,7]

Brute Force:
- For each sliding window of size k, compute the maximum by scanning all k elements.
- Time Complexity: O(n * k), since for each of the n-k+1 windows we do O(k) work.
- Space Complexity: O(1).

Optimal (Monotonic Deque):
- Maintain a deque (double-ended queue) that stores indices of useful elements for the current window.
- Ensure elements in the deque are in decreasing order of their values.
- Before adding a new element:
    - Remove indices that are out of the current window range.
    - Remove indices of elements smaller than or equal to the current element (since they are useless).
- The front of the deque always holds the index of the maximum element for the current window.
- Time Complexity: O(n) because each element is added and removed at most once.
- Space Complexity: O(k) for the deque.

Dry Run:
nums = [1,3,-1,-3,5,3,6,7], k=3

i=0, num=1 → deque=[0], not enough for window
i=1, num=3 → remove 1 since 3 >= 1 → deque=[1]
i=2, num=-1 → deque=[1,2], window ready → result[0]=nums[1]=3
i=3, num=-3 → deque=[1,2,3], window ready → result[1]=nums[1]=3
i=4, num=5 → remove -3, -1, 3 → deque=[4], result[2]=nums[4]=5
i=5, num=3 → deque=[4,5], result[3]=nums[4]=5
i=6, num=6 → remove 3, 5 → deque=[6], result[4]=nums[6]=6
i=7, num=7 → remove 6 → deque=[7], result[5]=nums[7]=7

Final Answer = [3,3,5,5,6,7]
*/

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class SlidingWindowMaximum {

    private static int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        if (n * k == 0)
            return new int[0];
        if (k == 1)
            return nums;

        Deque<Integer> deque = new LinkedList<>();
        int[] result = new int[n - k + 1];

        for (int i = 0; i < n; i++) {
            while (!deque.isEmpty() && deque.peek() < i - k + 1) {
                deque.poll();
            }

            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
                deque.pollLast();
            }

            deque.offer(i);

            if (i - k + 1 >= 0) {
                result[i - k + 1] = nums[deque.peek()];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int nums[] = { 1, 3, -1, -3, 5, 3, 6, 7 }, k = 3;
        System.out.println(Arrays.toString(maxSlidingWindow(nums, k))); // [3, 3, 5, 5, 6, 7]
    }
}
