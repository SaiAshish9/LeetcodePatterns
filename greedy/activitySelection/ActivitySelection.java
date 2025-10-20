package greedy.activitySelection;


import java.util.Arrays;
import java.util.Comparator;

public class ActivitySelection {

    static class Activity {
        int start, end;

        Activity(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    static int maxActivities(Activity[] activities) {
        Arrays.sort(activities, Comparator.comparingInt(a -> a.end));

        int count = 0;
        int lastEnd = -1;

        for (Activity activity : activities) {
            if (activity.start >= lastEnd) {
                count++;
                lastEnd = activity.end;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Activity[] activities = {
            new Activity(1, 2),
            new Activity(3, 4),
            new Activity(0, 6),
            new Activity(5, 7),
            new Activity(8, 9),
            new Activity(5, 9)
        };
        System.out.println("Maximum number of activities = " + maxActivities(activities));
        // Expected Output: 4
    }
}

// Output: Maximum number of activities = 4

// Complexity Analysis
// Time Complexity: O(n log n) due to sorting the activities based on their finish times.
// Space Complexity: O(1) if we ignore the input storage.