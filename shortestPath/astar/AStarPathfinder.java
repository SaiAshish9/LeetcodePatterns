package shortestPath.astar;

import java.util.*;

class Node {
    int row, col;
    int costFromStart; // g(n)
    int estimatedToGoal; // h(n)
    int totalEstimatedCost; // f(n) = g + h
    Node parent;

    Node(int row, int col, int costFromStart, int estimatedToGoal, Node parent) {
        this.row = row;
        this.col = col;
        this.costFromStart = costFromStart;
        this.estimatedToGoal = estimatedToGoal;
        this.totalEstimatedCost = costFromStart + estimatedToGoal;
        this.parent = parent;
    }
}

public class AStarPathfinder {
    // Directions: right, down, left, up
    static final int[][] DIRECTIONS = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

    public static List<int[]> findPath(int[][] grid, int[] start, int[] goal) {
        int rows = grid.length, cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];

        PriorityQueue<Node> openSet = new PriorityQueue<>(Comparator.comparingInt(node -> node.totalEstimatedCost));
        Node startNode = new Node(start[0], start[1], 0, calculateHeuristic(start, goal), null);
        openSet.offer(startNode);

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();

            if (current.row == goal[0] && current.col == goal[1]) {
                return reconstructPath(current);
            }

            if (visited[current.row][current.col])
                continue;
            visited[current.row][current.col] = true;

            for (int[] direction : DIRECTIONS) {
                int newRow = current.row + direction[0];
                int newCol = current.col + direction[1];

                if (isValidCell(newRow, newCol, grid, visited)) {
                    int newCostFromStart = current.costFromStart + 1;
                    int estimatedToGoal = calculateHeuristic(new int[] { newRow, newCol }, goal);
                    Node neighbor = new Node(newRow, newCol, newCostFromStart, estimatedToGoal, current);
                    openSet.offer(neighbor);
                }
            }
        }

        return Collections.emptyList(); // No path found
    }

    private static boolean isValidCell(int row, int col, int[][] grid, boolean[][] visited) {
        return row >= 0 && col >= 0 && row < grid.length && col < grid[0].length
                && grid[row][col] == 0 && !visited[row][col];
    }

    private static int calculateHeuristic(int[] from, int[] to) {
        // Manhattan distance heuristic
        return Math.abs(from[0] - to[0]) + Math.abs(from[1] - to[1]);
    }

    private static List<int[]> reconstructPath(Node node) {
        List<int[]> path = new ArrayList<>();
        while (node != null) {
            path.add(new int[] { node.row, node.col });
            node = node.parent;
        }
        Collections.reverse(path);
        return path;
    }

    public static void main(String[] args) {
        int[][] grid = {
                { 0, 0, 0, 0 },
                { 1, 1, 0, 1 },
                { 0, 0, 0, 0 },
                { 0, 1, 1, 0 },
                { 0, 0, 0, 0 }
        };

        int[] start = { 0, 0 };
        int[] goal = { 4, 3 };

        List<int[]> shortestPath = findPath(grid, start, goal);
        for (int[] cell : shortestPath) {
            System.out.println(Arrays.toString(cell));
        }
    }
}

/*
 * Time Complexity: O(E), where E is the number of edges in the graph. In the worst case, we may need to explore all edges.
 * Space Complexity: O(V), where V is the number of vertices in the graph. We may need to store all vertices in the priority queue and visited set.
 */
