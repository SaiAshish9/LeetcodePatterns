package sorting.comparison.tim;

import java.util.Arrays;

public class Tim {

    static final int RUN_SIZE = 32;

    // Insertion sort for small chunks
    public static void insertionSort(int[] array, int left, int right) {
        for (int current = left + 1; current <= right; current++) {
            int temp = array[current];
            int j = current - 1;

            while (j >= left && array[j] > temp) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = temp;
        }
    }

    // Merge two sorted subarrays: array[left...mid] and array[mid+1...right]
    public static void merge(int[] array, int left, int mid, int right) {
        int length1 = mid - left + 1;
        int length2 = right - mid;

        int[] leftPart = new int[length1];
        int[] rightPart = new int[length2];

        for (int i = 0; i < length1; i++) {
            leftPart[i] = array[left + i];
        }
        for (int j = 0; j < length2; j++) {
            rightPart[j] = array[mid + 1 + j];
        }

        int i = 0, j = 0, k = left;

        while (i < length1 && j < length2) {
            if (leftPart[i] <= rightPart[j]) {
                array[k++] = leftPart[i++];
            } else {
                array[k++] = rightPart[j++];
            }
        }

        while (i < length1) {
            array[k++] = leftPart[i++];
        }

        while (j < length2) {
            array[k++] = rightPart[j++];
        }
    }

    public static void timSort(int[] array) {
        int n = array.length;

        for (int start = 0; start < n; start += RUN_SIZE) {
            int end = Math.min(start + RUN_SIZE - 1, n - 1);
            insertionSort(array, start, end);
        }

        for (int size = RUN_SIZE; size < n; size *= 2) {
            for (int left = 0; left < n; left += 2 * size) {
                int mid = left + size - 1;
                int right = Math.min(left + 2 * size - 1, n - 1);

                if (mid < right) {
                    merge(array, left, mid, right);
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] array = {5, 21, 7, 23, 19, 10, 12, 11, 6, 9, 0};

        System.out.println("Original array:");
        System.out.println(Arrays.toString(array));

        timSort(array);

        System.out.println("Sorted array:");
        System.out.println(Arrays.toString(array));
    }
}