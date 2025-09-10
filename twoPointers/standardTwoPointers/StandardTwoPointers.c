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

#include <stdio.h>

const char *findPair(int nums[], int n, int target, char result[])
{
    int left = 0;
    int right = n - 1;

    while (left < right)
    {
        int sum = nums[left] + nums[right];
        if (sum == target)
        {
            // Store formatted result in buffer
            sprintf(result, "Pair found: (%d, %d)", nums[left], nums[right]);
            return result;
        }
        else if (sum < target)
        {
            left++;
        }
        else
        {
            right--;
        }
    }
    sprintf(result, "No pair found");
    return result;
}

int main()
{
    int nums[] = {2, 7, 11, 15};
    int n = sizeof(nums) / sizeof(nums[0]);
    int target = 9;

    char result[50]; // buffer for storing output string
    printf("%s\n", findPair(nums, n, target, result));
    // Output: Pair found: (2, 7)

    return 0;
}
