package reservoirSampling.q382;

import java.util.Random;

public class LinkedListRandomNode {

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    private ListNode head;
    private Random rand;

    public LinkedListRandomNode(ListNode head) {
        this.head = head;
        this.rand = new Random();
    }

    public int getRandom() {
        ListNode current = head;
        int reservoir = -1;
        int count = 0;

        while (current != null) {
            count++;
            if (rand.nextInt(count) == 0) {
                reservoir = current.val;
            }
            current = current.next;
        }

        return reservoir;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);

        LinkedListRandomNode solution = new LinkedListRandomNode(head);
        System.out.println("Random node value: " + solution.getRandom());
        // You can call getRandom() multiple times to see the distribution
        // of returned node values.
    }

}

// Complexity Analysis
// Time Complexity: O(N) for each call to getRandom(), where N is the number of
// nodes in the linked list.
// Space Complexity: O(1), as we are using a constant amount of space.
