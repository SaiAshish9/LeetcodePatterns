package backtracking.q797;

import java.util.*;

public class AllPathsFromSourceToTarget {
    private static List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        dfs(graph, 0, path, result);
        return result;
    }

    private static void dfs(int[][] graph, int node, List<Integer> path, List<List<Integer>> result) {
        path.add(node);
        if (node == graph.length - 1) {
            result.add(new ArrayList<>(path));
        } else {
            for (int next : graph[node]) {
                dfs(graph, next, path, result);
            }
        }
        path.remove(path.size() - 1);
    }

    public static void main(String[] args) {
        AllPathsFromSourceToTarget solution = new AllPathsFromSourceToTarget();
        int[][] graph = {{1, 2}, {3}, {3}, {}};
        System.out.println(allPathsSourceTarget(graph)); // Output: [[0, 1, 3], [0, 2, 3]]
    }
}

// Complexity Analysis
// Time Complexity: O(2^N * N) in the worst case, where N is the number of nodes in the graph. This is because there can be up to 2^(N-1) paths in a DAG, and each path can take O(N) time to copy to the result list.
// Space Complexity: O(N) for the recursion stack and the path list. The output list can take up to O(2^N * N) space in the worst case to store all paths.