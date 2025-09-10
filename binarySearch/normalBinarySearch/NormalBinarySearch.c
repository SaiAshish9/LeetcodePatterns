#include <stdio.h>

int search(int arr[], int n, int target) {
    int left = 0;
    int right = n - 1;

    while (left <= right) {
        int mid = left + (right - left) / 2; // prevents overflow

        if (arr[mid] == target) {
            return mid; // target found
        } else if (arr[mid] < target) {
            left = mid + 1; // search in right half
        } else {
            right = mid - 1; // search in left half
        }
    }

    return -1; // target not found
}

int main() {
    int arr[] = {-1, 0, 3, 5, 9, 12};
    int n = sizeof(arr) / sizeof(arr[0]);
    int target = 9;

    printf("%d\n", search(arr, n, target)); // Output: 4
    return 0;
}
