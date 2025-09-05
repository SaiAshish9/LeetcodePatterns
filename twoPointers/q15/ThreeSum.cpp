/*
Input: nums = [-1,0,1,2,-1,-4]
Output: [[-1,-1,2],[-1,0,1]]

Brute Force: O(n^3) time complexity, O(1) space complexity
1. Sort the array.
2. Use three nested loops to find all unique triplets that sum to zero.     
3. Skip duplicates to ensure uniqueness.        

Optimal Approach: O(n^2) time complexity, O(1) space complexity
1. Sort the array.              
2. Fix one element and use the two-pointer technique to find pairs that sum to the negative of the fixed element.
3. Skip duplicates to ensure uniqueness.                        

Dry Run:   
 
nums = [-1,0,1,2,-1,-4]
Sorted nums = [-4,-1,-1,0,1,2]                                                                                                      
i = 0, nums[i] = -4
left = 1, right = 5, target = 4
-4 + -1 + 2 < 0, left++
left = 2, right = 5
-4 + -1 + 2 < 0, left++
left = 3, right = 5
-4 + 0 + 2 < 0, left++
left = 4, right = 5
-4 + 1 + 2 < 0, left++
left = 5, right = 5 (exit while loop)
i = 1, nums[i] = -1
left = 2, right = 5, target = 1

-1 + -1 + 2 == 0, add [-1,-1,2]

] to result, skip duplicates
left = 3, right = 4

-1 + 0 + 1 == 0, add [-1,0,1] to result, skip duplicates

left = 4, right = 3 (exit while loop)
i = 2, nums[i] = -1 (skip duplicate)
i = 3, nums[i] = 0
left = 4, right = 5, target = 0
0 + 1 + 2 > 0, right--
left = 4, right = 4 (exit while loop)       
*/

#include <iostream>
#include <vector>

using namespace std;

vector<vector<int>> threeSum(vector<int> &nums)
{
    sort(nums.begin(), nums.end());
    int n = nums.size();
    vector<vector<int>> result;
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
                result.push_back({nums[i], nums[left], nums[right]});
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
    return result;
}

int main()
{
    int nums[] = {-1, 0, 1, 2, -1, -4};
    vector<int> vec(nums, nums + sizeof(nums) / sizeof(nums[0]));
    vector<vector<int>> result = threeSum(vec);

    for (size_t i = 0; i < result.size(); i++)
    {
        cout << "[";
        for (size_t j = 0; j < result[i].size(); j++)
        {
            cout << result[i][j];
            if (j + 1 < result[i].size())
                cout << ", ";
        }
        cout << "] ";
    }

    cout << endl;
    return 0;
}