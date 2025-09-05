/*
Input: nums = [0,0,1,1,1,2,2,3,3,4]
Output: 5, nums = [0,1,2,3,4,_,_,_,_,_]
Explanation: Your function should return k = 5, with the first five elements of nums being 0, 1, 2, 3, and 4 respectively
It does not matter what you leave beyond the returned k (hence they are underscores).

Brute Force:
1. Create a new array to store unique elements.
2. Iterate through the original array and add unique elements to the new array.             '
3. Copy the unique elements back to the original array.
4. Return the length of the new array.
Complexity Analysis:
Time Complexity: O(n)
Space Complexity: O(n)

Optimal Approach: Two Pointers
1. Use two pointers, one for iterating through the array (fast) and another for tracking the position of unique elements (slow).
2. Iterate through the array with the fast pointer.
3. If the current element is different from the previous one, move the slow pointer forward and update the value at the slow pointer.
4. Return the length of the unique elements (slow + 1).
Complexity Analysis:
Time Complexity: O(n)
Space Complexity: O(1)

Dry Run:
nums = [0,0,1,1,1,2,2,3,3,4]
        s
        f
nums = [0,1,1,1,1,2,2,3,3,4]
          s
                  f
nums = [0,1,2,1,1,2,2,3,3,4]
            s
                        f
nums = [0,1,2,3,1,2,2,3,3,4]
              s
                          f
nums = [0,1,2,3,4,1,2,2,3,3]
                s
                            f
Return 5
*/

#include <stdio.h>
#include <stdlib.h>

int removeDuplicates(int *nums, int n)
{
    if (n == 0)
        return 0;

    int slow = 0;
    for (int fast = 1; fast < n; fast++)
    {
        if (nums[fast] != nums[fast - 1])
        {
            nums[slow++] = nums[fast];
        }
    }
    return slow + 1;
}

int main()
{
    int nums[] = {1, 1, 2};
    int n = sizeof(nums) / sizeof(nums[0]);

    int newLength = removeDuplicates(nums, n);

    printf("New length: %d\n", newLength);
    printf("Modified array: ");
    for (int i = 0; i < newLength; i++)
    {
        printf("%d ", nums[i]);
    }
    printf("\n");

    return 0;
}
