/*
Input:
candies = [5, 8, 6], k = 3
Output:
5

Brute Force Approach:
- We try all possible answers from 1 to max(candies).
- For each possible allocation size `x`, check if it's possible to divide candies into at least `k` groups of size `x`.
- Keep track of the largest feasible `x`.
Complexity:
O(max(candies) * n), which is too slow if max(candies) is very large.

Optimal Approach:
- Use Binary Search on the answer (1 to max(candies)).
- For a mid value, check if we can divide candies into at least k children where each gets mid candies.
- If yes, increase search range (mid + 1), else decrease (mid - 1).
- Return the maximum feasible mid.
Complexity:
O(n log(max(candies))) where n = length of candies.

Dry Run:
candies = [5, 8, 6], k = 3

Step 1: max(candies) = 8
Search space = [1, 8]

Iteration 1:
left = 1, right = 8 → mid = 4
Check if possible:
5/4 + 8/4 + 6/4 = 1 + 2 + 1 = 4 ≥ 3 ✅
So ans = 4, move left = 5

Iteration 2:
left = 5, right = 8 → mid = 6
Check if possible:
5/6 + 8/6 + 6/6 = 0 + 1 + 1 = 2 < 3 ❌
So right = 5

Iteration 3:
left = 5, right = 5 → mid = 5
Check if possible:
5/5 + 8/5 + 6/5 = 1 + 1 + 1 = 3 ≥ 3 ✅
So ans = 5, move left = 6

Now left = 6, right = 5 → exit loop

Final Answer = 5
*/

#include <stdio.h>

int canDistribute(int candies[], int n, long k, int mid) {
    long count = 0;
    for (int i = 0; i < n; i++) {
        count += candies[i] / mid;
        if (count >= k) return 1; // true
    }
    return 0; // false
}

int maximumCandies(int candies[], int n, long k) {
    int max = 0;
    for (int i = 0; i < n; i++) {
        if (candies[i] > max) {
            max = candies[i];
        }
    }

    int left = 1, right = max, ans = 0;
    while (left <= right) {
        int mid = left + (right - left) / 2;
        if (canDistribute(candies, n, k, mid)) {
            ans = mid;
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }
    return ans;
}

int main() {
    int arr1[] = {5, 8, 6};
    int n1 = sizeof(arr1) / sizeof(arr1[0]);
    printf("%d\n", maximumCandies(arr1, n1, 3)); // 5

    int arr2[] = {2, 5};
    int n2 = sizeof(arr2) / sizeof(arr2[0]);
    printf("%d\n", maximumCandies(arr2, n2, 11)); // 0

    return 0;
}
