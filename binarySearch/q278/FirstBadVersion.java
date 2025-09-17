/*
Input:
n = 5
Bad versions start from version 4

Output:
4

Brute Force Approach:
- Check each version from 1 to n sequentially.
- Return the first version that is bad.

Complexity:
- Time: O(n)  (check each version)
- Space: O(1)

Optimal Approach (Binary Search):
- Initialize left = 1, right = n.
- While left < right:
    - Find mid = left + (right - left) / 2
    - If mid is a bad version, the first bad version is at mid or before, so move right = mid.
    - Else, move left = mid + 1.
- Return left as the first bad version.

Complexity:
- Time: O(log n)
- Space: O(1)

Dry Run:
n = 5, first bad version = 4
left = 1, right = 5

1st iteration:
mid = 3, isBadVersion(3) = false
left = mid + 1 = 4

2nd iteration:
mid = 4, isBadVersion(4) = true
right = mid = 4

Loop ends (left == right)
Return 4
*/

package binarySearch.q278;

public class FirstBadVersion {

    private static boolean isBadVersion(int version) {
        return version >= 4;
    }

    private static int firstBadVersion(int n) {
        int left = 1, right = n;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (isBadVersion(mid)) {
                right = mid; // The first bad version is at mid or before
            } else {
                left = mid + 1; // The first bad version is after mid
            }
        }

        return left;
    }

    public static void main(String[] args) {
        System.out.println(firstBadVersion(5)); // Output: 4
    }

}
