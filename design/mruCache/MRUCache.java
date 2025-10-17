package design.mruCache;

import java.util.*;

public class MRUCache {
    private class Node {
        int key, value;
        Node prev, next;

        Node(int k, int v) {
            key = k;
            value = v;
        }

        Node() {
        }
    }

    private final int capacity;
    private final Map<Integer, Node> map;
    private final Node head, tail;

    public MRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (!map.containsKey(key))
            return -1;
        Node node = map.get(key);
        remove(node);
        insertToTail(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;
            remove(node);
            insertToTail(node);
        } else {
            if (map.size() == capacity) {
                Node mru = tail.prev;
                remove(mru);
                map.remove(mru.key);
            }
            Node newNode = new Node(key, value);
            insertToTail(newNode);
            map.put(key, newNode);
        }
    }

    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void insertToTail(Node node) {
        Node last = tail.prev;
        last.next = node;
        node.prev = last;
        node.next = tail;
        tail.prev = node;
    }

    public static void main(String[] args) {
        MRUCache cache = new MRUCache(2);

        cache.put(1, 10);
        cache.put(2, 20);
        System.out.println(cache.get(1)); // 10
        cache.put(3, 30); // removes most recently used (key 1)
        System.out.println(cache.get(1)); // -1 (removed)
        System.out.println(cache.get(2)); // 20
        System.out.println(cache.get(3)); // 30
    }
}


// Complexity Analysis
// Time Complexity: O(1) for both get and put operations.
// Space Complexity: O(capacity) for storing the cache entries.