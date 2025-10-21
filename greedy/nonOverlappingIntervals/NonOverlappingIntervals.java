package greedy.nonOverlappingIntervals;

import java.util.*;

public class NonOverlappingIntervals {
    
    public static int eraseOverlapIntervals(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        
        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);
        
        int count = 0;
        int previousEnd = intervals[0][1];
        
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < previousEnd) {
                // Overlapping -> remove this interval
                count++;
            } else {
                // No overlap -> update previous end
                previousEnd = intervals[i][1];
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[][] intervals = {
            {1, 2},
            {2, 3},
            {3, 4},
            {1, 3}
        };
        int removeResult = eraseOverlapIntervals(intervals);
        System.out.println("Minimum intervals to remove: " + removeResult);
        // Expected output: 1
    }
}

// Complexity Analysis:
// Time Complexity: O(N log N), where N is the number of intervals. This is due to the sorting step.
// Space Complexity: O(1), as we are using only a constant amount of extra space.