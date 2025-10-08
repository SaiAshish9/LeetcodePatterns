package scc.tarjan.implementation;

import java.util.*;

public class Tarjan {
    static class Node {
        int val;
        List<Node> neighbors;

        Node(int val) {
            this.val = val;
            neighbors = new ArrayList<>();
        }
    }

    static class Graph {
        Node[] nodes;
        int V;
        int time;
        int[] disc;
        int[] low;
        boolean[] onStack;
        Stack<Node> stack;

        Graph(int V) {
            this.V = V;
            nodes = new Node[V];
            for (int i = 0; i < V; i++)
                nodes[i] = new Node(i);
        }

        void addEdge(int u, int v) {
            nodes[u].neighbors.add(nodes[v]);
        }

        void tarjansSCC() {
            disc = new int[V]; // Discovery times
            low = new int[V]; // Low-link values
            onStack = new boolean[V]; // Is the node in stack?
            stack = new Stack<>();

            Arrays.fill(disc, -1); // Unvisited

            System.out.println("Strongly Connected Components (SCCs):");
            for (int i = 0; i < V; i++) {
                if (disc[i] == -1)
                    dfs(nodes[i]);
            }
        }

        void dfs(Node u) {
            disc[u.val] = low[u.val] = ++time;
            stack.push(u);
            onStack[u.val] = true;

            for (Node v : u.neighbors) {
                if (disc[v.val] == -1) {
                    dfs(v);
                    low[u.val] = Math.min(low[u.val], low[v.val]);
                    // why low[u] = min(low[u], low[v])?
                    // because if v is part of an SCC, it might connect back to an ancestor of u
                    // thus we need to consider the lowest reachable vertex from v
                } else if (onStack[v.val]) {
                    low[u.val] = Math.min(low[u.val], disc[v.val]);
                }
            }

            // If u is the head of an SCC
            if (low[u.val] == disc[u.val]) {
                Node v;
                do {
                    v = stack.pop();
                    onStack[v.val] = false;
                    System.out.print(v.val + " ");
                } while (v != u);
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        Graph g = new Graph(5);

        // Initial graph representation:
        // 0 -> 2
        // 2 -> 1
        // 1 -> 0
        // 0 -> 3
        // 3 -> 4
        g.addEdge(0, 2);
        g.addEdge(2, 1);
        g.addEdge(1, 0);
        g.addEdge(0, 3);
        g.addEdge(3, 4);

        g.tarjansSCC();
    }
}

/*
 * Complexity Analysis:
 * Time Complexity: O(V + E), where V is the number of vertices and E is the number of edges.
 * Each vertex and edge is processed once.  
 * Space Complexity: O(V), for the stack, discovery, low-link, and onStack arrays.
 */