/*
Problem: LeetCode 1482 - Minimum Number of Days to Make m Bouquets

Input:
bloomDay = [1, 10, 3, 10, 2], m = 3, k = 1

Output:
3

------------------------------------------------------------
Brute Force Approach:
- Start from day = 1, go until max(bloomDay).
- For each day, check if it’s possible to make m bouquets of size k.
- The first day for which it's possible is the answer.
- If never possible, return -1.

Complexity:
Time: O(n * max(bloomDay))  → too large (since bloomDay[i] ≤ 1e9)
Space: O(1)

------------------------------------------------------------
Optimal Approach (Binary Search):
- Search in range [1, max(bloomDay)] using binary search.
- For each mid = (low + high) / 2, check if it’s possible to make m bouquets by that day.
- If possible, try smaller days (high = mid - 1).
- Otherwise, increase days (low = mid + 1).
- Return the smallest valid day.

Complexity:
Time: O(n log(max(bloomDay)))
Space: O(1)

------------------------------------------------------------
Dry Run:
bloomDay = [1, 10, 3, 10, 2], m = 3, k = 1

low = 1, high = 1e9
mid = 5
    → Check if possible by day 5:
      Flowers blooming ≤ 5 = [1, 3, 2]
      Each flower makes 1 bouquet (since k = 1)
      Total bouquets = 3 ✔ possible
    → result = 5, move left → high = 4

low = 1, high = 4
mid = 2
    → Flowers blooming ≤ 2 = [1, 2]
      Bouquets = 2 (need 3) ❌
    → move right → low = 3

low = 3, high = 4
mid = 3
    → Flowers blooming ≤ 3 = [1, 3, 2]
      Bouquets = 3 ✔ possible
    → result = 3, move left → high = 2

Loop ends → Answer = 3

------------------------------------------------------------
*/

#include <stdio.h>
#include <stdbool.h>

bool canMake(int bloomDay[], int n, int m, int k, int day) {
    int bouquets = 0;
    int flowers = 0;

    for (int i = 0; i < n; i++) {
        if (bloomDay[i] <= day) {
            flowers++;
            if (flowers == k) {
                bouquets++;
                flowers = 0;
            }
        } else {
            flowers = 0;
        }
    }

    return bouquets >= m;
}

int minDays(int bloomDay[], int n, int m, int k) {
    long long totalFlowers = (long long)m * k;
    if (totalFlowers > n)
        return -1; // Not enough flowers

    int low = 1, high = 1000000000; // 1e9
    int result = -1;

    while (low <= high) {
        int mid = low + (high - low) / 2;
        if (canMake(bloomDay, n, m, k, mid)) {
            result = mid;
            high = mid - 1; // Try earlier day
        } else {
            low = mid + 1; // Need more time
        }
    }

    return result;
}

int main() {
    int bloomDay[] = {1, 10, 3, 10, 2};
    int n = sizeof(bloomDay) / sizeof(bloomDay[0]);
    int m = 3, k = 1;

    printf("%d\n", minDays(bloomDay, n, m, k)); // Output: 3
    return 0;
}
