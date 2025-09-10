/*
Input:
nums = [2, 7, 11, 15], target = 9

Output:
"Pair found: (2, 7)"

Brute Force Approach:
- Check every possible pair (i, j) where i < j
- If nums[i] + nums[j] == target, return the pair
- Otherwise, continue until all pairs are checked
Complexity:
Time = O(n^2), Space = O(1)

Optimal Approach (Two Pointers):
- Sort the array (if not already sorted)
- Initialize two pointers: left = 0, right = n - 1
- While left < right:
    - Compute sum = nums[left] + nums[right]
    - If sum == target → return the pair
    - If sum < target → move left++ (to increase sum)
    - If sum > target → move right-- (to decrease sum)
- If no such pair exists, return "No pair found"
Complexity:
Time = O(n) (assuming array is sorted), Space = O(1)

Dry Run:
nums = [2, 7, 11, 15], target = 9
left = 0 (nums[left] = 2), right = 3 (nums[right] = 15)
sum = 2 + 15 = 17 > 9 → move right to 2
left = 0 (nums[left] = 2), right = 2 (nums[right] = 11)
sum = 2 + 11 = 13 > 9 → move right to 1
left = 0 (nums[left] = 2), right = 1 (nums[right] = 7)
sum = 2 + 7 = 9 == target → return "Pair found: (2, 7)"
*/

#include <iostream>
#include <vector>
#include <string>
using namespace std;

class StandardTwoPointers {
public:
    static string findPair(vector<int>& nums, int target) {
        int left = 0;
        int right = nums.size() - 1;

        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum == target) {
                return "Pair found: (" + to_string(nums[left]) + ", " + to_string(nums[right]) + ")";
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return "No pair found";
    }
};

int main() {
    int arr[] = {2, 7, 11, 15};
    int n = sizeof(arr) / sizeof(arr[0]);
    
    vector<int> nums(arr, arr + n);
    int target = 9;
    cout << StandardTwoPointers::findPair(nums, target) << endl; 
    // Output: Pair found: (2, 7)
    return 0;
}
