package kruskalMST.q1489;

import java.util.*;

class CriticalAndPsuedoCritical {

    static private class UnionFind {
        int[] parent;
        int[] rank;

        public UnionFind(int n) {
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

    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        List<Integer> criticalEdges = new ArrayList<>();
        List<Integer> pseudoCriticalEdges = new ArrayList<>();

        for (int i = 0; i < edges.length; ++i)
            edges[i] = new int[] { edges[i][0], edges[i][1], edges[i][2], i };

        Arrays.sort(edges, (a, b) -> a[2] - b[2]);

        final int mstWeight = getMSTWeight(n, edges, new int[] {}, -1);

        for (int[] edge : edges) {
            final int index = edge[3];
            // Deleting the `edge` increases the MST's weight or makes the MST invalid.
            if (getMSTWeight(n, edges, new int[] {}, index) > mstWeight)
                criticalEdges.add(index);
            // If an edge can be in any MST, we can always add `edge` to the edge set.
            else if (getMSTWeight(n, edges, edge, -1) == mstWeight)
                pseudoCriticalEdges.add(index);
        }

        return new ArrayList<>(Arrays.asList(criticalEdges, pseudoCriticalEdges));
    }

    private int getMSTWeight(int n, int[][] edges, int[] firstEdge, int deletedEdgeIndex) {
        int mstWeight = 0;
        UnionFind uf = new UnionFind(n);

        if (firstEdge.length == 4) {
            uf.union(firstEdge[0], firstEdge[1]);
            mstWeight += firstEdge[2];
        }

        for (int[] edge : edges) {
            final int u = edge[0];
            final int v = edge[1];
            final int weight = edge[2];
            final int index = edge[3];
            if (index == deletedEdgeIndex)
                continue;
            if (uf.find(u) == uf.find(v))
                continue;
            uf.union(u, v);
            mstWeight += weight;
        }

        final int root = uf.find(0);
        for (int i = 0; i < n; ++i)
            if (uf.find(i) != root)
                return Integer.MAX_VALUE;

        return mstWeight;
    }

    public static void main(String[] args) {
        CriticalAndPsuedoCritical sol = new CriticalAndPsuedoCritical();
        int n = 14;
        int[][] edges = {
                { 0, 1, 13 }, { 0, 2, 6 }, { 2, 3, 13 }, { 3, 4, 4 }, { 0, 5, 11 }, { 4, 6, 14 }, { 4, 7, 8 },
                { 2, 8, 6 }, { 4, 9, 6 }, { 7, 10, 4 }, { 5, 11, 3 }, { 6, 12, 7 }, { 12, 13, 9 }, { 7, 13, 2 },
                { 5, 13, 10 }, { 0, 6, 4 }, { 2, 7, 3 }, { 0, 7, 8 }, { 1, 12, 9 }, { 10, 12, 11 }, { 1, 2, 7 },
                { 1, 3, 10 }, { 3, 10, 6 }, { 6, 10, 4 }, { 4, 8, 5 }, { 1, 13, 4 }, { 11, 13, 8 }, { 2, 12, 10 },
                { 5, 8, 1 }, { 3, 7, 6 }, { 7, 12, 12 }, { 1, 7, 9 }, { 5, 9, 1 }, { 2, 13, 10 }, { 10, 11, 4 },
                { 3, 5, 10 }, { 6, 11, 14 }, { 5, 12, 3 }, { 0, 8, 13 }, { 8, 9, 1 }, { 3, 6, 8 }, { 0, 3, 4 },
                { 2, 9, 6 }, { 0, 11, 4 }, { 2, 5, 14 }, { 4, 11, 2 }, { 7, 11, 11 }, { 1, 11, 6 }, { 2, 10, 12 },
                { 0, 13, 4 }, { 3, 9, 9 }, { 4, 12, 3 }, { 6, 7, 10 }, { 6, 8, 13 }, { 9, 11, 3 }, { 1, 6, 2 },
                { 2, 4, 12 }, { 0, 10, 3 }, { 3, 12, 1 }, { 3, 8, 12 }, { 1, 8, 6 }, { 8, 13, 2 }, { 10, 13, 12 },
                { 9, 13, 11 }, { 2, 11, 14 }, { 5, 10, 9 }, { 5, 6, 10 }, { 2, 6, 9 }, { 8, 11, 3 }
        };
        List<List<Integer>> result = sol.findCriticalAndPseudoCriticalEdges(n, edges);
        System.out.println(result); 
        // Complexity Analysis
        // Time Complexity: O(E^2 * α(N)), where E is the number of edges, N is the number of
        // nodes, and α is the inverse Ackermann function.
        // Space Complexity: O(N) for the Union-Find data structure.
    }
}
