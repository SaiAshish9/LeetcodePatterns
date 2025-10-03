package unionFind.cycleDetection;

import java.util.*;

public class CycleDetection {

    private static class Node {
        int data;
        List<Node> neighbors;

        Node(int data) {
            this.data = data;
            this.neighbors = new ArrayList<>();
        }
    }

    static private class UF {
        int[] parent;

        public UF(int n) {
            parent = new int[n];

            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int u) {
            if (parent[u] != u) {
                parent[u] = find(parent[u]);
            }
            return parent[u];
        }

        public boolean union(int u, int v) {
            int x = find(u);
            int y = find(v);

            if (x == y)
                return false;

            parent[y] = x;

            return true;
        }

    }

    public static void main(String... s) {
        Node node1 = new Node(0);
        Node node2 = new Node(1);
        Node node3 = new Node(2);
        node1.neighbors.add(node2);
        node2.neighbors.add(node3);
        node3.neighbors.add(node1);
        int[][] edges = { { 0, 1 }, { 1, 2 }, { 2, 1 } };
        int v = 3;

        UF uf = new UF(3);

        if (edges.length != v - 1) {
            System.out.println(false);
            return;
        }

        for (int[] edge : edges) {
            if (!uf.union(edge[0], edge[1])) {
                System.out.println(true);
                return;
            }
        }

        System.out.println(false);
    }

}
