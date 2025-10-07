package shortestPath.q787;

import java.util.*;

public class CheapestFlightWithKStops {

    private static int minCost(int[][] flights, int src, int dest, int k, int n) {
        Map<Integer, List<int[]>> graph = new HashMap<>();

        for (int[] flight : flights) {
            graph.computeIfAbsent(flight[0], x -> new ArrayList<>())
                    .add(new int[]{flight[1], flight[2]});
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[]{src, 0, 0}); 

        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        int[] stops = new int[n];
        Arrays.fill(stops, Integer.MAX_VALUE);
        stops[src] = 0;

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int node = curr[0];
            int cost = curr[1];
            int stop = curr[2];

            if (cost > dist[node] || stop > stops[node]) continue;

            if (node == dest) return cost;

            if (stop > k) continue;

            for (int[] neighbor : graph.getOrDefault(node, Collections.emptyList())) {
                int nextNode = neighbor[0];
                int nextCost = cost + neighbor[1];
                int nextStop = stop + 1;

                if (nextCost < dist[nextNode] || nextStop < stops[nextNode]) {
                    dist[nextNode] = nextCost;
                    stops[nextNode] = nextStop;
                    pq.offer(new int[]{nextNode, nextCost, nextStop});
                }
            }
        }

        return -1;
    }

    public static void main(String... s) {
        int[][] flights = {
                {0, 1, 100},
                {1, 2, 200},
                {0, 2, 500}
        };
        int n = 4, src = 0, dst = 2, k = 1;

        System.out.println(minCost(flights, src, dst, k, n)); // 300
    }
}
/*
 * Time Complexity: O(E log V), where E is the number of edges and V is the number of vertices. Each edge is processed once, and each operation on the priority queue takes O(log V) time.
 * Space Complexity: O(V + E), for storing the graph and the priority queue.
 */