package scc.criticalComponents.implementation;

import java.util.*;

public class CriticalComponents {
    List<List<Integer>> graph;
    List<List<Integer>> result;
    int[] disc;
    int[] low;
    int time;

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        graph = new ArrayList<>();
        for (int i = 0; i < n; i++)
            graph.add(new ArrayList<>());
        for (List<Integer> conn : connections) {
            int u = conn.get(0);
            int v = conn.get(1);
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        disc = new int[n];
        low = new int[n];
        Arrays.fill(disc, -1);
        Arrays.fill(low, -1);
        result = new ArrayList<>();
        time = 0;

        dfs(0, -1);
        return result;
    }

    private void dfs(int u, int parent) {
        low[u] = disc[u] = ++time;
        for (int v : graph.get(u)) {
            if (v == parent) continue;

            if (disc[v] == -1) { // if v is not visited
                dfs(v, u);
                low[u] = Math.min(low[u], low[v]);
                if (low[v] > disc[u]) {
                    // (u, v) is a critical connection
                    result.add(Arrays.asList(u, v));
                }
            } else {
                low[u] = Math.min(low[u], disc[v]);
            }
        }
    }

    public static void main(String[] args) {
        int n = 4; 
        List<List<Integer>> connections = new ArrayList<>();
        connections.add(Arrays.asList(0, 1));
        connections.add(Arrays.asList(1, 2));
        connections.add(Arrays.asList(2, 0));
        connections.add(Arrays.asList(1, 3));

        CriticalComponents solver = new CriticalComponents();
        List<List<Integer>> bridges = solver.criticalConnections(n, connections);

        System.out.println("Critical connections (bridges):");
        for (List<Integer> bridge : bridges) {
            System.out.println(bridge); // Output: [1, 3]
        }
    }
}

/*
 * In the context of finding critical connections (bridges), if the low link
 * value of a vertex
 * v is greater than the discovery time of its parent vertex u (in the DFS
 * tree), then the edge (u, v)
 * is a bridge. This is because there is no alternative way to reach vertex v
 * or its descendants from vertex u or any of its ancestors, other than through
 * the edge (u, v).
 */