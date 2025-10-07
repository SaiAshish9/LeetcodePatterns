package shortestPath.flyodWarshall;

import java.util.*;

public class FloydWarshall {

    private static void shortestPaths(int[][] edges, int n) {
        int INF = Integer.MAX_VALUE / 2;
        int[][] dist = new int[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }

        for (int[] edge : edges) {
            dist[edge[0]][edge[1]] = edge[2];
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] != INF && dist[k][j] != INF) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (dist[i][j] >= INF) {
                    System.out.print("INF ");
                } else {
                    System.out.print(dist[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String... s) {
        int n = 5;
        int[][] edges = {
                { 0, 1, 3 },
                { 0, 4, 5 },
                { 1, 2, 1 },
                { 2, 3, 2 }
        };
        shortestPaths(edges, n); 
        // O(n^3) time | O(1) space
    }
}

/*
 * 0 3 4 6 5 
INF 0 1 3 INF 
INF INF 0 2 INF 
INF INF INF 0 INF 
INF INF INF INF 0 
 */