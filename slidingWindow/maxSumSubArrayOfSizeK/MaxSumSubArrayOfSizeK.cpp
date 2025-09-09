/*
Input: nums = [2,2,3,4], k = 2
Output: 7

Brute Force Approach:
- For every possible subarray of size k, calculate the sum.
- Keep track of the maximum sum found so far.
- Time Complexity: O(n^2), since for each starting index we compute the sum of k elements.

Optimal Approach (Sliding Window):
- Compute the sum of the first window of size k.
- Slide the window by one element at a time:
    -> Add the new element entering the window.
    -> Remove the element leaving the window.
- Update the maximum sum accordingly.
- Time Complexity: O(n), since each element is added and removed once.

Dry Run:
nums = [2, 2, 3, 4], k = 2

Brute Force:
- Subarray [2, 2] => sum = 4
- Subarray [2, 3] => sum = 5
- Subarray [3, 4] => sum = 7  -> max = 7

Sliding Window:
Initial window [2, 2] => sum = 4, max = 4
Slide to [2, 3]:
    sum = (prev sum 4) + 3 - 2 = 5, max = 5
Slide to [3, 4]:
    sum = (prev sum 5) + 4 - 2 = 7, max = 7

Final Answer = 7
*/

#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

// Brute Force O(n^2)
int maxSubArrayBruteForce(vector<int>& arr, int k) {
    int n = arr.size();
    int max_sum = 0;

    for (int i = 0; i <= n - k; i++) {
        int sum = 0;
        for (int j = 0; j < k; j++) {
            sum += arr[i + j];
        }
        max_sum = max(max_sum, sum);
    }
    return max_sum;
}

// Sliding Window O(n)
int maxSubArray(vector<int>& nums, int k) {
    int n = nums.size();
    int windowSum = 0;

    for (int i = 0; i < k; i++) {
        windowSum += nums[i];
    }
    int maxSum = windowSum;

    for (int i = k; i < n; i++) {
        windowSum += nums[i] - nums[i - k];
        maxSum = max(maxSum, windowSum);
    }
    return maxSum;
}

int main() {
    vector<int> nums = {2, 2, 3, 4};
    int k = 2;

    cout << maxSubArrayBruteForce(nums, k) << endl; // 7
    cout << maxSubArray(nums, k) << endl;           // 7

    return 0;
}
