package dfs.AllPathsFromSrcToDest;

import java.util.*;

public class AllPathsFromSrcToDest {

    static class Node {
        int val;
        List<Node> neighbors;

        Node(int val) {
            this.val = val;
            this.neighbors = new ArrayList<>();
        }

        @Override
        public String toString() {
            return String.valueOf(val);
        }
    }

    private static List<List<Node>> allPathsDfs(Node source, Node destination) {
        List<List<Node>> allPaths = new ArrayList<>();
        Set<Node> visited = new HashSet<>();
        List<Node> currentPath = new ArrayList<>();

        dfs(source, destination, visited, currentPath, allPaths);

        return allPaths;
    }

    private static void dfs(Node current, Node destination, Set<Node> visited,
            List<Node> currentPath, List<List<Node>> allPaths) {

        visited.add(current);
        currentPath.add(current);

        if (current == destination) {
            allPaths.add(new ArrayList<>(currentPath));
        } else {
            for (Node neighbor : current.neighbors) {
                if (!visited.contains(neighbor)) {
                    dfs(neighbor, destination, visited, currentPath, allPaths);
                }
            }
        }

        currentPath.remove(currentPath.size() - 1);
        visited.remove(current);
    }

    public static void main(String[] args) {
        Node node0 = new Node(0);
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);

        node0.neighbors = Arrays.asList(node1, node3);
        node1.neighbors = Arrays.asList(node0, node2);
        node2.neighbors = Arrays.asList(node1, node3);
        node3.neighbors = Arrays.asList(node2, node0);

        List<List<Node>> paths = allPathsDfs(node0, node3);
        for (List<Node> path : paths) {
            System.out.println(path);
        }
        // Expected Output: [[0, 3], [0, 1, 2, 3]]
    }

}

// Time Complexity: O(2^V) in the worst case, where V is the number of vertices
// Space Complexity: O(V) for the recursion stack and path storage