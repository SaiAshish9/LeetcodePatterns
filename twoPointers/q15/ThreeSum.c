#include <stdio.h>
#include <stdlib.h>

int comparator(const void *a, const void *b)
{
    return (*(int *)a - *(int *)b);
}

int threeSum(int nums[], int n, int result[][3])
{
    qsort(nums, n, sizeof(int), comparator);
    int count = 0;

    for (int i = 0; i < n - 2; i++)
    {
        if (i > 0 && nums[i] == nums[i - 1])
            continue;

        int left = i + 1;
        int right = n - 1;
        int target = -nums[i];

        while (left < right)
        {
            int sum = nums[left] + nums[right];

            if (sum == target)
            {
                result[count][0] = nums[i];
                result[count][1] = nums[left];
                result[count][2] = nums[right];
                count++;

                while (left < right && nums[left] == nums[left + 1])
                    left++;
                while (left < right && nums[right] == nums[right - 1])
                    right--;

                left++;
                right--;
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
    }
    return count;
}

int main()
{
    int nums[] = {-1, 0, 1, 2, -1, -4};
    int n = sizeof(nums) / sizeof(nums[0]);

    int result[100][3];
    int count = threeSum(nums, n, result);

    for (int i = 0; i < count; i++)
    {
        printf("[%d, %d, %d] ", result[i][0], result[i][1], result[i][2]);
    }
    printf("\n");

    return 0;
}
