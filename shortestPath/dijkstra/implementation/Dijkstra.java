package shortestPath.dijkstra.implementation;

import java.util.*;

public class Dijkstra {

    private static int shortestPath(Map<Integer, List<int[]>> graph, int n, int src, int dest) {
        int[] distances = new int[n];
        int[] parent = new int[n];
        Arrays.fill(distances, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);

        distances[src] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[] { src, 0 });

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int node = curr[0];
            int dist = curr[1];

            if (dist > distances[node])
                continue;

            for (int[] next : graph.getOrDefault(node, Collections.emptyList())) {
                int nextNode = next[0];
                int weight = next[1];
                int newDist = dist + weight;

                if (newDist < distances[nextNode]) {
                    distances[nextNode] = newDist;
                    parent[nextNode] = node; // store path info
                    pq.offer(new int[] { nextNode, newDist });
                }
            }
        }

        // reconstruct path from src to dest
        List<Integer> path = new ArrayList<>();
        if (distances[dest] == Integer.MAX_VALUE) {
            System.out.println("No path exists from " + src + " to " + dest);
            return -1;
        }

        for (int at = dest; at != -1; at = parent[at]) {
            path.add(at);
        }
        Collections.reverse(path);

        System.out.println("Shortest distance from " + src + " to " + dest + " = " + distances[dest]);
        System.out.println("Path: " + path);

        return distances[dest];
    }

    public static void main(String... s) {
        int[][] edges = {
                { 0, 1, 4 },
                { 0, 2, 1 },
                { 1, 3, 1 },
                { 2, 1, 2 },
                { 2, 3, 5 },
                { 3, 4, 3 }
        };
        int n = 5;
        Map<Integer, List<int[]>> graph = new HashMap<>();

        for (int[] edge : edges) {
            int from = edge[0], to = edge[1], weight = edge[2];
            graph.computeIfAbsent(from, k -> new ArrayList<>()).add(new int[] { to, weight });
        }

        shortestPath(graph, n, 0, 4); // 7
        /*
         * Shortest distance from 0 to 4 = 7
         * Path: [0, 2, 1, 3, 4]
         * 
         */
    }
}

/*
 * Time Complexity: O((V + E) log V)
 * Space Complexity: O(V)
 */
