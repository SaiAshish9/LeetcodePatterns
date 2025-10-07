package shortestPath.dijkstra.implementation;

import java.util.*;

public class Dijkstra {
    
    private static int shortestPath(Map<Integer, List<int[]>> graph, int n, int src, int dest) {
        int[] distances = new int[n];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[src] = 0;
        
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[]{src, 0}); 
        
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int node = curr[0];
            int dist = curr[1];
            
            if (dist > distances[node]) continue; 
            
            if (node == dest) return dist; 
            
            for (int[] next : graph.getOrDefault(node, Collections.emptyList())) {
                int nextNode = next[0];
                int weight = next[1];
                int newDist = dist + weight;
                
                if (newDist < distances[nextNode]) {
                    distances[nextNode] = newDist;
                    pq.offer(new int[]{nextNode, newDist});
                }
            }
        }
        
        return distances[dest];
    }
    
    public static void main(String... s) {
        int[][] edges = {
            {0,1,4},
            {0,2,1},
            {1,3,1},
            {2,1,2},
            {2,3,5},
            {3,4,3}
        };
        int n = 5;
        Map<Integer, List<int[]>> graph = new HashMap<>();
        
        for (int[] edge : edges) {
            int from = edge[0], to = edge[1], weight = edge[2];
            graph.computeIfAbsent(from, k -> new ArrayList<>()).add(new int[]{to, weight});
        }
        
        int result = shortestPath(graph, n, 0, 4);
        System.out.println("Shortest distance from 0 to 4 = " + result);
        // 7
    }
}

/*
Time Complexity: O((V + E) log V) where V is the number of vertices and E is the number of edges.
Space Complexity: O(V) for the distance array and priority queue.
 */