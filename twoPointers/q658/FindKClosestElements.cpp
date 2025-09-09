/*
Input:
arr = [1, 2, 3, 4, 5], k = 4, x = 3
Output:
[1, 2, 3, 4]

Brute Force:
- Compute absolute difference between each element and x.
- Sort the array by:
    1) Smaller difference first.
    2) If equal difference, smaller number first.
- Pick the first k elements after sorting, then sort them again in ascending order.
- Time Complexity: O(n log n)
- Space Complexity: O(n)

Optimal:
- Use binary search to find the left boundary of the k closest elements.
- Search between indices [0, n-k].
- For each mid, compare distance from x to arr[mid] vs. arr[mid + k].
- Narrow down to correct left boundary.
- Final answer = arr[left .. left + k - 1].
- Time Complexity: O(log(n-k) + k) ≈ O(log n + k)
- Space Complexity: O(1)

Dry Run:
arr = [1, 2, 3, 4, 5], k = 4, x = 3
left = 0, right = 1 (since n-k = 5-4=1)

- Iteration 1:
- mid = 0
- Check: x - arr[mid] = 3 - 1 = 2
       arr[mid + k] - x = arr[4] - 3 = 5 - 3 = 2
- Since 2 <= 2, move right = mid = 0
- Loop ends (left=0, right=0)
- Result = arr[0..3] = [1, 2, 3, 4]
*/

#include <iostream>
#include <vector>

using namespace std;

class FindKClosestElements
{
public:
    vector<int> findClosestElements(vector<int> &arr, int k, int x)
    {
        int left = 0;
        int right = arr.size() - k;

        while (left < right)
        {
            int mid = left + (right - left) / 2;
            if (x - arr[mid] <= arr[mid + k] - x)
            {
                right = mid;
            }
            else
            {
                left = mid + 1;
            }
        }

        vector<int> result;
        for (int i = left; i < left + k; i++)
        {
            result.push_back(arr[i]);
        }
        return result;
    }
};

int main()
{
    int arr[] = {1, 2, 3, 4, 5};
    int n = sizeof(arr) / sizeof(arr[0]);

    vector<int> nums(arr, arr + n);
    int k = 4;
    int x = 3;

    FindKClosestElements solver;
    vector<int> ans = solver.findClosestElements(nums, k, x);

    cout << "[";
    for (int i = 0; i < ans.size(); i++)
    {
        cout << ans[i];
        if (i < ans.size() - 1)
            cout << ", ";
    }
    cout << "]";  // [1, 2, 3, 4]
}