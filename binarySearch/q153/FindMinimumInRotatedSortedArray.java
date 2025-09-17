/*
Input:
nums = [3, 4, 5, 1, 2]

Output:
1

Brute Force Approach:
- Iterate through the array from start to end.
- Find the minimum element by comparing each element.
- Example: for nums = [3, 4, 5, 1, 2] → min = 1

Complexity:
- Time: O(n)
- Space: O(1)

Optimal Approach:
- Use Binary Search:
  - Check mid element:
    - If nums[mid] > nums[right] → minimum is in the right half → left = mid + 1
    - Else → minimum is in the left half → right = mid
- Continue until left == right. Return nums[left].

Complexity:
- Time: O(log n)
- Space: O(1)

Dry Run:
nums = [3, 4, 5, 1, 2]
left = 0, right = 4

Iteration 1:
mid = 2 → nums[2]=5 > nums[4]=2 → left = mid+1 = 3

Iteration 2:
left=3, right=4 → mid=3 → nums[3]=1 ≤ nums[4]=2 → right=mid=3

End → left=right=3 → nums[3]=1
Result = 1
*/

package binarySearch.q153;

public class FindMinimumInRotatedSortedArray {

    private static int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return nums[left];
    }

    public static void main(String[] args) {
        System.out.println(findMin(new int[] { 3, 4, 5, 1, 2 })); // Output: 1
    }

}
