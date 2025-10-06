package primsMST.q1168;

import java.util.*;

public class Main {

    private static int minCostToSupplyWater(int[] wells, int n, int[][] pipes, int src) {
        Map<Integer, List<int[]>> graph = new HashMap<>();

        for (int i = 0; i < n; i++) {
            graph
                    .computeIfAbsent(src, k -> new ArrayList<>())
                    .add(new int[] {
                            src, i + 1, wells[i]
                    });

            graph
                    .computeIfAbsent(i + 1, k -> new ArrayList<>())
                    .add(new int[] {
                            i + 1, src, wells[i]
                    });
        }

        for (int[] pipe : pipes) {
            graph
                    .computeIfAbsent(pipe[0], k -> new ArrayList<>())
                    .add(new int[] {
                            pipe[0], pipe[1], pipe[2]
                    });

            graph
                    .computeIfAbsent(pipe[1], k -> new ArrayList<>())
                    .add(new int[] {
                            pipe[1], pipe[0], pipe[2]
                    });
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        pq.addAll(graph.getOrDefault(src, new ArrayList<>()));
        boolean[] visited = new boolean[n + 1];
        visited[src] = true;
        int mstWeight = 0;

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int destination = curr[1];
            int weight = curr[2];

            if (visited[destination]) {
                continue;
            }
            visited[destination] = true;
            mstWeight += weight;

            for (int[] neighbor : graph.getOrDefault(destination, new ArrayList<>())) {
                int nextSource = neighbor[1];
                if (!visited[nextSource]) {
                    pq.offer(neighbor);
                }
            }
        }

        return mstWeight;
    }

    public static void main(String... s) {
        int[] wells = {
                1, 2, 2
        };
        int n = 3;
        int[][] pipes = {
                { 1, 2, 1 },
                { 2, 3, 1 }
        };
        System.out.println(minCostToSupplyWater(wells, n, pipes, 0));
    }

}