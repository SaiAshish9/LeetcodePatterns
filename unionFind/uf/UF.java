package unionFind.uf;

public class UF {
    static class UnionFind {
        int[] parent;

        UnionFind(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int u) {
            if (u != parent[u]) {
                parent[u] = find(parent[u]); // path compression
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
        UnionFind uf = new UnionFind(5);
        System.out.println(uf.find(1));
        uf.union(1, 2);
        uf.union(2, 3);
        System.out.println(uf.find(1));
        System.out.println(uf.find(2));
        System.out.println(uf.find(3));
    }

}

/*
 * 1
 * 1
 * 1
 * 1
 */

// Complexity Analysis
// Time Complexity: O(N) for the find operation in the worst case, where N is
// the number of elements. The union operation is O(1).
// Space Complexity: O(N) for storing the parent array.