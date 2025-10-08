package hamiltonianCycle;

import java.util.*;

public class HamiltonianCycle {
    static class Node {
        int val;
        List<Node> neighbors = new ArrayList<>();

        Node(int val) {
            this.val = val;
        }
    }

    private final List<Node> nodes;
    private final Set<Integer> visited = new HashSet<>();
    private final List<Node> path = new ArrayList<>();
    private Node startNode;

    public HamiltonianCycle(List<Node> nodes) {
        this.nodes = nodes;
    }

    public boolean findHamiltonianCycle() {
        if (nodes.isEmpty())
            return false;

        startNode = nodes.get(0);
        path.add(startNode);
        visited.add(startNode.val);

        return dfs(startNode);
    }

    private boolean dfs(Node current) {
        if (path.size() == nodes.size()) {
            // Check if last node connects back to start
            return current.neighbors.contains(startNode);
        }

        for (Node neighbor : current.neighbors) {
            if (visited.contains(neighbor.val))
                continue;

            visited.add(neighbor.val);
            path.add(neighbor);

            if (dfs(neighbor))
                return true;

            visited.remove(neighbor.val);
            path.remove(path.size() - 1);
        }

        return false;
    }

    public void printCycle() {
        if (findHamiltonianCycle()) {
            System.out.print("Hamiltonian Cycle: ");
            for (Node n : path) {
                System.out.print(n.val + " ");
            }
            System.out.println(path.get(0).val); // To complete the cycle
        } else {
            System.out.println("No Hamiltonian Cycle found");
        }
    }

    public static void main(String[] args) {
        Node n0 = new Node(0);
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);

        n0.neighbors.addAll(Arrays.asList(n1, n2, n3));
        n1.neighbors.addAll(Arrays.asList(n0, n2, n3));
        n2.neighbors.addAll(Arrays.asList(n0, n1, n3));
        n3.neighbors.addAll(Arrays.asList(n0, n1, n2));

        List<Node> graph = Arrays.asList(n0, n1, n2, n3);

        HamiltonianCycle solver = new HamiltonianCycle(graph);
        solver.printCycle();
        // Hamiltonian Cycle: 0 1 2 3 0
    }
}

/*
 * Time Complexity: O(N!), where N is the number of nodes. In the worst case, we might explore all permutations of nodes.
 * Space Complexity: O(N) for the recursion stack and path storage.
 */