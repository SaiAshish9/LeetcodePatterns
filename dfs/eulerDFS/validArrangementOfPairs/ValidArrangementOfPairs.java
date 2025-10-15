package dfs.eulerDFS.validArrangementOfPairs;

import java.util.*;

public class ValidArrangementOfPairs {
    public int[][] validArrangement(int[][] pairs) {
        List<int[]> ans = new ArrayList<>();
        Map<Integer, Stack<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> outDegree = new HashMap<>();
        Map<Integer, Integer> inDegrees = new HashMap<>();

        for (int[] pair : pairs) {
            final int start = pair[0];
            final int end = pair[1];
            graph.putIfAbsent(start, new Stack<>());
            graph.get(start).push(end);
            outDegree.merge(start, 1, Integer::sum);
            inDegrees.merge(end, 1, Integer::sum);
        }

        final int startNode = getStartNode(graph, outDegree, inDegrees, pairs);
        eulerDFS(graph, startNode, ans);
        Collections.reverse(ans);
        return ans.stream().toArray(int[][]::new);
    }

    private int getStartNode(Map<Integer, Stack<Integer>> graph, Map<Integer, Integer> outDegree,
            Map<Integer, Integer> inDegrees, int[][] pairs) {
        for (final int u : graph.keySet())
            if (outDegree.getOrDefault(u, 0) - inDegrees.getOrDefault(u, 0) == 1)
                return u;
        return pairs[0][0];
    }

    private void eulerDFS(Map<Integer, Stack<Integer>> graph, int u, List<int[]> ans) {
        Stack<Integer> stack = graph.get(u);
        while (stack != null && !stack.isEmpty()) {
            final int v = stack.pop();
            eulerDFS(graph, v, ans);
            ans.add(new int[] { u, v });
        }
    }

    public static void main(String[] args) {
        ValidArrangementOfPairs obj = new ValidArrangementOfPairs();
        int[][] pairs = { { 5, 1 }, { 4, 5 }, { 11, 9 }, { 9, 4 } };
        int[][] result = obj.validArrangement(pairs);
        for (int[] pair : result) {
            System.out.println(Arrays.toString(pair));
        }
        // Expected output (one of the valid arrangements):
        // [11, 9]
        // [9, 4]
        // [4, 5]
        // [5, 1]   
    }
}

// Complexity Analysis:
// Time Complexity: O(E + V) where E is the number of edges (pairs) and V is the number of vertices (unique integers in pairs).
// Space Complexity: O(E + V) for storing the graph and the result list.