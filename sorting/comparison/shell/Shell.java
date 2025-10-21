package sorting.comparison.shell;

public class Shell {

    public static void shellSort(int[] arr) {
        int n = arr.length;
        
        // Start with a big gap, then reduce the gap
        for (int gap = n/2; gap > 0; gap /= 2) {
            // Do a gapped insertion sort for this gap size
            for (int i = gap; i < n; i++) {
                int temp = arr[i];
                int j;
                for (j = i; j >= gap && arr[j - gap] > temp; j -= gap) {
                    arr[j] = arr[j - gap];
                }
                arr[j] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {12, 34, 54, 2, 3};
        shellSort(arr);
        System.out.println("Sorted array:");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        // Output: Sorted array: 2 3 12 34 54
    }

}

// Complexity Analysis:
// Time Complexity: O(n^2) in the worst case, O(n log n) on average, and O(n) in the best case.
// Space Complexity: O(1) as it is an in-place sorting algorithm.
// Stability: Shell sort is not a stable sorting algorithm.