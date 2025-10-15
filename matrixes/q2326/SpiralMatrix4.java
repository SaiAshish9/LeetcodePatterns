package matrixes.q2326;

import java.util.*;

public class SpiralMatrix4 {
    private static final int[][] DIRECTIONS = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    private static int[][] spiralMatrix(int m, int n, ListNode head) {
        int[][] result = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = -1;
            }
        }

        int r = 0, c = 0, dirIndex = 0;

        ListNode current = head;
        while (current != null) {
            result[r][c] = current.val;
            current = current.next;

            int newR = r + DIRECTIONS[dirIndex][0];
            int newC = c + DIRECTIONS[dirIndex][1];

            if (newR < 0 || newR >= m || newC < 0 || newC >= n || result[newR][newC] != -1) {
                dirIndex = (dirIndex + 1) % 4;
                newR = r + DIRECTIONS[dirIndex][0];
                newC = c + DIRECTIONS[dirIndex][1];
            }

            r = newR;
            c = newC;
        }

        return result;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(3);
        head.next = new ListNode(0);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(6);
        head.next.next.next.next = new ListNode(8);
        head.next.next.next.next.next = new ListNode(1);
        head.next.next.next.next.next.next = new ListNode(7);
        head.next.next.next.next.next.next.next = new ListNode(9);
        head.next.next.next.next.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next.next.next.next.next = new ListNode(2);
        head.next.next.next.next.next.next.next.next.next.next = new ListNode(5);
        int[][] result = spiralMatrix(3, 5, head);
        for (int[] row : result) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
        // Expected Output:
        // [[3,0,2,6,8],[5,0,-1,-1,1],[5,2,4,9,7]]
    }
}

// Complexity Analysis
// Time Complexity: O(m * n)
// Space Complexity: O(1) (excluding the space for the output matrix)