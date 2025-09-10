/*
Input:
arr = [1, 5, 3, 4, 2], target = 3

Output:
true   // because (5 - 2 = 3) or (4 - 1 = 3)

Brute Force Approach:
- Iterate over all pairs (i, j) with i ≠ j
- Check if |arr[j] - arr[i]| == target
- If yes → return true
- Otherwise continue until all pairs are checked
Complexity:
Time = O(n^2), Space = O(1)

Optimal Approach (Two Pointers):
- Sort the array (important for two-pointer technique)
- Initialize two pointers: left = 0, right = 1
- While right < n:
    - Calculate diff = arr[right] - arr[left]
    - If diff == target and left != right → return true
    - If diff < target → move right++ (increase difference)
    - If diff > target → move left++ (decrease difference)
    - If left == right → move right++ (avoid same element)
- If loop ends without finding → return false
Complexity:
Time = O(n log n) (due to sorting) + O(n) (two-pointer scan) ≈ O(n log n)
Space = O(1)

Dry Run:
arr = [1, 5, 3, 4, 2], target = 3
After sorting → [1, 2, 3, 4, 5]
left = 0 (arr[left] = 1), right = 1 (arr[right] = 2)
diff = 2 - 1 = 1 < 3 → right++
left = 0, right = 2 → diff = 3 - 1 = 2 < 3 → right++
left = 0, right = 3 → diff = 4 - 1 = 3 == target → return true
*/

#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h> // for qsort

int compare(const void *a, const void *b) {
    return (*(int*)a - *(int*)b);
}

bool findPairWithGivenDifference(int arr[], int n, int target) {
    qsort(arr, n, sizeof(int), compare); // sort array using qsort

    int left = 0;
    int right = 1;

    while (right < n) {
        int diff = arr[right] - arr[left];

        if (diff == target && left != right) {
            return true;
        } else if (diff < target) {
            right++;
        } else {
            left++;
        }

        if (left == right) {
            right++;
        }
    }

    return false;
}

int main() {
    int arr[] = {1, 5, 3, 4, 2};
    int n = sizeof(arr) / sizeof(arr[0]);
    int target = 3;

    printf("%s\n", findPairWithGivenDifference(arr, n, target) ? "true" : "false");
    // Output: true

    return 0;
}
