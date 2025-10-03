package kruskalMST.implementation;

import java.util.*;

public class KruskalMST {

    static private class UF {
        int[] parent;
        int[] rank;

        public UF(int n) {
            parent = new int[n];
            rank = new int[n];

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

            if (rank[x] < rank[y]) {
                parent[x] = y;
            } else if (rank[y] < rank[x]) {
                parent[y] = x;
            } else {
                parent[y] = x;
                rank[x]++;
            }

            return true;
        }

    }

    public static void main(String... s) {
        int[][] edges = {
                { 0, 1, 10 }, { 0, 2, 6 }, { 0, 3, 5 }, { 1, 3, 15 }, { 2, 3, 4 }
        };

        int n = 4;
        int mstWeight = 0;

        UF uf = new UF(n);
        Arrays.sort(edges, (a, b) -> a[2] - b[2]);

        for (int[] edge : edges) {
            if (uf.union(edge[0], edge[1])) {
                mstWeight += edge[2];
            }
        }

        System.out.println(mstWeight); // Output: 19
        // Complexity Analysis
        // Time Complexity: O(E log E) or O(E log V), where E is the number of edges and V is
        // the number of vertices. Sorting the edges takes O(E log E) time, and each union/find operation takes nearly constant time, O(α(V)), where α is the inverse Ackermann function.
        // Space Complexity: O(V) for storing the parent and rank arrays in the Union-Find data structure.
    }

}