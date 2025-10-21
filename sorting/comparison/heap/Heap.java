package sorting.comparison.heap;

import java.util.Arrays;

public class Heap {
    private static void heapSort(int[] arr) {
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        for (int i = n - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapify(arr, i, 0); 
        }
    }

    private static void heapify(int[] arr, int n, int i) {
        int largest = i;   
        int left = 2 * i + 1;   
        int right = 2 * i + 2;   

        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }

        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }

        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            heapify(arr, n, largest);
        }
    }

    public static void main(String[] args) {
        int[] arr = {4, 2, 7, 1, 3, 6, 5};
        System.out.println("Original array:");
        System.out.println(Arrays.toString(arr));
        heapSort(arr);
        System.out.println("Sorted array:");
        System.out.println(Arrays.toString(arr)); // [1, 2, 3, 4, 5, 6, 7]
        
        // Test with the array from the sorting steps example
        int[] arr2 = {10, 5, 3, 4, 1};
        System.out.println("\nOriginal array 2:");
        System.out.println(Arrays.toString(arr2));
        heapSort(arr2);
        System.out.println("Sorted array 2:");
        System.out.println(Arrays.toString(arr2)); // [1, 3, 4, 5, 10]
    }
}


// Complexity Analysis
// Time Complexity: O(n log n) in all cases (best, average, worst) due to the heap construction and the repeated extraction of the maximum element.
// Space Complexity: O(1) as the sorting is done in place without requiring additional storage proportional to the input size.