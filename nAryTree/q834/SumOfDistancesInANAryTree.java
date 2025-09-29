/*
Input:
n = 6
edges = [[0,1],[0,2],[2,3],[2,4],[2,5]]

Output:
[8,12,6,10,10,10]

Brute Force Approach:
- For each node i, compute distance to every other node j.
- Use LCA (Lowest Common Ancestor) + DFS to calculate distance(i,j).
- Sum them up for each i.
- Time complexity: O(N^2 * H) ≈ O(N^3) in worst case (since each distance query may traverse tree height).

Complexity:
Time: O(N^2 * H)
Space: O(N)

Optimal Approach:
- Use tree DP with 2 DFS passes:
  1. First DFS: compute size of subtree and sum of distances from root (node 0).
  2. Second DFS: use parent’s result to compute for children without recomputing from scratch.
- This reduces complexity to O(N).

Complexity:
Time: O(N)
Space: O(N)

Dry Run:
Tree:
       0
      / \
     1   2
        /|\
       3 4 5

Distances:
From 0: d(0,1)=1, d(0,2)=1, d(0,3)=2, d(0,4)=2, d(0,5)=2 → sum=8
From 1: d(1,0)=1, d(1,2)=2, d(1,3)=3, d(1,4)=3, d(1,5)=3 → sum=12
From 2: d(2,0)=1, d(2,1)=2, d(2,3)=1, d(2,4)=1, d(2,5)=1 → sum=6
From 3: d(3,2)=1, d(3,0)=2, d(3,1)=3, d(3,4)=2, d(3,5)=2 → sum=10
From 4: d(4,2)=1, d(4,0)=2, d(4,1)=3, d(4,3)=2, d(4,5)=2 → sum=10
From 5: d(5,2)=1, d(5,0)=2, d(5,1)=3, d(5,3)=2, d(5,4)=2 → sum=10

Final Result = [8, 12, 6, 10, 10, 10]
*/

package nAryTree.q834;

import java.util.*;

public class SumOfDistancesInANAryTree {
    static class TreeNode {
        int data;
        List<TreeNode> children;

        TreeNode(int data) {
            this.data = data;
            children = new ArrayList<>();
        }
    }

    private static void buildTree(Map<Integer, TreeNode> nodeMap, int[][] edges) {
        for (int[] edge : edges) {
            nodeMap.get(edge[0]).children.add(nodeMap.get(edge[1]));
        }
    }

    private static TreeNode findLCA(TreeNode root, int n1, int n2) {
        if (root == null)
            return null;
        if (root.data == n1 || root.data == n2)
            return root;

        int count = 0;
        TreeNode temp = null;

        for (TreeNode child : root.children) {
            TreeNode res = findLCA(child, n1, n2);
            if (res != null) {
                count++;
                temp = res;
            }
            if (count == 2)
                return root;
        }
        return temp;
    }

    private static int findDistance(TreeNode root, int target, int level) {
        if (root == null)
            return -1;
        if (root.data == target)
            return level;

        for (TreeNode child : root.children) {
            int dist = findDistance(child, target, level + 1);
            if (dist != -1)
                return dist;
        }
        return -1;
    }

    private static List<Integer> computeSumDistances(Map<Integer, TreeNode> nodeMap, int n) {
        List<Integer> res = new ArrayList<>();
        TreeNode root = nodeMap.get(0);

        for (int i = 0; i < n; i++) {
            int sum = 0;
            Set<Integer> neighbors = new HashSet<>();
            for (TreeNode child : nodeMap.get(i).children) {
                sum += 1;
                neighbors.add(child.data);
            }

            for (int j = 0; j < n; j++) {
                if (i != j && !neighbors.contains(j)) {
                    TreeNode lca = findLCA(root, i, j);
                    int dist1 = findDistance(lca, i, 0);
                    int dist2 = findDistance(lca, j, 0);
                    sum += dist1 + dist2;
                }
            }
            res.add(sum);
        }
        return res;
    }

    public static void main(String[] args) {
        int n = 6;
        int[][] edges = new int[][] {
                { 0, 1 }, { 0, 2 }, { 2, 3 }, { 2, 4 }, { 2, 5 }
        };

        Map<Integer, TreeNode> nodeMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            nodeMap.put(i, new TreeNode(i));
        }

        buildTree(nodeMap, edges);

        List<Integer> result = computeSumDistances(nodeMap, n);
        System.out.println(result); // Output: [8, 12, 6, 10, 10, 10]
    }
}
