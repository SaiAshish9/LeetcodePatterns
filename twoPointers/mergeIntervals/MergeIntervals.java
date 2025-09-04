package twoPointers.mergeIntervals;

import java.util.*;

public class MergeIntervals {

    private static int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        int[] current = intervals[0];

        List<int[]> merged = new ArrayList<>();

        for (int i = 1; i < intervals.length; i++) {
            int[] next = intervals[i];
            if (next[0] <= current[1]) {
                current[1] = Math.max(current[1], next[1]);
            } else {
                merged.add(current);
                current = next;
            }
        }

        merged.add(current);
        return merged.toArray(new int[merged.size()][]);
    }

    public static void main(String[] args) {
        int[][] intervals = { { 1, 3 }, { 2, 6 }, { 8, 10 }, { 15, 18 } };
        System.out.println(Arrays.deepToString(merge(intervals)));
        // Output: [[1, 6], [8, 10]]
    }

}
