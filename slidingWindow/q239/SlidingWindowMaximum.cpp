#include <iostream>
#include <vector>
#include <deque>
using namespace std;

vector<int> maxSlidingWindow(vector<int>& nums, int k) {
    int n = nums.size();
    if (k == 1) return nums;

    deque<int> dq; 
    vector<int> res;

    for (int i = 0; i < n; i++) {
        while (!dq.empty() && dq.front() < i - k + 1) {
            dq.pop_front();
        }
        while (!dq.empty() && nums[dq.back()] <= nums[i]) {
            dq.pop_back();
        }
        dq.push_back(i);

        if (i - k + 1 >= 0) {
            res.push_back(nums[dq.front()]);
        }
    }
    return res;
}

int main() {
    int arr[] = {1, 3, -1, -3, 5, 3, 6, 7};
    int n = sizeof(arr) / sizeof(arr[0]);
    vector<int> nums(arr, arr + n);
    int k = 3;

    vector<int> result = maxSlidingWindow(nums, k);

    cout << "[";
    for (size_t i = 0; i < result.size(); i++) {
        cout << result[i];
        if (i < result.size() - 1) cout << ", ";
    }
    cout << "]" << endl;

    return 0;
}
