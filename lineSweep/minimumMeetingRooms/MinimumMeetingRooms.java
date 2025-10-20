package lineSweep.minimumMeetingRooms;

import java.util.*;

public class MinimumMeetingRooms {
    public static int uniqueListClass(int[][] intervals) {
        TreeMap<Integer, Integer> timeline = new TreeMap<>();
        
        for (int[] interval : intervals) {
            int start = interval[0];
            int end = interval[1];
            
            timeline.put(start, timeline.getOrDefault(start, 0) + 1);
            timeline.put(end, timeline.getOrDefault(end, 0) - 1);
        }
        
        int currentRooms = 0;
        int maxRooms = 0;
        
        for (int count : timeline.values()) {
            currentRooms += count;
            maxRooms = Math.max(maxRooms, currentRooms);
        }
        
        return maxRooms;
    }
    
    public static void main(String[] args) {
        int[][] intervals = {{0, 1}, {0, 3}, {0, 4}};
        System.out.println("The minimum meeting rooms required: " + uniqueListClass(intervals));
        // Expected Output: 3
    }
}

// Time Complexity: O(n log n)
// Space Complexity: O(n)