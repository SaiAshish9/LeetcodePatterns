#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

vector<vector<int> > merge(vector<vector<int> >& intervals) {
    if (intervals.empty()) return vector<vector<int> >();

    sort(intervals.begin(), intervals.end());

    vector<vector<int> > merged;
    vector<int> current = intervals[0];

    for (size_t i = 1; i < intervals.size(); i++) {
        vector<int>& next = intervals[i];

        if (next[0] <= current[1]) {
            current[1] = max(current[1], next[1]);
        } else {
            merged.push_back(current);
            current = next;
        }
    }

    merged.push_back(current);
    return merged;
}

int main() {
    vector<vector<int> > intervals;
    int a1[] = {1, 3};
    int a2[] = {2, 6};
    int a3[] = {8, 10};
    int a4[] = {15, 18};
    intervals.push_back(vector<int>(a1, a1+2));
    intervals.push_back(vector<int>(a2, a2+2));
    intervals.push_back(vector<int>(a3, a3+2));
    intervals.push_back(vector<int>(a4, a4+2));

    vector<vector<int> > result = merge(intervals);

    cout << "[";
    for (size_t i = 0; i < result.size(); i++) {
        cout << "[" << result[i][0] << ", " << result[i][1] << "]";
        if (i != result.size() - 1) cout << ", ";
    }
    cout << "]" << endl;

    return 0;
}
