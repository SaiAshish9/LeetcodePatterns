/*
Problem: LeetCode 875 - Koko Eating Bananas

Input:
piles = [3, 6, 7, 11], h = 8

Output:
4

------------------------------------------------------------
Brute Force Approach:
- Try every possible eating speed `k` from 1 to max(piles).
- For each speed, calculate total hours needed.
- Return the minimum speed that allows Koko to finish within h hours.

Complexity:
Time: O(n * max(pile))  → too large (since max(pile) can be 10^9)
Space: O(1)

------------------------------------------------------------
Optimal Approach (Binary Search):
- Search in the range [1, max(piles)].
- Use binary search to find the smallest `k` such that Koko can finish in `h` hours.
- Check feasibility using a helper function `canFinish`.

Complexity:
Time: O(n * log(max(pile)))
Space: O(1)

------------------------------------------------------------
Dry Run:
piles = [3, 6, 7, 11], h = 8

low = 1, high = 11
mid = 6 → total hours = ceil(3/6)+ceil(6/6)+ceil(7/6)+ceil(11/6) = 1+1+2+2 = 6 <= 8 ✔
   → answer = 6, move left (high = 5)

low = 1, high = 5
mid = 3 → hours = ceil(3/3)+ceil(6/3)+ceil(7/3)+ceil(11/3) = 1+2+3+4 = 10 > 8 ❌
   → move right (low = 4)

low = 4, high = 5
mid = 4 → hours = ceil(3/4)+ceil(6/4)+ceil(7/4)+ceil(11/4) = 1+2+2+3 = 8 ✔
   → answer = 4, move left (high = 3)

Loop ends → final answer = 4

------------------------------------------------------------
*/

package binarySearchAndPrefixSum.q875;

import java.util.Arrays;

public class KokoEatingBananas {

    private static int minEatingSpeed(int[] piles, int h) {
        int low = 1;
        int high = Arrays.stream(piles).max().getAsInt();
        int answer = high;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (canFinish(piles, mid, h)) {
                answer = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return answer;
    }

    private static boolean canFinish(int[] piles, int k, int h) {
        long hours = 0;
        for (int pile : piles) {
            hours += (pile + (long) k - 1) / k; // equivalent to ceil(pile / k)
            if (hours > h)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int[] piles = { 3, 6, 7, 11 };
        int h = 8;
        System.out.println(minEatingSpeed(piles, h)); // Output: 4
    }

}
