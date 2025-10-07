package primsMST.implementation;

import java.util.*;

public class PrimsMST {

    private static int computePrimsMSTWeight(Map<Integer, List<int[]>> graph, int src, int n) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);

        int mstWeight = 0;
        boolean[] visited = new boolean[n];
        pq.addAll(graph.getOrDefault(src, Collections.emptyList()));
        visited[src] = true;

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int destination = curr[0];
            int weight = curr[1];

            if (visited[destination])
                continue;
            visited[destination] = true;
            mstWeight += weight;

            for (int[] neighbor : graph.getOrDefault(destination, Collections.emptyList())) {
                int nextDestination = neighbor[0];

                if (!visited[nextDestination]) {
                    pq.offer(neighbor);
                }

            }

        }

        return mstWeight;
    }

    public static void main(String... s) {
        int[][] edges = {
                { 0, 1, 10 }, { 0, 2, 6 }, { 0, 3, 5 },
                { 1, 3, 15 }, { 2, 3, 4 }
        };

        Map<Integer, List<int[]>> graph = new HashMap<>();

        for (int[] edge : edges) {
            graph
                    .computeIfAbsent(edge[0], k -> new ArrayList<>())
                    .add(new int[] { edge[1], edge[2] });

            graph
                    .computeIfAbsent(edge[1], k -> new ArrayList<>())
                    .add(new int[] { edge[0], edge[2] });
        }

        int n = 4;
        System.out.println(computePrimsMSTWeight(graph, 0, n)); // 19

        // Complexity Analysis
        // Time Complexity: O(E log E) where E is the number of edges in the graph.
        // Space Complexity: O(E) where E is the number of edges in the graph
    }

}