package unionFind.ufRank;

public class UFRank {
    static class UF {

        int[] parent;
        int[] rank;

        UF(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0;
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

            if (rank[x] < rank[y]) {
                parent[x] = y;
            } else if (rank[x] > rank[y]) {
                parent[y] = x;
            } else {
                parent[y] = x;
                rank[x]++;
            }

            return true;
        }
    }

    public static void main(String... s) {
        UF uf = new UF(5);
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