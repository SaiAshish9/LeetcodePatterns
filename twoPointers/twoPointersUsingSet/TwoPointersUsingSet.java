/*
Input:
arr = [1, 2, 3, 4, 5], target = 8

Output:
true   // because 3 + 5 = 8

Brute Force Approach:
- Iterate over all pairs (i, j) with i < j
- If arr[i] + arr[j] == target → return true
- If no such pair exists → return false
Complexity:
Time = O(n^2), Space = O(1)

Optimal Approach (Using HashSet):
- Use a HashSet to store visited numbers
- For each num in arr:
    - Compute complement = target - num
    - If complement exists in HashSet → return true
    - Otherwise, add num to HashSet
- If no such pair is found → return false
Complexity:
Time = O(n), Space = O(n)

Dry Run:
arr = [1, 2, 3, 4, 5], target = 8
set = {}
num = 1 → complement = 7 → not in set → add 1 → set = {1}
num = 2 → complement = 6 → not in set → add 2 → set = {1,2}
num = 3 → complement = 5 → not in set → add 3 → set = {1,2,3}
num = 4 → complement = 4 → not in set → add 4 → set = {1,2,3,4}
num = 5 → complement = 3 → 3 exists in set → return true
*/

package twoPointers.twoPointersUsingSet;

import java.util.HashSet;

public class TwoPointersUsingSet {

    private static boolean findPair(int arr[], int target) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : arr) {
            int complement = target - num;
            if (set.contains(complement)) {
                return true;
            }
            set.add(num);
        }
        return false;
    }

    public static void main(String[] args) {
        int arr[] = { 1, 2, 3, 4, 5 };
        int target = 8;
        System.out.println(findPair(arr, target));
    }

}
