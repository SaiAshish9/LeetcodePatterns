/*
height = [1,8,6,2,5,4,8,3,7]
max_area = 49

Brute Force Approach:
1. Initialize max_area to 0.
2. Use two nested loops to iterate through all pairs of lines.
3. For each pair of lines, calculate the area formed between them.
4. Update max_area if the calculated area is greater than the current max_area.
5. Return max_area.
Time Complexity: O(n^2)
Space Complexity: O(1)  

Optimal Approach:
1. Initialize two pointers, left and right, at the beginning and end of the array, respectively.
2. Initialize max_area to 0.
3. While left is less than right:
   a. Calculate the width as right - left.
   b. Determine the height as the minimum of height[left] and height[right].
   c. Calculate the area as width * height.
   d. Update max_area if the calculated area is greater than the current max_area.
   e. Move the pointer pointing to the shorter line inward (left++ if height[left] < height[right], else right--).
4. Return max_area.
Time Complexity: O(n)
Space Complexity: O(1)
*/

#include <stdio.h>

int min(int a, int b)
{
    return (a < b) ? a : b;
}

int max(int a, int b)
{
    return (a > b) ? a : b;
}

int main()
{
    int height[] = {1, 8, 6, 2, 5, 4, 8, 3, 7};
    int n = sizeof(height) / sizeof(height[0]);

    int left = 0, right = n - 1;
    int maxArea = 0;

    while (left < right)
    {
        int width = right - left;
        int h = min(height[left], height[right]);
        int area = width * h;
        maxArea = max(maxArea, area);

        if (height[left] < height[right])
        {
            left++;
        }
        else
        {
            right--;
        }
    }

    printf("%d\n", maxArea);
    return 0;
}
