package binarySearch.normalBinarySearch;

/*
Input:
arr = [-1, 0, 3, 5, 9, 12]
target = 9

Output:
4

Brute Force Approach:
- Traverse the array from start to end.
- Compare each element with the target.
- If found, return its index, else return -1.
Complexity: O(n) time, O(1) space.

Optimal Approach (Binary Search):
- Since the array is sorted, we can use Binary Search.
- Keep dividing the search range into halves.
- If the middle element matches the target, return its index.
- If the middle element is smaller, search the right half.
- If the middle element is larger, search the left half.
Complexity: O(log n) time, O(1) space.

Dry Run:
arr = [-1, 0, 3, 5, 9, 12], target = 9

Initial: left = 0, right = 5
mid = (0 + 5) / 2 = 2 → arr[2] = 3 < 9 → search right half

Now: left = 3, right = 5
mid = (3 + 5) / 2 = 4 → arr[4] = 9 == target → return 4
*/

public class NormalBinarySearch {

    private static int search(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

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

        return -1; // Target not found
    }

    public static void main(String[] args) {
        int[] arr = {-1, 0, 3, 5, 9, 12};
        int target = 9;
        System.out.println(search(arr, target)); // Output: 4
    }
}
