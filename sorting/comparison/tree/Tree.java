package sorting.comparison.tree;

import java.util.ArrayList;

public class Tree {

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

    public static void main(String[] args) {
        System.out.println("=== TREE SORT ===");
        int[] arr = { 5, 3, 7, 2, 4, 6, 8 };
        System.out.print("Original: ");
        for (int i : arr)
            System.out.print(i + " "); // 5 3 7 2 4 6 8

        treeSort(arr);

        System.out.print("\nSorted: ");
        for (int i : arr)
            System.out.print(i + " "); // 2 3 4 5 6 7 8

    }
}

// Complexity Analysis
// Time Complexity: O(n log n) on average for inserting n elements into the BST and O(n) for in-order traversal. In the worst case (unbalanced tree), it can degrade to O(n^2).
// Space Complexity: O(n) for storing the BST and the sorted array.