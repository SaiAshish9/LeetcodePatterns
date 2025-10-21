package sorting.comparison.randomizedQuickSelect;

import java.util.Random;

public class RandomizedQuickSelect {
    Random rand = new Random();
    public int quickSelect(int[] arr, int k) {
        return quickSelect(arr, 0, arr.length - 1, k);
    }

    private int quickSelect(int[] arr, int left, int right, int k) {
        if (left == right) return arr[left];
        int pivotIndex = left + rand.nextInt(right - left + 1);
        pivotIndex = partition(arr, left, right, pivotIndex);
        if (k == pivotIndex) {
            return arr[k];
        } else if (k < pivotIndex) {
            return quickSelect(arr, left, pivotIndex - 1, k);
        } else {
            return quickSelect(arr, pivotIndex + 1, right, k);
        }
    }

    private int partition(int[] arr, int left, int right, int pivotIndex) {
        int pivot = arr[pivotIndex];
        swap(arr, pivotIndex, right); // Move pivot to end
        int storeIndex = left;
        for (int i = left; i < right; i++) {
            if (arr[i] < pivot) {
                swap(arr, storeIndex, i);
                storeIndex++;
            }
        }
        swap(arr, storeIndex, right); // Move pivot to final place
        return storeIndex;
    }
    
    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        RandomizedQuickSelect rqs = new RandomizedQuickSelect();
        int[] arr = {3, 6, 2, 7, 5, 1, 4};
        int k = 3; // Find the 3rd smallest element (0-based index)
        int result = rqs.quickSelect(arr, k);
        System.out.println("The " + k + "th smallest element is: " + result);
        // Expected output: The 3th smallest element is: 4
    }
}

// Complexity Analysis
// Time Complexity: O(n) on average, O(n^2) in the worst case
// Space Complexity: O(1)