package unionFind.connectedComponents;

import java.util.*;

public class ConnectedComponents {

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
        int[][] edges = { { 0, 1 }, { 1, 2 }, { 3, 4 } };
        int n = 5;

        UF uf = new UF(n);

        for (int[] edge : edges) {
            uf.union(edge[0], edge[1]);
        }

        int count = 0;

        for (int i = 0; i < 5; i++) {
            if (uf.find(i) == i) {
                count++;
            }
        }

        System.out.println(count); // 2
    }

}