/*
Input:
arr = [1, 2, 3, 4, 5], k = 4, x = 3
Output:
[1, 2, 3, 4]

Brute Force:
- Compute absolute difference between each element and x.
- Sort the array by:
    1) Smaller difference first.
    2) If equal difference, smaller number first.
- Pick the first k elements after sorting, then sort them again in ascending order.
- Time Complexity: O(n log n)
- Space Complexity: O(n)

Optimal:
- Use binary search to find the left boundary of the k closest elements.
- Search between indices [0, n-k].
- For each mid, compare distance from x to arr[mid] vs. arr[mid + k].
- Narrow down to correct left boundary.
- Final answer = arr[left .. left + k - 1].
- Time Complexity: O(log(n-k) + k) ≈ O(log n + k)
- Space Complexity: O(1)

Dry Run:
arr = [1, 2, 3, 4, 5], k = 4, x = 3
left = 0, right = 1 (since n-k = 5-4=1)

- Iteration 1:
- mid = 0
- Check: x - arr[mid] = 3 - 1 = 2
       arr[mid + k] - x = arr[4] - 3 = 5 - 3 = 2
- Since 2 <= 2, move right = mid = 0
- Loop ends (left=0, right=0)
- Result = arr[0..3] = [1, 2, 3, 4]
*/

#include <stdio.h>
#include <stdlib.h>

int* findClosestElements(int* arr, int n, int k, int x, int* returnSize) {
    int left = 0;
    int right = n - k;

    while (left < right) {
        int mid = left + (right - left) / 2;
        if (x - arr[mid] <= arr[mid + k] - x) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }

    int* result = (int*)malloc(k * sizeof(int));
    for (int i = 0; i < k; i++) {
        result[i] = arr[left + i];
    }

    *returnSize = k; 
    return result;
}

int main() {
    int arr[] = {1, 2, 3, 4, 5};
    int n = sizeof(arr) / sizeof(arr[0]);
    int k = 4;
    int x = 3;

    int returnSize = 0;
    int* ans = findClosestElements(arr, n, k, x, &returnSize);

    printf("[");
    for (int i = 0; i < returnSize; i++) {
        printf("%d", ans[i]);
        if (i < returnSize - 1) {
            printf(", ");
        }
    }
    printf("]\n"); // Output: [1, 2, 3, 4]
    free(ans); // free allocated memory
    return 0;
}
