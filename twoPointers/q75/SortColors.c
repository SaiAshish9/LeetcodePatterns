/*
Input: nums = [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]

Brute Force: Sort the array using any sorting algorithm. O(nlogn)

Optimal Approach: Dutch National Flag Algorithm (Three Pointers: low, mid, high)
Time Complexity: O(n)
Space Complexity: O(1)

Dry Run:
Initial: nums = [2,0,2,1,1,0], low = 0, mid = 0, high = 5
1st Iteration: nums = [0,0,2,1,1,2], low = 1, mid = 1, high = 4
2nd Iteration: nums = [0,0,2,1,1,2], low = 1, mid = 2, high = 4
3rd Iteration: nums = [0,0,1,1,2,2], low = 1, mid = 3, high = 3
4th Iteration: nums = [0,0,1,1,2,2], low = 2, mid = 4, high = 2
End: nums = [0,0,1,1,2,2]
*/

#include <stdio.h>

void swap(int nums[], int i, int j)
{
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
}

int main()
{
    int nums[] = {2, 0, 2, 1, 1, 0};
    int n = sizeof(nums) / sizeof(nums[0]);

    int low = 0, mid = 0, high = n - 1;
    while (low <= high && mid <= high)
    {
        if (nums[mid] == 0)
        {
            swap(nums, low, mid);
            low++;
            mid++;
        }
        else if (nums[mid] == 1)
        {
            mid++;
        }
        else
        {
            swap(nums, mid, high);
            high--;
        }
    }

    printf("[");
    for (int i = 0; i < n; i++)
    {
        printf("%d", nums[i]);
        if (i + 1 < n)
            printf(", ");
    }
    printf("]\n");

    return 0;
}
