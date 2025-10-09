package recursion.timeConverter.implementation;

import java.util.*;

public class TimeConverter {

    static class TimeUnit {
        String label;
        double value;

        public TimeUnit(String label, double value) {
            this.label = label;
            this.value = value;
        }
    }

    static List<TimeUnit> units = Arrays.asList(
            new TimeUnit("Years", 525600),
            new TimeUnit("Months", 43200),
            new TimeUnit("Days", 1440),
            new TimeUnit("Hours", 60),
            new TimeUnit("Minutes", 1),
            new TimeUnit("Seconds", 1.0 / 60));

    private static void convert(double totalMinutes, int index, StringBuilder sb) {
        if (index >= units.size())
            return;

        TimeUnit current = units.get(index);

        if (current.label.equals("Seconds")) {
            int seconds = (int) Math.round(totalMinutes * 60);
            sb.append(seconds).append(" ").append(current.label);
        } else {
            int time = (int) (totalMinutes / current.value);
            sb.append(time).append(" ").append(current.label).append(" ");
            double remaining = totalMinutes % current.value;
            convert(remaining, index + 1, sb);
        }
    }

    public static void main(String... s) {
        double totalMinutes = 1_000_000;
        StringBuilder sb = new StringBuilder();
        convert(totalMinutes, 0, sb);
        System.out.println(sb.toString());
    }
    // 1 Years 10 Months 29 Days 10 Hours 40 Minutes 0 Seconds
}

/*
 * Complexity Analysis:
 * Time Complexity: O(1), since the number of time units is fixed.
 * Space Complexity: O(1), for the recursion stack and output string.
 * 
 * If not using recursion, both time and space complexity remain O(1).
 * 
 * If not fixed number of time units, time complexity would be O(n) where n is the number of time units.
 * Space complexity would also be O(n) for the recursion stack in the worst case.
 */