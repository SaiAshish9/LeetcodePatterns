package sorting.comparison.insertion;

import java.util.Arrays;

public class Insertion {

    public static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;

            // Move elements greater than key one position ahead
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            // Place the key in its correct position
            arr[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        int[] arr = { 5, 3, 8, 4, 2 };
        System.out.println("Original array:");
        System.out.println(Arrays.toString(arr)); // [5, 3, 8, 4, 2]
        insertionSort(arr);
        System.out.println("Sorted array:");
        System.out.println(Arrays.toString(arr)); // [2, 3, 4, 5, 8]
    }

}

// Complexity Analysis:
// Time Complexity: O(n^2) in the average and worst cases, O(n) in the best case (when the array is already sorted).
// Space Complexity: O(1)