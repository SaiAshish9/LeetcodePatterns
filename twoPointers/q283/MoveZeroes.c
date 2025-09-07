/*
Input: nums = [0,1,0,3,12]
Output: [1,3,12,0,0]

Brute Force:
Time Complexity: O(n^2)
Space Complexity: O(1)
Approach: Iterate through the array and for each zero found, shift all subsequent elements to the left and
place zero at the end.

Optimal Approach: Two Pointers
Time Complexity: O(n)
Space Complexity: O(1)

Dry Run:
nums = [0,1,0,3,12]
slow = 0, fast = 0 -> nums[fast] == 0 -> do nothing
slow = 0, fast = 1 -> nums[fast] != 0 -> swap nums[slow] and nums[fast] -> nums = [1,0,0,3,12], slow = 1
slow = 1, fast = 2 -> nums[fast] == 0 -> do nothing
slow = 1, fast = 3 -> nums[fast] != 0 -> swap nums[slow] and nums[fast] -> nums = [1,3,0,0,12], slow = 2
slow = 2, fast = 4 -> nums[fast] != 0 -> swap nums[slow] and nums[fast] -> nums = [1,3,12,0,0], slow = 3
 */

#include <stdio.h>

void moveZeroes(int nums[], int n)
{
    int slow = 0;
    for (int fast = 0; fast < n; fast++)
    {
        if (nums[fast] != 0)
        {
            if (slow != fast)
            {
                int temp = nums[slow];
                nums[slow] = nums[fast];
                nums[fast] = temp;
            }
            slow++;
        }
    }
}

int main()
{
    int nums[] = {0, 1, 0, 3, 12};
    int n = sizeof(nums) / sizeof(nums[0]);

    moveZeroes(nums, n);

    for (int i = 0; i < n; i++)
    {
        printf("%d ", nums[i]);
    }

    return 0;
}
