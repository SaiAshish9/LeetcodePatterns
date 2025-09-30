package nAryTree.binaryLifting;

import java.util.*;

public class SumOfDistancesBinaryLifting {
    static int N, LOG;
    static List<Integer>[] graph;
    static int[][] up;       // up[v][k] = 2^k ancestor of v
    static int[] depth;

    private static void dfs(int node, int parent) {
        up[node][0] = parent;
        for (int k = 1; k < LOG; k++) {
            if (up[node][k - 1] != -1)
                up[node][k] = up[up[node][k - 1]][k - 1];
            else
                up[node][k] = -1;
        }
        for (int nei : graph[node]) {
            if (nei != parent) {
                depth[nei] = depth[node] + 1;
                dfs(nei, node);
            }
        }
    }

    private static int lca(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        int diff = depth[u] - depth[v];
        for (int k = LOG - 1; k >= 0; k--) {
            if (((diff >> k) & 1) == 1)
                u = up[u][k];
        }



/*
if (k & (1 << j)) {
    node = up[node][j];
}
means:
👉 “If the j-th bit of k is set, then move the node upward by 
2
𝑗
2 
j
  steps.”

That line:

if (((diff >> k) & 1) == 1)

is a bitwise check. Let’s break it down:

diff >> k → right-shifts the number diff by k positions.
This effectively brings the k-th bit (counting from 0 at the least significant bit) into the 
least significant bit (LSB) position.

& 1 → bitwise AND with 1.
This extracts the LSB of the shifted result. So it’s either 0 or 1.

== 1 → checks whether that extracted bit is set (1) or not (0).

🔎 Meaning:
The condition is true if the k-th bit of diff is set to 1.
Otherwise, it’s false.
 */

        if (u == v) return u;

        for (int k = LOG - 1; k >= 0; k--) {
            if (up[u][k] != -1 && up[u][k] != up[v][k]) {
                u = up[u][k];
                v = up[v][k];
            }
        }
        return up[u][0];
    }

    private static int distance(int u, int v) {
        int l = lca(u, v);
        return depth[u] + depth[v] - 2 * depth[l];
    }

    private static List<Integer> computeSumDistances(int n) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = 0; j < n; j++) {
                sum += distance(i, j);
            }
            res.add(sum);
        }
        return res;
    }

    public static void main(String[] args) {
        N = 6;
        int[][] edges = new int[][] {
            {0, 1}, {0, 2}, {2, 3}, {2, 4}, {2, 5}
        };

        graph = new ArrayList[N];
        for (int i = 0; i < N; i++) graph[i] = new ArrayList<>();
        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }

        LOG = 32 - Integer.numberOfLeadingZeros(N); // ~ log2(N)
        up = new int[N][LOG];
        depth = new int[N];
        dfs(0, -1);

        List<Integer> result = computeSumDistances(N);
        System.out.println(result); // [8, 12, 6, 10, 10, 10]
    }
}
