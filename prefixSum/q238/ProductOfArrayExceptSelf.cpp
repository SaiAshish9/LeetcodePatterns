/*
Input:
nums = [1, 2, 3, 4]

Output:
[24, 12, 8, 6]

---------------------------------------------------
Brute Force Approach:
- For each index i, compute product of all elements except nums[i].
- This requires nested loops.

Steps:
1. Initialize an output array of size n.
2. For each i:
   - Multiply all nums[j] where j != i.
3. Store product in output[i].

Complexity:
- Time: O(n^2)  (two loops for each element)
- Space: O(1)   (ignoring output array)

---------------------------------------------------
Optimal Approach (Prefix + Postfix):
- Avoid redundant multiplications using prefix and postfix arrays.
- Use one pass left-to-right for prefix, and another right-to-left for postfix.

Steps:
1. Initialize `answer` array with 1.
2. Traverse left-to-right:
   - Store running prefix product in `answer[i]`.
   - Multiply prefix by nums[i].
3. Traverse right-to-left:
   - Multiply `answer[i]` by running postfix product.
   - Multiply postfix by nums[i].
4. Return `answer`.

Complexity:
- Time: O(n)  (two passes)
- Space: O(1) extra space (excluding output array)

---------------------------------------------------
Dry Run:
nums = [1, 2, 3, 4]
answer = [1, 1, 1, 1]

Pass 1 (prefix pass):
prefix = 1
i=0 → answer[0] = 1, prefix = 1*1=1
i=1 → answer[1] = 1, prefix = 1*2=2
i=2 → answer[2] = 2, prefix = 2*3=6
i=3 → answer[3] = 6, prefix = 6*4=24
answer = [1, 1, 2, 6]

Pass 2 (postfix pass):
postfix = 1
i=3 → answer[3] = 6*1=6, postfix = 1*4=4
i=2 → answer[2] = 2*4=8, postfix = 4*3=12
i=1 → answer[1] = 1*12=12, postfix = 12*2=24
i=0 → answer[0] = 1*24=24, postfix = 24*1=24
answer = [24, 12, 8, 6]

Final Output:
[24, 12, 8, 6]
*/

#include <iostream>
#include <vector>
using namespace std;

vector<int> productExceptSelf(vector<int>& nums) {
    int n = nums.size();
    vector<int> answer(n, 1);

    int prefix = 1;
    for (int i = 0; i < n; i++) {
        answer[i] = prefix;
        prefix *= nums[i];
    }

    int postfix = 1;
    for (int i = n - 1; i >= 0; i--) {
        answer[i] *= postfix;
        postfix *= nums[i];
    }

    return answer;
}

int main() {
    int arr[] = {1, 2, 3, 4};
    int n = sizeof(arr) / sizeof(arr[0]);
    vector<int> nums(arr, arr + n);
    vector<int> result = productExceptSelf(nums);

    cout << "[";
    for (int i = 0; i < result.size(); i++) {
        cout << result[i];
        if (i < result.size() - 1) cout << ", ";
    }
    cout << "]" << endl;

    // Output: [24, 12, 8, 6]
    return 0;
}
