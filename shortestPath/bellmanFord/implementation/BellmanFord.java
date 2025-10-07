package shortestPath.bellmanFord.implementation;

import java.util.*;

public class BellmanFord {

    private static int shortestPath(int[][] edges, int src, int dest, int n) {
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        // Relax all edges (n - 1) times
        // Why n-1 times? Because the longest possible path without a cycle
        // between any two vertices can have at most (n - 1) edges.
        for (int i = 0; i < n - 1; i++) {
            boolean updated = false;
            for (int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];
                int w = edge[2];

                if (dist[u] != Integer.MAX_VALUE && dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                    updated = true;
                }
            }
            // Optimization: stop early if no update occurred in this iteration
            if (!updated) break;
        }

        // Step 2: Detect negative weight cycle
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];

            if (dist[u] != Integer.MAX_VALUE && dist[u] + w < dist[v]) {
                System.out.println("⚠️ Negative weight cycle detected!");
                return Integer.MIN_VALUE;
            }
        }

        return dist[dest];
    }

    public static void main(String... s) {
        int n = 5;
        int[][] edges = {
            {0, 1, -1},
            {0, 2, 4},
            {1, 2, 3},
            {1, 3, 2},
            {1, 4, 2},
            {3, 2, 5},
            {3, 1, 1},
            {4, 3, -3}
        };

        int result = shortestPath(edges, 0, 4, n);
        if (result != Integer.MIN_VALUE) {
            System.out.println("Shortest distance from 0 to 4 = " + result);
        }
    }
}

/*
 * Complexity Analysis:
 * Time Complexity: O(V * E), where V is the number of vertices and E is the number of edges. The algorithm relaxes all edges (E) for (V - 1) iterations.
 * Space Complexity: O(V), for storing the distance array.
 */