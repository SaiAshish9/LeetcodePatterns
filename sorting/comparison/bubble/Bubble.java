package sorting.comparison.bubble;

public class Bubble {

    private static void bubbleSort(int[] array) {
        int n = array.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }
            // If no two elements were swapped in the inner loop, then break
            if (!swapped) break;
        }
    }

    public static void main(String[] args) {
        int[] array = {5, 2, 9, 1, 5, 6};
        bubbleSort(array);
        for (int num : array) {
            System.out.print(num + " ");
        }
    }

}

// Complexity Analysis:
// Time Complexity: O(n^2) in the average and worst case, O(n) in the best case (when the array is already sorted).
// Space Complexity: O(1) as it is an in-place sorting algorithm.