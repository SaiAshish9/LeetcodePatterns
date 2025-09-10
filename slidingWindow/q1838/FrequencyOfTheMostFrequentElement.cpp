/*
Input: 
nums = [1, 2, 4], k = 5

Output: 
3

Brute Force Approach:
- Try to make every element the most frequent by incrementing smaller elements to match it.
- For each target element, calculate the total cost required to make others equal to it.
- If the cost ≤ k, update the maximum frequency.
- This involves nested loops and repeated calculations.

Complexity:
Time: O(n^2)  
Space: O(1)

Optimal Approach (Sliding Window + Sorting):
1. Sort the array so that we only need to consider increasing smaller numbers to match larger ones.  
2. Use a sliding window with two pointers (left and right).  
3. Maintain the sum of the current window.  
4. For each `right`, check if the total cost to make all elements in the window equal to `nums[right]` is ≤ k:  
   - Cost = (windowSize * nums[right]) - sum.  
5. If cost > k, shrink the window from the left until it becomes valid.  
6. Track the maximum valid window size.

Complexity:
Time: O(n log n)  (due to sorting, window traversal is O(n))  
Space: O(1)

Dry Run:
nums = [1, 2, 4], k = 5  
Sorted nums = [1, 2, 4]

Step 1: right = 0 → sum = 1  
Window = [1], cost = 1*1 - 1 = 0 ≤ 5 → maxFreq = 1  

Step 2: right = 1 → sum = 3  
Window = [1,2], cost = 2*2 - 3 = 1 ≤ 5 → maxFreq = 2  

Step 3: right = 2 → sum = 7  
Window = [1,2,4], cost = 3*4 - 7 = 5 ≤ 5 → maxFreq = 3  

Final Answer = 3
*/

#include <iostream>
#include <vector>
using namespace std;

class FrequencyOfTheMostFrequentElement {
public:
    static int maxFrequency(vector<int>& nums, int k) {
        sort(nums.begin(), nums.end());
        int left = 0;
        long long sum = 0;
        int maxFreq = 0;

        for (int right = 0; right < nums.size(); right++) {
            sum += nums[right];

            // Shrink the window if cost exceeds k
            while ((long long)(right - left + 1) * nums[right] - sum > k) {
                sum -= nums[left];
                left++;
            }

            int windowSize = right - left + 1;
            maxFreq = max(maxFreq, windowSize);
        }

        return maxFreq;
    }
};

int main() {
    int arr[] = {1, 2, 4};
    int n = sizeof(arr) / sizeof(arr[0]);
    vector<int> nums(arr, arr + n);
    int k = 5;
    cout << FrequencyOfTheMostFrequentElement::maxFrequency(nums, k) << endl; // Output: 3
    return 0;
}
