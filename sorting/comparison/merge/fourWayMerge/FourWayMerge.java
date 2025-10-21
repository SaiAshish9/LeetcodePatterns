package sorting.comparison.merge.fourWayMerge;

import java.util.Arrays;

public class FourWayMerge {

    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            // Optimization: skip merge if already sorted
            if (arr[mid] <= arr[mid + 1]) {
                return;
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

    public static int[] mergeTwoSortedArrays(int[] A, int[] B) {
        int n = A.length, m = B.length;
        int[] result = new int[n + m];

        int i = 0, j = 0, k = 0;
        while (i < n && j < m) {
            if (A[i] <= B[j]) {
                result[k++] = A[i++];
            } else {
                result[k++] = B[j++];
            }
        }

        while (i < n)
            result[k++] = A[i++];
        while (j < m)
            result[k++] = B[j++];

        return result;
    }

    public static int[] mergeFourSortedArrays(int[] A, int[] B, int[] C, int[] D) {
        int[] firstMerge = mergeTwoSortedArrays(A, B);
        int[] secondMerge = mergeTwoSortedArrays(C, D);
        return mergeTwoSortedArrays(firstMerge, secondMerge);
    }

    public static void main(String[] args) {

        int[] arr = { 38, 27, 43, 3, 9, 82, 10 };
        System.out.println("Original array: " + Arrays.toString(arr));

        mergeSort(arr, 0, arr.length - 1);

        System.out.println("Sorted array:   " + Arrays.toString(arr));

        int[] A = { 1, 3, 5, 7 };
        int[] B = { 2, 4, 6, 8 };
        int[] C = { 0, 9, 10 };
        int[] D = { 11, 12, 15 };
        int[] mergedFour = mergeFourSortedArrays(A, B, C, D);
        System.out.println("Merged Four Arrays: " + Arrays.toString(mergedFour));
        // Output: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 15]
    }
}

// Operation	Time Complexity	Space Complexity
// Merge Sort	O(n log n)	O(n)
// Merge 2 Arrays	O(n + m)	O(n + m)
// Merge 4 Arrays	O(n + m + p + q)	O(n + m + p + q)

// why O(n + m) and not n+m log n+m for merging two sorted arrays?
// When merging two sorted arrays, the time complexity is O(n + m) because you are 
// essentially performing a linear scan through both arrays.

// 🔍 Why not O(n log n + m)?

// Because:

// You’re not sorting here — both arrays are already sorted.

// Merge Sort’s log n term comes from repeatedly splitting arrays.
// In a simple merge, there’s no splitting — just one merge pass.

// So, you only do one linear pass over the two arrays.