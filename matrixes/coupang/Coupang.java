package matrixes.coupang;

import java.util.*;

public class Coupang {

    private static final Map<String, int[]> DIRECTIONS = new HashMap<String, int[]>() {
        {
            put("R", new int[] { 0, 1 });
            put("L", new int[] { 0, -1 });
            put("U", new int[] { -1, 0 });
            put("D", new int[] { 1, 0 });
        }
    };

    private static boolean hasPath(String[][] matrix, int[] entryPoint, int[] exitPoint) {
        boolean[] pathExists = new boolean[1];
        dfs(matrix, entryPoint[0], entryPoint[1], pathExists, exitPoint);
        return pathExists[0];
    }

    private static void dfs(String[][] matrix, int row, int col, boolean[] pathExists, int[] exitPoint) {
        if (row < 0 || row >= matrix.length || col < 0 || col >= matrix[0].length || matrix[row][col].equals(".")) {
            return;
        }

        if (row == exitPoint[0] && col == exitPoint[1]) {
            pathExists[0] = true;
            return;
        }

        int[] direction = DIRECTIONS.get(matrix[row][col]);
        matrix[row][col] = "."; // mark visited

        int newRow = row + direction[0];
        int newCol = col + direction[1];
        dfs(matrix, newRow, newCol, pathExists, exitPoint);
    }

    public static void main(String[] args) {
        String[][] matrix = {
                { "R", "D", "U", "U" },
                { "R", "R", "R", "U" },
                { "R", "D", "U", "U" }
        };

        int[] entryPoint = { 0, 0 };
        int[] exitPoint = { 1, 3 };

        System.out.println(hasPath(matrix, entryPoint, exitPoint)); // Output: true
    }
}

// Complexity Analysis
// Time Complexity: O(N), where N is the number of cells in the matrix. In the worst case, we may need to visit all cells.
// Space Complexity: O(N) for the recursion stack in the worst case.