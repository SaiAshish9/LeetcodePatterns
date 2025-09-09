/*
Input: [1, 4, 2, 10, 2, 3, 1, 0, 20]
Output: 43

Brute Force:
- Consider every possible subarray.
- Calculate its sum and track the maximum.
- Time Complexity: O(n^2), since there are O(n^2) subarrays.

Optimal Approach (Kadane’s Algorithm):
- Keep a running sum (curr_sum).
- If curr_sum becomes negative, reset it to 0 (start a new subarray).
- Keep track of max_sum and also track the start and end indices of the subarray.
- Time Complexity: O(n).

Dry Run:
nums = [1, 4, 2, 10, 2, 3, 1, 0, 20]

Step 1: curr_sum = 1 → max_sum = 1, subarray = [1]
Step 2: curr_sum = 1+4 = 5 → max_sum = 5, subarray = [1, 4]
Step 3: curr_sum = 5+2 = 7 → max_sum = 7, subarray = [1, 4, 2]
Step 4: curr_sum = 7+10 = 17 → max_sum = 17, subarray = [1, 4, 2, 10]
Step 5: curr_sum = 17+2 = 19 → max_sum = 19, subarray = [1, 4, 2, 10, 2]
Step 6: curr_sum = 19+3 = 22 → max_sum = 22, subarray = [1, 4, 2, 10, 2, 3]
Step 7: curr_sum = 22+1 = 23 → max_sum = 23, subarray = [1, 4, 2, 10, 2, 3, 1]
Step 8: curr_sum = 23+0 = 23 → max_sum = 23, subarray = [1, 4, 2, 10, 2, 3, 1, 0]
Step 9: curr_sum = 23+20 = 43 → max_sum = 43, subarray = [1, 4, 2, 10, 2, 3, 1, 0, 20]

Final Answer:
Max Subarray = [1, 4, 2, 10, 2, 3, 1, 0, 20]
Max Sum = 43
*/

#include <iostream>
#include <vector>
#include <climits>
using namespace std;

int maxSubArraySum(const vector<int>& nums) {
    int n = nums.size();
    int curr_sum = 0, max_sum = INT_MIN;
    int start = 0, tempStart = 0, end = 0;

    for (int i = 0; i < n; i++) {
        curr_sum += nums[i];

        if (curr_sum > max_sum) {
            max_sum = curr_sum;
            start = tempStart;
            end = i;
        }

        if (curr_sum < 0) {
            curr_sum = 0;
            tempStart = i + 1;
        }
    }

    return max_sum;
}

int main() {
    vector<int> arr = {1, 4, 2, 10, 2, 3, 1, 0, 20};

    int result = maxSubArraySum(arr);
    cout << "Max Subarray Sum = " << result << endl; // 43

    return 0;
}
