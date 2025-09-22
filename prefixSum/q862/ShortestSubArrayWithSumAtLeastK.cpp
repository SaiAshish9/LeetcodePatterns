/*
Input:
nums = [2, -1, 2], K = 3
Output:
3
Explanation: The shortest subarray with sum at least 3 is the whole array [2, -1, 2].

Brute Force Approach:
- Generate all subarrays and calculate their sums.
- Track the minimum length of subarrays whose sum >= K.
Complexity:
- Time: O(n^2)
- Space: O(1)

Optimal Approach (Using Prefix Sum + Monotonic Queue):
- Compute prefix sum array.
- Use a deque to maintain indices of prefix sums in increasing order.
- For each prefixSum[i]:
    1. Remove indices from front while prefixSum[i] - prefixSum[deque.front()] >= K
       → Update minLength.
    2. Remove indices from back while prefixSum[i] <= prefixSum[deque.back()]
       → Maintain increasing order in deque.
    3. Add current index i to deque.
- Return minLength if found, otherwise -1.
Complexity:
- Time: O(n) → Each index is added/removed from deque at most once.
- Space: O(n) → For prefix sum array and deque.

Dry Run:
nums = [2, -1, 2], K = 3
prefixSum = [0, 2, 1, 3]

i = 0 → deque = [0]
i = 1 → deque = [0, 1]
i = 2 → deque = [0, 1, 2]
i = 3 → prefixSum[3] - prefixSum[0] = 3 >= 3 → minLength = 3, deque after pollFirst = [1,2]
         deque after adding 3 = [1,2,3]
Return 3
*/

#include <iostream>
#include <vector>
#include <deque>
using namespace std;

int shortestSubarray(vector<int>& nums, int K) {
    int n = nums.size();
    vector<long long> prefixSum(n + 1, 0);
    for (int i = 0; i < n; i++) {
        prefixSum[i + 1] = prefixSum[i] + nums[i];
    }

    deque<int> dq;
    int minLength = INT_MAX;

    for (int i = 0; i <= n; i++) {
        while (!dq.empty() && prefixSum[i] - prefixSum[dq.front()] >= K) {
            minLength = min(minLength, i - dq.front());
            dq.pop_front();
        }
        while (!dq.empty() && prefixSum[i] <= prefixSum[dq.back()]) {
            dq.pop_back();
        }
        dq.push_back(i);
    }

    return (minLength == INT_MAX) ? -1 : minLength;
}

int main() {
    int arr[] = {2, -1, 2};
    vector<int> nums(arr, arr + sizeof(arr) / sizeof(arr[0]));
    int K = 3;
    cout << shortestSubarray(nums, K) << endl; // Output: 3
    return 0;
}