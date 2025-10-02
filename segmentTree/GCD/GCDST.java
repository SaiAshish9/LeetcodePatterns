package segmentTree.GCD;

import java.util.*;

public class GCDST {

    private static int gcd(int a, int b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }

    private static class SegmentTree {
        int n;
        int[] tree;

        public SegmentTree(int[] nums) {
            n = nums.length;
            tree = new int[4 * n];
            build(nums, 0, n - 1, 0);
            System.out.println("Built tree: " + Arrays.toString(tree));
        }

        public void build(int[] nums, int left, int right, int node) {
            if (left == right) {
                tree[node] = nums[left];
                return;
            }

            int mid = left + (right - left) / 2;
            build(nums, left, mid, 2 * node + 1);
            build(nums, mid + 1, right, 2 * node + 2);
            tree[node] = gcd(tree[2 * node + 1], tree[2 * node + 2]);
        }

        public void update(int left, int right, int node, int index, int val) {
            if (left == right) {
                tree[node] = val;
                return;
            }

            int mid = left + (right - left) / 2;
            if (index <= mid) {
                update(left, mid, 2 * node + 1, index, val);
            } else {
                update(mid + 1, right, 2 * node + 2, index, val);
            }

            tree[node] = gcd(tree[2 * node + 1], tree[2 * node + 2]);
        }

        public int query(int start, int end, int left, int right, int node) {
            if (right < start || end < left)
                return 0; // no overlap
            if (left <= start && end <= right)
                return tree[node]; // total overlap

            int mid = start + (end - start) / 2;
            int leftQ = query(start, mid, left, right, 2 * node + 1);
            int rightQ = query(mid + 1, end, left, right, 2 * node + 2);
            return gcd(leftQ, rightQ);
        }
    }

    public static void main(String... s) {
        int nums[] = { 1, 3, 5, 7, 9, 11 };
        int n = nums.length;
        SegmentTree tree = new SegmentTree(nums);
        System.out.println("Query(1,3) = " + tree.query(0, n - 1, 1, 3, 0));

        tree.update(0, n - 1, 0, 1, 10);
        System.out.println("Query(1,3) after update = " + tree.query(0, n - 1, 1, 3, 0));
    }
}

/*
 * Built tree: [1, 1, 1, 1, 5, 1, 11, 1, 3, 0, 0, 7, 9, 0, 0, 0, 0, 0, 0, 0, 0,
 * 0, 0, 0]
 * Query(1,3) = 1
 * Query(1,3) after update = 1
 */
