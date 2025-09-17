/*
Input:
matrix = [
  [1, 3, 5, 7],
  [10, 11, 16, 20],
  [23, 30, 34, 60]
]
target = 3

Output:
true

Brute Force Approach:
- Iterate through every row and column.
- Compare each element with the target.
- If found, return true; otherwise, return false.

Complexity:
- Time: O(m * n)   (m = rows, n = columns)
- Space: O(1)

Optimal Approach:
- Treat the 2D matrix as a flattened sorted 1D array of size m*n.
- Perform binary search using indices:
    - midValue = matrix[mid / cols][mid % cols]
- Compare with target:
    - If equal → return true
    - If less → search right half
    - If greater → search left half

Complexity:
- Time: O(log(m * n))
- Space: O(1)

Dry Run:
matrix = [
  [1, 3, 5, 7],
  [10, 11, 16, 20],
  [23, 30, 34, 60]
]
target = 3

rows = 3, cols = 4
left = 0, right = 11

Iteration 1:
mid = 5 → midValue = matrix[5/4][5%4] = matrix[1][1] = 11
11 > 3 → right = 4

Iteration 2:
mid = 2 → midValue = matrix[2/4][2%4] = matrix[0][2] = 5
5 > 3 → right = 1

Iteration 3:
mid = 0 → midValue = matrix[0][0] = 1
1 < 3 → left = 1

Iteration 4:
mid = 1 → midValue = matrix[0][1] = 3
3 == 3 → return true

Result: true
*/

#include <stdio.h>
#include <stdbool.h>

bool searchMatrix(int matrix[][4], int rows, int cols, int target) {
    if (rows == 0 || cols == 0) {
        return false;
    }

    int left = 0, right = rows * cols - 1;
    while (left <= right) {
        int mid = left + (right - left) / 2;
        int midValue = matrix[mid / cols][mid % cols];

        if (midValue == target) {
            return true;
        } else if (midValue < target) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }
    return false;
}

int main() {
    int matrix[3][4] = {
        {1, 3, 5, 7},
        {10, 11, 16, 20},
        {23, 30, 34, 60}
    };
    int target = 3;
    printf("%s\n", searchMatrix(matrix, 3, 4, target) ? "true" : "false"); // true
    return 0;
}
