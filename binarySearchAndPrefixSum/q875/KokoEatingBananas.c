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

#include <stdio.h>
#include <limits.h>

int getMax(int piles[], int n) {
    int maxVal = piles[0];
    for (int i = 1; i < n; i++) {
        if (piles[i] > maxVal) {
            maxVal = piles[i];
        }
    }
    return maxVal;
}

int canFinish(int piles[], int n, int k, int h) {
    long long hours = 0;
    for (int i = 0; i < n; i++) {
        hours += (piles[i] + (long long)k - 1) / k; // ceil(piles[i] / k)
        if (hours > h) return 0; // false
    }
    return 1; // true
}

int minEatingSpeed(int piles[], int n, int h) {
    int low = 1;
    int high = getMax(piles, n);
    int answer = high;

    while (low <= high) {
        int mid = low + (high - low) / 2;
        if (canFinish(piles, n, mid, h)) {
            answer = mid;
            high = mid - 1;
        } else {
            low = mid + 1;
        }
    }
    return answer;
}

int main() {
    int piles[] = {3, 6, 7, 11};
    int n = sizeof(piles) / sizeof(piles[0]);
    int h = 8;

    printf("%d\n", minEatingSpeed(piles, n, h)); // Output: 4
    return 0;
}
