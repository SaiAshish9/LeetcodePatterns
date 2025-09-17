/*
Input:
nums = [3, 4, 5, 1, 2]

Output:
1

Brute Force Approach:
- Iterate through the array from start to end.
- Find the minimum element by comparing each element.
- Example: for nums = [3, 4, 5, 1, 2] → min = 1

Complexity:
- Time: O(n)
- Space: O(1)

Optimal Approach:
- Use Binary Search:
  - Check mid element:
    - If nums[mid] > nums[right] → minimum is in the right half → left = mid + 1
    - Else → minimum is in the left half → right = mid
- Continue until left == right. Return nums[left].

Complexity:
- Time: O(log n)
- Space: O(1)

Dry Run:
nums = [3, 4, 5, 1, 2]
left = 0, right = 4

Iteration 1:
mid = 2 → nums[2]=5 > nums[4]=2 → left = mid+1 = 3

Iteration 2:
left=3, right=4 → mid=3 → nums[3]=1 ≤ nums[4]=2 → right=mid=3

End → left=right=3 → nums[3]=1
Result = 1
*/

#include <iostream>
using namespace std;

int findMin(int arr[], int n) {
    int left = 0, right = n - 1;

    while (left < right) {
        int mid = left + (right - left) / 2;

        if (arr[mid] > arr[right]) {
            left = mid + 1;
        } else {
            right = mid;
        }
    }

    return arr[left];
}

int main() {
    int arr1[] = {3, 4, 5, 1, 2};
    int n1 = sizeof(arr1) / sizeof(arr1[0]);
    cout << findMin(arr1, n1) << endl; // Output: 1

    int arr2[] = {4, 5, 6, 7, 0, 1, 2};
    int n2 = sizeof(arr2) / sizeof(arr2[0]);
    cout << findMin(arr2, n2) << endl; // Output: 0

    int arr3[] = {11, 13, 15, 17};
    int n3 = sizeof(arr3) / sizeof(arr3[0]);
    cout << findMin(arr3, n3) << endl; // Output: 11

    return 0;
}
