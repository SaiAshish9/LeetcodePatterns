package scc.kosaraju.implementation;

import java.util.*;

public class KosarajuSCC {

    private static void dfs(int node, Map<Integer, List<Integer>> graph, boolean[] visited, Stack<Integer> stack) {
        visited[node] = true;
        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor, graph, visited, stack);
            }
        }
        stack.push(node);
    }

    private static void dfsOnTranspose(int node, Map<Integer, List<Integer>> transpose, boolean[] visited, List<Integer> component) {
        visited[node] = true;
        component.add(node);
        for (int neighbor : transpose.get(node)) {
            if (!visited[neighbor]) {
                dfsOnTranspose(neighbor, transpose, visited, component);
            }
        }
    }

    public static List<List<Integer>> kosaraju(int[][] edges, int n) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        
        for(int[] edge: edges) {
            graph
            .computeIfAbsent(edge[0], x -> new ArrayList<>())
            .add(edge[1]);
        }
        
        for (int i = 0; i < n; i++) {
            if (!graph.containsKey(i)) {
                graph.put(i, new ArrayList<>()); 
            }
        }
        
        boolean[] visited = new boolean[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            if (!visited[i]) dfs(i, graph, visited, stack);
        }
        
        Map<Integer, List<Integer>> transpose = new HashMap<>();
        for (int i = 0; i < n; i++) transpose.put(i, new ArrayList<>());
        for (int u = 0; u < n; u++) {
            for (int v : graph.get(u)) {
                transpose.get(v).add(u);
            }
        }
        
        Arrays.fill(visited, false);
        List<List<Integer>> scc = new ArrayList<>();
        
        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (!visited[node]) {
                List<Integer> component = new ArrayList<>();
                dfsOnTranspose(node, transpose, visited, component);
                scc.add(component);
            }
        }

        return scc;
    }

    public static void main(String[] args) {
        int[][] edges = {
            {0, 1}, {1, 2}, {2, 0}, {1, 3}, {3, 4}
        };
        int n = 5;
        List<List<Integer>> sccs = kosaraju(edges, n);
        System.out.println("Strongly Connected Components: " + sccs);
    }
    
}

/*
 * Complexity Analysis:
 * Time Complexity: O(V + E), where V is the number of vertices and E is the number of edges in the graph. We perform two DFS traversals, each taking O(V + E) time.
 * Space Complexity: O(V + E) for storing the graph and its transpose, and O(V) for the stack and visited array.
 */
