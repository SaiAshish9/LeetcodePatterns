/*
Input:
x = 8

Output:
2

Brute Force Approach:
- Start from 1 and increment i until i*i > x.
- Return i-1 as the integer square root.
- Example: for x = 8 → check 1^2=1, 2^2=4, 3^2=9 (too big) → answer = 2.

Complexity:
- Time: O(√x)
- Space: O(1)

Optimal Approach:
- Use Binary Search between [0, x].
- Keep track of the largest mid such that mid*mid <= x.
- Avoid overflow by comparing mid <= x/mid instead of mid*mid <= x.

Complexity:
- Time: O(log x)
- Space: O(1)

Dry Run:
x = 8

left = 0, right = 8, res = 0
mid = 4 → 4 <= 8/4=2 (false) → right = 3
mid = 2 → 2 <= 8/2=4 (true) → res=2, left=3
mid = 3 → 3 <= 8/3=2 (false) → right=2
End → res=2

Result = 2
*/

#include <stdio.h>

int mySqrt(int x) {
    if (x == 0) return 0;
    if (x == 1) return 1;

    int left = 0, right = x;
    int res = 0;

    while (left <= right) {
        int mid = left + (right - left + 1) / 2;

        if (mid <= x / mid) {
            res = mid;
            left = mid + 1;  // try to find a bigger square root
        } else {
            right = mid - 1;
        }
    }

    return res;
}

int main() {
    printf("%d\n", mySqrt(8)); // Output: 2
    printf("%d\n", mySqrt(1)); // Output: 1
    printf("%d\n", mySqrt(4)); // Output: 2
    return 0;
}
