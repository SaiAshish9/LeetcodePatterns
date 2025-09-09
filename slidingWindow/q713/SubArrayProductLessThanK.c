#include <stdio.h>

/*
Input:
nums = [10, 5, 2, 6], k = 100

Output:
8

Brute Force Approach:
- Generate all possible subArrays and check if their product is < k.
- For each subArray, compute product in O(n), so overall O(n^2).
- Count all valid subArrays.
Complexity:
- Time Complexity: O(n^2)
- Space Complexity: O(1)

Optimal Approach (Sliding Window):
- Use two pointers (`left` and `right`) with a running product.
- Expand `right` by multiplying nums[right].
- If product >= k, shrink from the left until product < k.
- At each step, all subArrays ending at `right` and starting from [left...right] are valid.
- Add (right - left + 1) to the count.
Complexity:
- Time Complexity: O(n) since each element is processed at most twice.
- Space Complexity: O(1)

Dry Run:
nums = [10, 5, 2, 6], k = 100

right=0 → product=10 (<100), count += (0-0+1)=1
right=1 → product=50 (<100), count += (1-0+1)=2 → total=3
right=2 → product=100 (>=100), shrink:
    product/=10 → product=10, left=1
    count += (2-1+1)=2 → total=5
right=3 → product=60 (<100), count += (3-1+1)=3 → total=8

Final Answer = 8
*/

int getSubArrayProductLessThanK(int nums[], int n, int k)
{
    if (k <= 1)
        return 0;
    int left = 0;
    long long product = 1;
    int count = 0;

    for (int right = 0; right < n; right++)
    {
        product *= nums[right];

        while (product >= k && left <= right)
        {
            product /= nums[left];
            left++;
        }

        count += (right - left + 1);
    }

    return count;
}

int main()
{
    int nums[] = {10, 5, 2, 6};
    int n = sizeof(nums) / sizeof(nums[0]);
    int k = 100;

    printf("%d\n", getSubArrayProductLessThanK(nums, n, k)); // Output: 8
    return 0;
}
