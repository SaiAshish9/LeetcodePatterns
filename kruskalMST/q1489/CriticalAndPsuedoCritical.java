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

        int mstWeight = getMSTWeight(n, edges, new int[] {}, -1);

        for (int[] edge : edges) {
            int index = edge[3];
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
            int u = edge[0];
            int v = edge[1];
            int weight = edge[2];
            int index = edge[3];
            if (index == deletedEdgeIndex)
                continue;
            uf.union(u, v);
            mstWeight += weight;
        }

        int root = uf.find(0);
        for (int i = 0; i < n; ++i)
            if (uf.find(i) != root)
                return Integer.MAX_VALUE;

        return mstWeight;
    }

    public static void main(String[] args) {
        CriticalAndPsuedoCritical sol = new CriticalAndPsuedoCritical();
        int n = 14;
        int[][] edges = new int[][]{
            {0, 1, 1}, 
            {1, 2, 1},
            {2, 3, 2},
            {0, 3, 2},
            {0, 4, 3},
            {3, 4, 3},
            {1, 4, 6},
        };
        List<List<Integer>> result = sol.findCriticalAndPseudoCriticalEdges(n, edges);
        System.out.println(result); 
        // Complexity Analysis
        // Time Complexity: O(E^2 * α(N)), where E is the number of edges, N is the number of
        // nodes, and α is the inverse Ackermann function.
        // Space Complexity: O(N) for the Union-Find data structure.
    }
}
