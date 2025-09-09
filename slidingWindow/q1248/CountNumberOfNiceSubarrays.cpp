/*
Input:
nums = [1, 1, 2, 1, 1], k = 3

Output:
2

Brute Force Approach:
- Generate all subarrays.
- For each subarray, count the number of odd numbers.
- If the count of odd numbers == k, increase the answer.
Complexity:
- Time Complexity: O(n^2)
- Space Complexity: O(1)

Optimal Approach (Sliding Window + AtMost Trick):
- A subarray has exactly k odd numbers = (number of subarrays with at most k odd numbers) 
  - (number of subarrays with at most k-1 odd numbers).
- Use a sliding window `atMost(nums, k)` function:
  - Maintain a window with ≤ k odd numbers.
  - If odd count exceeds k, move `left` forward until valid again.
  - Add all subarrays ending at `right` to the result.
Complexity:
- Time Complexity: O(n)
- Space Complexity: O(1)

Dry Run:
nums = [1, 1, 2, 1, 1], k = 3

atMost(nums, 3):
 right=0 → odd=1 → res=1
 right=1 → odd=2 → res=3
 right=2 → odd=2 → res=6
 right=3 → odd=3 → res=10
 right=4 → odd=4 → shrink left → odd=3 → res=14

atMost(nums, 2):
 right=0 → odd=1 → res=1
 right=1 → odd=2 → res=3
 right=2 → odd=2 → res=6
 right=3 → odd=3 → shrink left → odd=2 → res=9
 right=4 → odd=3 → shrink left → odd=2 → res=12

Result = atMost(3) - atMost(2) = 14 - 12 = 2
*/

#include <iostream>
#include <vector>
using namespace std;

int atMost(const vector<int>& nums, int k) {
    int res = 0;
    int left = 0;

    for (int right = 0; right < nums.size(); right++) {
        if (nums[right] % 2 == 1) {
            --k;
        }

        while (k < 0) {
            if (nums[left] % 2 == 1) {
                ++k;
            }
            left++;
        }

        res += right - left + 1;
    }

    return res;
}

int countNumberOfSubArrays(const vector<int>& nums, int k) {
    return atMost(nums, k) - atMost(nums, k - 1);
}

int main() {
    int arr[] = {1, 1, 2, 1, 1};
    int n = sizeof(arr) / sizeof(arr[0]);
    vector<int> nums(arr, arr + n);
    int k = 3;

    int result = countNumberOfSubArrays(nums, k);
    cout << result << endl; // Output: 2

    return 0;
}
