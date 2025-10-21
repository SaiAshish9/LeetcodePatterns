package sorting.comparison.quickselect;

public class QuickSelect {

    static class Node {
        int val;
        Node left, right;

        Node(int val) {
            this.val = val;
        }
    }

    static Node insert(Node root, int val) {
        if (root == null)
            return new Node(val);
        if (val < root.val)
            root.left = insert(root.left, val);
        else
            root.right = insert(root.right, val);
        return root;
    }

    public static int quickSelect(int[] arr, int k) {
        return quickSelect(arr, 0, arr.length - 1, k - 1);
    }

    private static int quickSelect(int[] arr, int left, int right, int k) {
        if (left <= right) {
            int pivotIndex = partition(arr, left, right);

            if (pivotIndex == k) {
                return arr[pivotIndex];
            } else if (pivotIndex > k) {
                return quickSelect(arr, left, pivotIndex - 1, k);
            } else {
                return quickSelect(arr, pivotIndex + 1, right, k);
            }
        }
        return -1; // k is out of bounds
    }

    private static int partition(int[] arr, int left, int right) {
        int pivot = arr[right];
        int i = left - 1;

        for (int j = left; j < right; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, right);
        return i + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        System.out.println("\n\n=== QUICKSELECT ===");
        int[] arr2 = { 7, 10, 4, 3, 20, 15 };
        System.out.print("Array: ");
        for (int i : arr2)
            System.out.print(i + " ");

        int k = 3; // Find 3rd smallest element
        int result = quickSelect(arr2, k);
        System.out.println("\n" + k + "rd smallest element: " + result); // Should be 7

        k = 4; // Find 4th smallest element
        result = quickSelect(arr2.clone(), k); // Use clone to keep original array intact
        System.out.println(k + "th smallest element: " + result); // Should be 10
    }
}

// Complexity Analysis
// Time Complexity: O(n) on average, O(n^2) in the worst case
// Space Complexity: O(1) for the iterative version, O(log n) for the recursive version due to stack space