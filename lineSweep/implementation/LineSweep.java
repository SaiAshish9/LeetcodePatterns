package lineSweep.implementation;

import java.util.*;

public class LineSweep {
    public static int maxOverlap(int[][] intervals) {
        TreeMap<Integer, Integer> timeline = new TreeMap<>();
        
        for (int[] interval : intervals) {
            int start = interval[0];
            int end = interval[1];
            timeline.put(start, timeline.getOrDefault(start, 0) + 1);
            timeline.put(end + 1, timeline.getOrDefault(end + 1, 0) - 1); // end + 1 to count exclusive
        }
        
        int active = 0, max = 0;
        for (int count : timeline.values()) {
            active += count;
            max = Math.max(max, active);
        }
        return max;
    }

    public static void main(String[] args) {
        int[][] intervals = {{1, 3}, {2, 5}, {4, 6}};
        System.out.println("Max Overlap: " + maxOverlap(intervals));
        // Output: Max Overlap: 2
    }
}

// Complexity Analysis
// Time Complexity: O(N log N), where N is the number of intervals. Sorting the
// events takes O(N log N) time.
// Space Complexity: O(N) for storing the timeline events.