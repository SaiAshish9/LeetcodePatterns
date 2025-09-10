#include <iostream>
#include <vector>
using namespace std;

class BinarySearch
{
public:
    static int search(const vector<int> &arr, int target)
    {
        int left = 0;
        int right = arr.size() - 1;

        while (left <= right)
        {
            int mid = left + (right - left) / 2; // prevents overflow

            if (arr[mid] == target)
            {
                return mid; // target found
            }
            else if (arr[mid] < target)
            {
                left = mid + 1; // search in right half
            }
            else
            {
                right = mid - 1; // search in left half
            }
        }

        return -1; // target not found
    }
};

int main()
{
    int nums[] = {-1, 0, 3, 5, 9, 12};
    int n = sizeof(nums) / sizeof(nums[0]);
    vector<int> arr(nums, nums + n);
    int target = 9;
    cout << BinarySearch::search(arr, target) << endl; // Output: 4
    return 0;
}
