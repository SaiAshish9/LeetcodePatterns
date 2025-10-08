package bcc.implementation;

import java.util.*;

public class BCC {
    static class Edge {
        int u, v;

        Edge(int u, int v) {
            this.u = u;
            this.v = v;
        }

        public String toString() {
            return "(" + u + ", " + v + ")";
        }
    }

    private static int time = 0;
    private static int[] disc, low;
    private static List<List<Edge>> bccList = new ArrayList<>();
    private static Map<Integer, List<Integer>> graph = new HashMap<>();
    private static Deque<Edge> stack = new ArrayDeque<>();

    private static void findBCCs(int n, List<List<Integer>> edges) {
        disc = new int[n];
        low = new int[n];

        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (List<Integer> e : edges) {
            int u = e.get(0), v = e.get(1);
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        for (int i = 0; i < n; i++) {
            if (disc[i] == 0) {
                dfs(i, -1);
                if (!stack.isEmpty()) {
                    List<Edge> bcc = new ArrayList<>();
                    while (!stack.isEmpty()) {
                        bcc.add(stack.pop());
                    }
                    bccList.add(bcc);
                }
            }
        }

        // Print all BCCs
        int idx = 1;
        for (List<Edge> bcc : bccList) {
            System.out.println("BCC #" + idx++ + ": " + bcc);
        }
    }

    private static void dfs(int u, int parent) {
        disc[u] = low[u] = ++time;

        for (int v : graph.get(u)) {
            if (v == parent)
                continue;

            if (disc[v] == 0) {
                stack.push(new Edge(u, v));
                dfs(v, u);
                low[u] = Math.min(low[u], low[v]);

                if (low[v] >= disc[u]) {
                    List<Edge> bcc = new ArrayList<>();
                    Edge e;
                    do {
                        e = stack.pop();
                        bcc.add(e);
                    } while (!(e.u == u && e.v == v) && !(e.u == v && e.v == u));
                    bccList.add(bcc);
                }
            } else if (disc[v] < disc[u]) {
                stack.push(new Edge(u, v));
                low[u] = Math.min(low[u], disc[v]);
            }
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> edges = Arrays.asList(
                Arrays.asList(0, 1), Arrays.asList(1, 2), Arrays.asList(2, 0),
                Arrays.asList(1, 3), Arrays.asList(1, 4), Arrays.asList(1, 6),
                Arrays.asList(3, 5), Arrays.asList(4, 5));

        findBCCs(7, edges);
    }
}
