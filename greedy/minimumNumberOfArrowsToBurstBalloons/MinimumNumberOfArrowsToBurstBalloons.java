package greedy.minimumNumberOfArrowsToBurstBalloons;

import java.util.Arrays;

public class MinimumNumberOfArrowsToBurstBalloons {
    public static int findMinArrowShots(int[][] points) {
        if (points == null || points.length == 0) { 
            return 0;
        }
        
        // Sort balloons by end coordinate
        Arrays.sort(points, (a, b) -> Integer.compare(a[1], b[1]));
        
        int arrows = 1;
        int arrowEnd = points[0][1];
        
        for (int i = 1; i < points.length; i++) {
            if (points[i][0] > arrowEnd) {
                // Need a new arrow
                arrows++;
                arrowEnd = points[i][1];
            }
        }
        return arrows;
    }

    public static void main(String[] args) {
        int[][] balloons = {
            {10, 16},
            {2, 8},
            {1, 6},
            {7, 12}
        };
        int result = findMinArrowShots(balloons);
        System.out.println("Minimum arrows needed: " + result);
        // Expected output: 2
    }
}

// Time Complexity: O(n log n) due to sorting
// Space Complexity: O(1)