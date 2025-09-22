/*
Input:
nums = [0, 1, 0, 1, 1, 0]

Output:
6   // because the entire array is a contiguous subarray with equal number of 0s and 1s

---------------------------------------------------
Brute Force Approach:
- Consider all subarrays.
- For each subarray, count 0s and 1s.
- If count0 == count1, update maxLength.

Complexity:
- Time: O(n^2)  (checking all subarrays)
- Space: O(1)

---------------------------------------------------
Optimal Approach (Prefix Sum + HashMap):
- Replace 0 with -1 (so problem reduces to finding longest subarray with sum = 0).
- Maintain prefix sum while traversing.
- Use a HashMap to store the first index where a prefix sum occurs.
- If the same prefix sum occurs again, the subarray between those indices has equal 0s and 1s.
- Track the maximum length.

Steps:
1. Initialize prefixSum = 0, maxLength = 0, and map {0 → -1}.
2. Traverse nums:
   - Add +1 for each 1, -1 for each 0.
   - If prefixSum seen before → calculate length = i - map[prefixSum].
   - Update maxLength if larger.
   - Else store prefixSum → i in map.
3. Return maxLength.

Complexity:
- Time: O(n)
- Space: O(n)

---------------------------------------------------
Dry Run:
nums = [0, 1, 0, 1, 1, 0]
Convert 0 → -1
nums = [-1, 1, -1, 1, 1, -1]

prefixSumMap = {0 : -1}
prefixSum = 0, maxLength = 0

i=0 → prefixSum = -1 → not in map → map[-1] = 0
i=1 → prefixSum = 0 → seen at -1 → length = 1 - (-1) = 2 → maxLength = 2
i=2 → prefixSum = -1 → seen at 0 → length = 2 - 0 = 2 → maxLength = 2
i=3 → prefixSum = 0 → seen at -1 → length = 3 - (-1) = 4 → maxLength = 4
i=4 → prefixSum = 1 → not in map → map[1] = 4
i=5 → prefixSum = 0 → seen at -1 → length = 5 - (-1) = 6 → maxLength = 6

Final Answer: 6
*/

#include <iostream>
#include <vector>
#include <unordered_map>
using namespace std;

int findMaxLength(vector<int> &nums)
{
    unordered_map<int, int> prefixSumMap;
    prefixSumMap[0] = -1;

    int prefixSum = 0;
    int maxLength = 0;

    for (int i = 0; i < nums.size(); i++)
    {
        prefixSum += (nums[i] == 1) ? 1 : -1;

        if (prefixSumMap.find(prefixSum) != prefixSumMap.end())
        {
            maxLength = max(maxLength, i - prefixSumMap[prefixSum]);
        }
        else
        {
            prefixSumMap[prefixSum] = i;
        }
    }

    return maxLength;
}

int main()
{

    int arr[] = {0, 1, 0, 1, 1, 0};
    int n = sizeof(arr) / sizeof(arr[0]);
    vector<int> nums(arr, arr + n);
    cout << findMaxLength(nums) << endl;
    // Output: 6
    return 0;
}
