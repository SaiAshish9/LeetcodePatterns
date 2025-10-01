package topologicalSorting.TSDFS;

import java.util.*;

class TSDFS {

    static class Node {
        private int data;
        private List<Node> neighbors;

        Node(int data) {
            this.data = data;
            this.neighbors = new ArrayList<>();
        }
    }

    private static void dfs(Node node, Stack<Integer> stack, boolean[] visited) {
        visited[node.data] = true;

        List<Node> neighbors = node.neighbors;

        for (Node neighbor : neighbors) {
            if (!visited[neighbor.data]) {
                dfs(neighbor, stack, visited);
            }
        }

        stack.push(node.data);
    }

    private static void computeTopologicalSort(Node node, int n) {
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[n];
        Arrays.fill(visited, false);
        dfs(node, stack, visited);

        System.out.println(stack.size());

        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }

    }

    public static void main(String... s) {
        Node node1 = new Node(0);
        Node node2 = new Node(1);
        Node node3 = new Node(2);
        Node node4 = new Node(3);
        Node node5 = new Node(4);
        node1.neighbors.add(node2);
        node2.neighbors.add(node3);
        node3.neighbors.add(node4);
        node4.neighbors.add(node5);
        computeTopologicalSort(node1, 5);
    }

}