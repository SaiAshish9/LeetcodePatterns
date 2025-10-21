package greedy.meetingRoomsII;

import java.util.*;

public class MeetingRoomsII {
    
    public static int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        PriorityQueue<Integer> heap = new PriorityQueue<>();        
        heap.offer(intervals[0][1]);
        
        for (int i = 1; i < intervals.length; i++) {
            int currentStart = intervals[i][0];
            int currentEnd = intervals[i][1];
            
            if (currentStart >= heap.peek()) {
                heap.poll();
            }
            
            heap.offer(currentEnd);
        }
        
        return heap.size();
    }

    public static void main(String[] args) {
        int[][] meetings = {
            {0, 30},
            {5, 10},
            {15, 20}
        };
        int roomsResult = minMeetingRooms(meetings);
        System.out.println("Minimum meeting rooms needed: " + roomsResult);
        // Expected output: 2
    }
}

// Complexity Analysis
// Time Complexity: O(N log N), where N is the number of meetings. Sorting the
// intervals takes O(N log N) time, and we perform O(N) insertions and
// deletions in the priority queue, each taking O(log N) time.
// Space Complexity: O(N) in the worst case, where all meetings overlap and we
// need N rooms, leading to N elements in the priority queue.