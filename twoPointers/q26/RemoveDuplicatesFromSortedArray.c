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
