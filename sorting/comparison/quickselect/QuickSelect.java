package sorting.comparison.quickselect;

import java.util.ArrayList;

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

    static void inOrderTraversal(Node root, ArrayList<Integer> result) {
        if (root == null)
            return;
        inOrderTraversal(root.left, result);
        result.add(root.val);
        inOrderTraversal(root.right, result);
    }

    public static void treeSort(int[] arr) {
        Node root = null;
        for (int val : arr) {
            root = insert(root, val);
        }

        ArrayList<Integer> sorted = new ArrayList<>();
        inOrderTraversal(root, sorted);

        for (int i = 0; i < arr.length; i++) {
            arr[i] = sorted.get(i);
        }
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
        // Test Tree Sort
        System.out.println("=== TREE SORT ===");
        int[] arr = { 5, 3, 7, 2, 4, 6, 8 };
        System.out.print("Original: ");
        for (int i : arr)
            System.out.print(i + " "); // 5 3 7 2 4 6 8

        treeSort(arr);

        System.out.print("\nSorted: ");
        for (int i : arr)
            System.out.print(i + " "); // 2 3 4 5 6 7 8

        // Test QuickSelect
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

        // Display sorted array for verification
        int[] arr2Sorted = arr2.clone();
        treeSort(arr2Sorted);
        System.out.print("Sorted array for verification: ");
        for (int i : arr2Sorted)
            System.out.print(i + " ");
    }
}