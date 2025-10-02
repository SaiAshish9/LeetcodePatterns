package segmentTree.FrequencyCount;

import java.util.*;

public class FrequencyST {

    private static class SegmentTree {
        int n;
        List<Map<Integer, Integer>> tree; // list of maps

        public SegmentTree(int[] nums) {
            n = nums.length;
            tree = new ArrayList<>(4 * n);
            for (int i = 0; i < 4 * n; i++) {
                tree.add(new HashMap<>());
            }
            build(nums, 0, n - 1, 0);
        }

        private void build(int[] nums, int left, int right, int node) {
            if (left == right) {
                tree.get(node).put(nums[left], 1);
                return;
            }

            int mid = left + (right - left) / 2;
            build(nums, left, mid, 2 * node + 1);
            build(nums, mid + 1, right, 2 * node + 2);

            merge(tree.get(node), tree.get(2 * node + 1), tree.get(2 * node + 2));
        }

        private void merge(Map<Integer, Integer> parent, Map<Integer, Integer> left, Map<Integer, Integer> right) {
            parent.clear();
            parent.putAll(left);
            for (Map.Entry<Integer, Integer> e : right.entrySet()) {
                parent.put(e.getKey(), parent.getOrDefault(e.getKey(), 0) + e.getValue());
            }
        }

        public void update(int left, int right, int node, int index, int newVal, int oldVal) {
            if (left == right) {
                tree.get(node).clear();
                tree.get(node).put(newVal, 1);
                return;
            }

            int mid = left + (right - left) / 2;
            if (index <= mid) {
                update(left, mid, 2 * node + 1, index, newVal, oldVal);
            } else {
                update(mid + 1, right, 2 * node + 2, index, newVal, oldVal);
            }

            merge(tree.get(node), tree.get(2 * node + 1), tree.get(2 * node + 2));
        }

        public Map<Integer, Integer> query(int start, int end, int l, int r, int node) {
            if (r < start || l > end) return new HashMap<>(); // no overlap
            if (l <= start && end <= r) return new HashMap<>(tree.get(node)); // total overlap

            int mid = start + (end - start) / 2;
            Map<Integer, Integer> leftQ = query(start, mid, l, r, 2 * node + 1);
            Map<Integer, Integer> rightQ = query(mid + 1, end, l, r, 2 * node + 2);

            Map<Integer, Integer> res = new HashMap<>();
            merge(res, leftQ, rightQ);
            return res;
        }
    }

    public static void main(String... s) {
        int nums[] = {1, 3, 5, 3, 9, 1};
        int n = nums.length;
        SegmentTree tree = new SegmentTree(nums);

        System.out.println("Query(1,4) frequencies = " + tree.query(0, n - 1, 1, 4, 0));

        tree.update(0, n - 1, 0, 1, 5, 3); // change index 1 from 3 → 5
        System.out.println("Query(1,4) after update = " + tree.query(0, n - 1, 1, 4, 0));
    }
}
