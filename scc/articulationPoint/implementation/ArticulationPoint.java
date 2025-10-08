package scc.articulationPoint.implementation;

import java.util.*;

public class ArticulationPoint {
    private int time = 0;
    private int[] disc, low, parent;
    private boolean[] isArticulation;
    private Map<Integer, List<Integer>> graph = new HashMap<>();

    public List<Integer> findArticulationPoints(int n, List<List<Integer>> edges) {
        disc = new int[n];
        low = new int[n];
        parent = new int[n];
        isArticulation = new boolean[n];

        Arrays.fill(parent, -1);
        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (List<Integer> edge : edges) {
            int u = edge.get(0), v = edge.get(1);
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        for (int i = 0; i < n; i++) {
            if (disc[i] == 0) {
                dfs(i);
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (isArticulation[i])
                result.add(i);
        }
        return result;
    }

    private void dfs(int u) {
        disc[u] = low[u] = ++time;
        int children = 0;

        for (int v : graph.get(u)) {
            if (disc[v] == 0) {
                children++;
                parent[v] = u;
                dfs(v);

                low[u] = Math.min(low[u], low[v]);

                if (parent[u] == -1 && children > 1) {
                    isArticulation[u] = true;
                }
                if (parent[u] != -1 && low[v] >= disc[u]) {
                    isArticulation[u] = true;
                }
            } else if (v != parent[u]) {
                low[u] = Math.min(low[u], disc[v]);
            }
        }
    }

    public static void main(String[] args) {
        ArticulationPoint solver = new ArticulationPoint();

        int n = 5;
        List<List<Integer>> edges = Arrays.asList(
                Arrays.asList(0, 1),
                Arrays.asList(1, 2),
                Arrays.asList(2, 3),
                Arrays.asList(3, 4));

        List<Integer> points = solver.findArticulationPoints(n, edges);
        System.out.println("Articulation Points: " + points);
        // Expected Output: [1, 2, 3]
    }
}

/*
 * Articulation Points in an undirected graph are vertices which, when removed
 * along with their associated edges, increase the number of connected
 * components in the graph.
 * In other words, they are critical nodes that, if taken out, would disconnect
 * parts of the graph.
 * 
 * Complexity Analysis:
 * Time Complexity: O(V + E), where V is the number of vertices and E is the
 * number of edges in the graph.
 * Space Complexity: O(V), for storing discovery and low arrays, parent array,
 * isArticulation array, and the graph representation.
 */
