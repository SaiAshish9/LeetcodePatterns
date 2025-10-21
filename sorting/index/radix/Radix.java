package sorting.index.radix;

import java.util.Arrays;

public class Radix {

    static int getMax(int[] arr) {
        int max = arr[0];
        for (int value : arr)
            if (value > max)
                max = value;
        return max;
    }

    static void countingSortForRadix(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[10];

        for (int i = 0; i < n; i++) {
            int digit = (arr[i] / exp) % 10;
            count[digit]++;
        }

        for (int i = 1; i < 10; i++)
            count[i] += count[i - 1];

        for (int i = n - 1; i >= 0; i--) {
            int digit = (arr[i] / exp) % 10;
            output[count[digit] - 1] = arr[i];
            count[digit]--;
        }

        for (int i = 0; i < n; i++)
            arr[i] = output[i];
    }

    public static void radixSort(int[] arr) {
        int max = getMax(arr);
        for (int exp = 1; max / exp > 0; exp *= 10)
            countingSortForRadix(arr, exp);
    }

    public static void main(String[] args) {

        int[] radixArr = { 170, 45, 75, 90, 802, 24, 2, 66 };
        System.out.println("Original array: " + Arrays.toString(radixArr));
        // Original array: [170, 45, 75, 90, 802, 24, 2, 66]
        radixSort(radixArr);
        System.out.println("Sorted array: " + Arrays.toString(radixArr));
        // Sorted array: [2, 24, 45, 66, 75, 90, 170, 802]
    }
}

// Complexity Analysis
// Time Complexity: O(d * (n + k)), where n is the number of elements in the input array,
// d is the number of digits in the maximum number, and k is the range of the input (0 to 9 for decimal
// numbers).
// Space Complexity: O(n + k) for the output and count arrays used in counting sort