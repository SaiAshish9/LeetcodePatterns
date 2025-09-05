/*
Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].

Brute Force: O(nlogn) + O(n^2) time | O(n) space
1. Sort the intervals based on the starting time.   O(nlogn)
2. Create a result list to store the merged intervals.
3. Iterate through the sorted intervals and for each interval:   
    a. If the result list is empty or the current interval does not overlap with the last interval in the result list, add it to the result list.
    b. If there is an overlap, merge the current interval with the last interval in the result list by updating the end time of the last interval to be the maximum of the two end times.   O(n^2)
4. Return the result list as an array. O(n) space for the result list.

Optimal Approach: O(nlogn) time | O(n) space
1. Sort the intervals based on the starting time.   O(nlogn)                            
2. Create a result list to store the merged intervals.
3. Initialize a variable to keep track of the current interval being merged.
4. Iterate through the sorted intervals starting from the second interval:
    a. If the current interval overlaps with the next interval, update the end time of the current interval to be the maximum of the two end times.
    b. If there is no overlap, add the current interval to the result list and update the current interval to be the next interval.  O(n)               
5. After the loop, add the last current interval to the result list.    O(n)        
6. Return the result list as an array. O(n) space for the result list.     

Dry Run:    
1. Sort the intervals: [[1,3],[2,6],[8,10],[15,18]] -> [[1,3],[2,6],[8,10],[15,18]]
2. Initialize result list: []
3. Set current interval to [1,3]
4. Iterate through intervals:
    a. Compare [1,3] with [2,6]: Overlap exists, update current interval to [1,6]
    b. Compare [1,6] with [8,10]: No overlap, add [1,6] to result list, update current interval to [8,10]
    c. Compare [8,10] with [15,18]: No overlap, add [8,10] to result list, update current interval to [15,18]
5. Add last current interval [15,18] to result list: [[1,6],[8,10],[15,18]]
6. Return result list as array: [[1,6],[8,10],[15,18]]
 */

#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

vector<vector<int>> merge(vector<vector<int>> &intervals)
{
    if (intervals.empty())
        return vector<vector<int>>();

    sort(intervals.begin(), intervals.end());

    vector<vector<int>> merged;
    vector<int> current = intervals[0];

    for (size_t i = 1; i < intervals.size(); i++)
    {
        vector<int> &next = intervals[i];

        if (next[0] <= current[1])
        {
            current[1] = max(current[1], next[1]);
        }
        else
        {
            merged.push_back(current);
            current = next;
        }
    }

    merged.push_back(current);
    return merged;
}

int main()
{
    vector<vector<int>> intervals;
    int a1[] = {1, 3};
    int a2[] = {2, 6};
    int a3[] = {8, 10};
    int a4[] = {15, 18};
    intervals.push_back(vector<int>(a1, a1 + 2));
    intervals.push_back(vector<int>(a2, a2 + 2));
    intervals.push_back(vector<int>(a3, a3 + 2));
    intervals.push_back(vector<int>(a4, a4 + 2));

    vector<vector<int>> result = merge(intervals);

    cout << "[";
    for (size_t i = 0; i < result.size(); i++)
    {
        cout << "[" << result[i][0] << ", " << result[i][1] << "]";
        if (i != result.size() - 1)
            cout << ", ";
    }
    cout << "]" << endl;

    return 0;
}
