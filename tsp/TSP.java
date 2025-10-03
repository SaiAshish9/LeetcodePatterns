package tsp;

import java.util.*;

public class TSP {

    private static void dfs(int current, int count, int cost, int start, int[][] graph, int n, int[] minCost,
            boolean[] visited) {
        if (count == n && graph[current][start] > 0) {
            minCost[0] = Math.min(minCost[0], cost + graph[current][start]);
            return;
        }

        visited[current] = true;

        for (int next = 0; next < n; next++) {
            if (!visited[next] && graph[current][next] > 0) {
                dfs(next, count + 1, cost + graph[current][next], start, graph, n, minCost, visited);
            }
        }

        visited[current] = false;
    }

    public static void main(String... s) {

        int[][] graph = {
                { 0, 10, 15, 20 },
                { 10, 0, 35, 25 },
                { 15, 35, 0, 30 },
                { 20, 25, 30, 0 }
        };
        int n = 4;

        boolean[] visited = new boolean[n];
        int[] minCost = { Integer.MAX_VALUE };

        dfs(0, 1, 0, 0, graph, n, minCost, visited);

        System.out.println(Arrays.deepToString(graph));
        System.out.println(minCost[0]);

    }

}
