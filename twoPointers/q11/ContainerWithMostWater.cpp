#include <iostream>
#include <vector>

using namespace std;

int main()
{
    int arr[] = {1, 8, 6, 2, 5, 4, 8, 3, 7};
    int n = sizeof(arr) / sizeof(arr[0]);

    vector<int> height(arr, arr + n);
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
    cout << maxArea << endl;
}
