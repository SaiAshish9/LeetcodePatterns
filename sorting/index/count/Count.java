package sorting.index.count;

import java.util.Arrays;

public class Count {

    private static void countingSort(int[] arr) {
        if (arr.length == 0)
            return;

        int max = Arrays.stream(arr).max().getAsInt();
        int min = Arrays.stream(arr).min().getAsInt();
        int range = max - min + 1;
        int[] count = new int[range]; // Frequency of each element
        int[] output = new int[arr.length]; // Output sorted array

        // Count occurrences
        for (int i = 0; i < arr.length; i++) {
            count[arr[i] - min]++;
        }
        System.out.println("Count array: " + Arrays.toString(count)); // [1, 2, 2, 1, 0, 0, 0, 1]

        // Cumulative count (for stable sort)
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }
        System.out.println("Cumulative count: " + Arrays.toString(count)); // [1, 3, 5, 6, 6, 6, 6, 7]

        // If we want a non-stable version, we can do:
        /*
         * for (int i = 0; i < arr.length; i++) {
         * output[count[arr[i] - min] - 1] = arr[i];
         * count[arr[i] - min]--;
         * }
         * This will place elements in output array but not maintain stability.
         * Duplicates
         * may not retain their original order. Equal elements could be placed in any
         * order and overwrite each other which is not desired in a stable sort. (unstable)
         */

        // Build the output array (stable version)
        for (int i = arr.length - 1; i >= 0; i--) {
            output[count[arr[i] - min] - 1] = arr[i]; // [1, 2, 2, 3, 3, 4, 8]
            count[arr[i] - min]--;
        }
        System.out.println("Final count: " + Arrays.toString(count)); // [0, 1, 3, 5, 6, 6, 6, 6]

        // Copy back to original array
        for (int i = 0; i < arr.length; i++) {
            arr[i] = output[i];
        }
    }
    public static void main(String[] args) {
        int[] arr = { 3, 6, 4, 1, 2, 3, 2, 8 };
        countingSort(arr);
        System.out.println("Sorted array: " + Arrays.toString(arr)); // [1, 2, 2, 3, 3, 4, 6, 8]
    }

}

// Complexity Analysis
// Time Complexity: O(n + k), where n is the number of elements in the input
// array and k is the range of the input.
// Space Complexity: O(n + k) for storing the count array and the output array.
