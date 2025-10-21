package sorting.comparison.randomizedQuick;

import java.util.Random;

public class RandomizedQuick {
    Random rand = new Random();

    public void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = randomizedPartition(arr, low, high);
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    private int randomizedPartition(int[] arr, int low, int high) {
        int pivotIdx = low + rand.nextInt(high - low + 1);
        swap(arr, pivotIdx, high); 
        return partition(arr, low, high); 
    }

    private int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1; 

        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high); 
        return i + 1;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {3, 6, 2, 7, 5, 1, 4};
        RandomizedQuick rq = new RandomizedQuick();
        rq.quickSort(arr, 0, arr.length - 1);
        System.out.println("Sorted array:");
        for (int num : arr) {
            System.out.print(num + " ");    
        }
        // Expected output: Sorted array: 1 2 3 4 5 6 7
    }
}

// Complexity Analysis
// Time Complexity: O(n log n) on average, O(n^2) in the worst case
// Space Complexity: O(log n) due to recursion stack
