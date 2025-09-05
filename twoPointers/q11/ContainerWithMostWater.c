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

Dry Run:
1. Initialize left = 0, right = 8, maxArea = 0.
2. Calculate area: width = 8, height = min(1, 7) = 1, area = 8 * 1 = 8, maxArea = 8             
3. Move left pointer: left = 1, right = 8
4. Calculate area: width = 7, height = min(8, 7) = 7, area = 7 * 7 = 49, maxArea = 49
5. Move right pointer: left = 1, right = 7
6. Calculate area: width = 6, height = min(8, 3) = 3, area = 6 * 3 = 18, maxArea = 49
7. Move right pointer: left = 1, right = 6
8. Calculate area: width = 5, height = min(8, 8) = 8, area = 5 * 8 = 40, maxArea = 49
9. Move right pointer: left = 1, right = 5
10. Calculate area: width = 4, height = min(8, 4) = 4, area = 4 * 4 = 16, maxArea = 49
11. Move right pointer: left = 1, right = 4
12. Calculate area: width = 3, height = min(8, 5) = 5, area = 3 * 5 = 15, maxArea = 49
13. Move right pointer: left = 1, right = 3
14. Calculate area: width = 2, height = min(8, 2) = 2, area = 2 * 2 = 4, maxArea = 49
15. Move right pointer: left = 1, right = 2                                         
16. Calculate area: width = 1, height = min(8, 6) = 6, area = 1 * 6 = 6, maxArea = 49
17. Move right pointer: left = 1, right = 1
18. Exit loop as left is not less than right.   
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
