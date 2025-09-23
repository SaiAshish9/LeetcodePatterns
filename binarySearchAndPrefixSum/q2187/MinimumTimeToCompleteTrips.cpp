/*
Input:
time = [1, 2, 3]
totalTrips = 5

Output:
3

Brute Force Approach:
- Start from time = 1 and keep checking how many trips can be completed by that time.
- Increment time by 1 until totalTrips can be completed.
- Return the first time when totalTrips are completed.

Complexity:
- Time: O(totalTrips * n) in worst case, where n = time.length
- Space: O(1)

Optimal Approach:
- Use binary search on time.
- Minimum possible time is 1.
- Maximum possible time is min(time) * totalTrips (all trips done by the fastest bus).
- For each mid time, check if totalTrips can be completed.
- If yes, try smaller time (right = mid), else increase time (left = mid + 1).

Complexity:
- Time: O(n * log(maxTime)), where maxTime = min(time) * totalTrips
- Space: O(1)

Dry Run:
time = [1, 2, 3], totalTrips = 5
left = 1, right = 1 * 5 = 5
mid = (1 + 5) / 2 = 3
Check trips in 3 units of time:
  - bus 1: 3 / 1 = 3 trips
  - bus 2: 3 / 2 = 1 trip
  - bus 3: 3 / 3 = 1 trip
Total trips = 3 + 1 + 1 = 5 >= 5 ✅
So, right = mid = 3
Now, left = 1, right = 3
mid = 2
Trips in 2 units:
  - bus 1: 2 / 1 = 2
  - bus 2: 2 / 2 = 1
  - bus 3: 2 / 3 = 0
Total = 3 < 5 ❌
left = mid + 1 = 3
left == right, answer = 3
*/

#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

bool canCompleteTrips(const vector<int>& time, long long currentTime, int totalTrips) {
    long long tripsDone = 0;
    for (int t : time) {
        tripsDone += currentTime / t;
        if (tripsDone >= totalTrips) {
            return true; // Already enough trips
        }
    }
    return false;
}

long long minimumTime(const vector<int>& time, int totalTrips) {
    long long left = 1;
    long long right = (long long)(*min_element(time.begin(), time.end())) * totalTrips;

    while (left < right) {
        long long mid = left + (right - left) / 2;
        if (canCompleteTrips(time, mid, totalTrips)) {
            right = mid; // Try smaller time
        } else {
            left = mid + 1; // Need more time
        }
    }

    return left;
}

int main() {
    int arr[] = {1, 2, 3};
    vector<int> time(arr, arr + sizeof(arr) / sizeof(arr[0]));
    int totalTrips = 5;
    cout << minimumTime(time, totalTrips) << endl; // Output: 3
    return 0;
}
