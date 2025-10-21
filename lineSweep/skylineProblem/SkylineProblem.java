package lineSweep.skylineProblem;

import java.util.*;
import java.util.Collections;

public class SkylineProblem {
    private static List<int[]> getSkyline(int[][] buildings) {
        List<int[]> events = new ArrayList<>();
        
        // Create events: start with negative height, end with positive height
        for (int[] b : buildings) {
            events.add(new int[]{b[0], -b[2]}); // building start (negative height)
            events.add(new int[]{b[1], b[2]});  // building end (positive height)
        }
        
        // Sort by x-coordinate; if tie, by height (start events first)
        events.sort((a, b) -> 
            a[0] != b[0] ? Integer.compare(a[0], b[0]) : Integer.compare(a[1], b[1])
        );
        
        List<int[]> result = new ArrayList<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        
        maxHeap.add(0); // initial ground height
        int prevMax = 0;
        
        for (int[] event : events) {
            int x = event[0], h = event[1];
            
            if (h < 0) {
                // Start of building - add height to heap
                maxHeap.add(-h);
            } else {
                // End of building - remove height from heap
                maxHeap.remove(h);
            }
            
            int currMax = maxHeap.peek();
            if (currMax != prevMax) {
                // Height changed, add key point
                result.add(new int[]{x, currMax});
                prevMax = currMax;
            }
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        int[][] buildings = {
            {2, 9, 10}, {3, 7, 15}, {5, 12, 12}, 
            {15, 20, 10}, {19, 24, 8}
        };
        
        List<int[]> skyline = getSkyline(buildings);
        for (int[] point : skyline) {
            System.out.println(Arrays.toString(point));
        }
        // Expected Output:
        // [2, 10]
        // [3, 15]
        // [7, 12]
        // [12, 0]  
        // [15, 10]
        // [20, 8]
        // [24, 0]
    }
}

// Time Complexity: O(n log n)
// Space Complexity: O(n)