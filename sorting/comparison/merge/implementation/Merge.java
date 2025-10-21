package sorting.comparison.merge.implementation;

import java.util.Arrays;

public class Merge {

    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            if (arr[mid] <= arr[mid + 1]) {
                return; // Optimization: skip merge if already sorted
            }

            merge(arr, left, mid, right);
        }
    }

    public static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; i++)
            L[i] = arr[left + i];
        for (int j = 0; j < n2; j++)
            R[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }

        while (i < n1)
            arr[k++] = L[i++];
        while (j < n2)
            arr[k++] = R[j++];
    }

    public static void main(String[] args) {
        int[] arr = { 38, 27, 43, 3, 9, 82, 10 };
        System.out.println("Original array: " + Arrays.toString(arr)); // Output: [38, 27, 43, 3, 9, 82, 10]

        mergeSort(arr, 0, arr.length - 1);

        System.out.println("Sorted array: " + Arrays.toString(arr)); // Output: [3, 9, 10, 27, 38, 43, 82]
    }
}

// Output:
// Original array: [38, 27, 43, 3, 9, 82, 10]
// Sorted array: [3, 9, 10, 27, 38, 43, 82]

// Complexity Analysis:
// Time Complexity: O(n log n) in all cases (best, average, worst)
// Space Complexity: O(n) due to the temporary arrays used for merging