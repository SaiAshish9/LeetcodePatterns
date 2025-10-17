package design.treeMap;

import java.util.NoSuchElementException;

public class TreeMap<K extends Comparable<K>, V> {
    
    private class Node {
        K key;
        V value;
        Node left, right;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node root;
    private int size;

    public TreeMap() {
        root = null;
        size = 0;
    }

    public void put(K key, V value) {
        root = put(root, key, value);
    }

    private Node put(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = put(node.left, key, value);
        } else if (cmp > 0) {
            node.right = put(node.right, key, value);
        } else {
            node.value = value; 
        }
        return node;
    }

    public V get(K key) {
        Node node = get(root, key);
        return node == null ? null : node.value;
    }

    private Node get(Node node, K key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) return get(node.left, key);
        else if (cmp > 0) return get(node.right, key);
        else return node;
    }

    public boolean containsKey(K key) {
        return get(key) != null;
    }

    public void remove(K key) {
        if (!containsKey(key)) throw new NoSuchElementException("Key not found: " + key);
        root = remove(root, key);
        size--;
    }

    private Node remove(Node node, K key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) node.left = remove(node.left, key);
        else if (cmp > 0) node.right = remove(node.right, key);
        else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            Node successor = min(node.right);
            node.key = successor.key;
            node.value = successor.value;
            node.right = remove(node.right, successor.key);
        }
        return node;
    }

    private Node min(Node node) {
        while (node.left != null) node = node.left;
        return node;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
    
    public void inorderTraversal() {
        inorder(root);
        System.out.println();
    }

    private void inorder(Node node) {
        if (node == null) return;
        inorder(node.left);
        System.out.print(node.key + "=" + node.value + " ");
        inorder(node.right);
    }

    public static void main(String[] args) {
        TreeMap<Integer, String> map = new TreeMap<>();
        map.put(3, "C");
        map.put(1, "A");
        map.put(2, "B");
        map.put(5, "E");
        map.put(4, "D");

        System.out.println("Inorder (sorted by key):");
        map.inorderTraversal();  // 1=A 2=B 3=C 4=D 5=E 

        System.out.println("Get key 3: " + map.get(3));
        System.out.println("Contains key 4: " + map.containsKey(4));

        map.remove(3);
        System.out.println("After removing key 3:");
        map.inorderTraversal();
    }
}

// Complexity Analysis:
// Time Complexity:
// - put: O(h) where h is the height of the tree
// - get: O(h)
// - remove: O(h)   

// A TreeMap is typically a Red-Black Tree implementation (like in Java’s standard library) that stores key–value pairs in sorted order by key.
// Here’s a simplified version of a TreeMap implementation using a Binary Search Tree (BST) for clarity
