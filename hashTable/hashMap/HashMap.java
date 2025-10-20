package hashTable.hashMap;

class MyHashMap {
    private static class Node {
        int key, value;
        Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int SIZE = 10000;
    private Node[] buckets;

    public MyHashMap() {
        buckets = new Node[SIZE];
    }

    private int hash(int key) {
        return key % SIZE;
    }

    public void put(int key, int value) {
        int index = hash(key);
        if (buckets[index] == null) {
            buckets[index] = new Node(-1, -1);
        }
        Node prev = find(buckets[index], key);
        if (prev.next == null) {
            prev.next = new Node(key, value);
        } else {
            prev.next.value = value; 
        }
    }

    public int get(int key) {
        int index = hash(key);
        if (buckets[index] == null) {
            return -1;
        }
        Node prev = find(buckets[index], key);
        if (prev.next == null) {
            return -1;
        }
        return prev.next.value;
    }

    public void remove(int key) {
        int index = hash(key);
        if (buckets[index] == null) {
            return;
        }
        Node prev = find(buckets[index], key);
        if (prev.next == null) {
            return;
        }
        prev.next = prev.next.next;
    }

    private Node find(Node head, int key) {
        Node curr = head, prev = null;
        while (curr != null && curr.key != key) {
            prev = curr;
            curr = curr.next;
        }
        return prev;
    }

    public static void main(String[] args) {
        MyHashMap map = new MyHashMap();
        map.put(1, 1);
        map.put(2, 2);
        System.out.println(map.get(1)); // returns 1
        System.out.println(map.get(3)); // returns -1 (not found)
        map.put(2, 1); // update the value
        System.out.println(map.get(2)); // returns 1
        map.remove(2); // remove key 2
        System.out.println(map.get(2)); // returns -1 (removed)
    }
}

// Complexity Analysis
// Time Complexity: O(N/K) on average for each operation (put, get, remove), where N is the number of keys and K is the number of buckets. 
// In the worst case, all keys hash to the same bucket, leading to O(N) time complexity.
// Space Complexity: O(N + K), where N is the number of unique keys stored  
// 