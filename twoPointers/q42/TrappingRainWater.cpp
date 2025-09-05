/*
Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1].
In this case, 6 units of rain water (blue section) are being trapped.

Brute Force: O(n^2)
1. For each element, find the maximum height to its left and right.
2. The water trapped on top of that element is the minimum of the two maximums minus the height of the element itself.
3. Sum up the water trapped for all elements.

Optimized: O(n) using two pointers

Dry Run:

Initial values:
left = 0, right = 11
left_max = 0, right_max = 0
waterTrapped = 0

Iteration breakdown:
1. height[left]=0 < height[right]=1
height[left]=0 >= left_max=0 → update left_max = 0
left=1

2. height[left]=1 < height[right]=1 → false (equal, so go else branch)
height[right]=1 >= right_max=0 → update right_max=1
right=10

3. height[left]=1 < height[right]=2
height[left]=1 >= left_max=0 → update left_max=1
left=2

4. height[left]=0 < height[right]=2
height[left]=0 < left_max=1 → trap 1-0=1
waterTrapped=1
left=3

5. height[left]=2 < height[right]=2 → false
height[right]=2 >= right_max=1 → update right_max=2
right=9

6. height[left]=2 < height[right]=1 → false
height[right]=1 < right_max=2 → trap 2-1=1
waterTrapped=2
right=8

7. height[left]=2 < height[right]=2 → false
height[right]=2 >= right_max=2 → update right_max=2
right=7

8. height[left]=2 < height[right]=3
height[left]=2 >= left_max=1 → update left_max=2
left=4

9. height[left]=1 < height[right]=3
height[left]=1 < left_max=2 → trap 2-1=1
waterTrapped=3
left=5

10. height[left]=0 < height[right]=3
height[left]=0 < left_max=2 → trap 2-0=2
waterTrapped=5
left=6

11. height[left]=1 < height[right]=3
height[left]=1 < left_max=2 → trap 2-1=1
waterTrapped=6
left=7
*/

#include <iostream>
#include <vector>
using namespace std;

class TrappingRainWater
{
public:
    int trap(vector<int> &height)
    {
        if (height.empty())
            return 0;

        int left = 0, right = height.size() - 1;
        int left_max = 0, right_max = 0;
        int waterTrapped = 0;

        while (left < right)
        {
            if (height[left] < height[right])
            {
                if (height[left] >= left_max)
                {
                    left_max = height[left];
                }
                else
                {
                    waterTrapped += left_max - height[left];
                }
                left++;
            }
            else
            {
                if (height[right] >= right_max)
                {
                    right_max = height[right];
                }
                else
                {
                    waterTrapped += right_max - height[right];
                }
                right--;
            }
        }

        return waterTrapped;
    }
};

int main()
{
    TrappingRainWater trappingRainWater;
    vector<int> height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
    cout << trappingRainWater.trap(height) << endl; // Output: 6
    return 0;
}
