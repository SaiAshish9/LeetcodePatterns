/*
Problem:
We are given an array of package weights and D days.
We must ship packages in order (no reordering). Each day, 
we can load a ship with packages such that the total weight 
does not exceed the ship's capacity. 
Return the minimum ship capacity required to ship all packages in D days.

---------------------------------------------------------------
Input:
weights = [1,2,3,4,5,6,7,8,9,10], D = 5

Output:
15

---------------------------------------------------------------
Brute Force Approach:
- The minimum capacity must be at least max(weights).
- The maximum capacity could be sum(weights).
- For each capacity between max(weights) and sum(weights):
    - Simulate the shipping process day by day.
    - Check if we can ship all packages within D days.
- Return the smallest capacity that works.

Complexity:
Time = O(n * (sum(weights) - max(weights))) → Too slow
Space = O(1)

---------------------------------------------------------------
Optimal Approach (Binary Search):
- Instead of checking all capacities, we apply binary search between 
  max(weights) and sum(weights).
- For each mid capacity:
    - Simulate shipping with greedy approach.
    - If feasible within D days → try smaller capacity.
    - Otherwise, increase capacity.
- This reduces the search drastically.

Complexity:
Time = O(n * log(sum(weights)))
Space = O(1)

---------------------------------------------------------------
Dry Run:
weights = [3,2,2,4,1,4], D = 3

Step 1: maxWeight = 4, sumWeight = 16
Search space = [4..16]

Binary Search:
mid = 10 → can we ship in 3 days? 
    Day1: 3+2+2=7, Day2: 4+1=5, Day3: 4 → OK
    → capacity 10 works → try smaller → right = 9

mid = 6 → can we ship in 3 days?
    Day1: 3+2=5, Day2: 2+4=6, Day3: 1+4=5 → OK
    → capacity 6 works → try smaller → right = 5

mid = 4 → can we ship in 3 days?
    Day1: 3, Day2: 2+2=4, Day3: 4, Day4: 1+4=5 → Needs 4 days (>3) → not OK
    → left = 5

mid = 5 → can we ship in 3 days?
    Day1: 3+2=5, Day2: 2, Day3: 4, Day4: 1+4=5 → Needs 4 days (>3) → not OK
    → left = 6

Now left=6, right=5 → exit loop
Answer = 6
---------------------------------------------------------------
*/

package binarySearchAndPrefixSum.q1011;

public class CapacityToShipPackagesWithinDDays {

    // Check if we can ship all packages within D days using capacity cap
    private static boolean canShip(int[] weights, int D, int cap) {
        int days = 1;
        int currLoad = 0;

        for (int w : weights) {
            if (currLoad + w <= cap) {
                currLoad += w;
            } else {
                days++;
                currLoad = w;
                if (days > D)
                    return false;
            }
        }
        return true;
    }

    private static int shipWithinDays(int[] weights, int D) {
        int maxWeight = 0, sumWeight = 0;
        for (int w : weights) {
            maxWeight = Math.max(maxWeight, w);
            sumWeight += w;
        }

        int left = maxWeight, right = sumWeight;
        int ans = sumWeight;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (canShip(weights, D, mid)) {
                ans = mid; // possible answer, try smaller capacity
                right = mid - 1;
            } else {
                left = mid + 1; // need bigger capacity
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] weights1 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        int D1 = 5;
        System.out.println(shipWithinDays(weights1, D1)); // Output: 15

        int[] weights2 = { 3, 2, 2, 4, 1, 4 };
        int D2 = 3;
        System.out.println(shipWithinDays(weights2, D2)); // Output: 6

        int[] weights3 = { 1, 2, 3, 1, 1 };
        int D3 = 4;
        System.out.println(shipWithinDays(weights3, D3)); // Output: 3
    }
}
