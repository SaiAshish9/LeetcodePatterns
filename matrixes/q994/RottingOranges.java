package matrixes.q994;

import java.util.*;

public class RottingOranges {

    private static final int[][] DIRECTIONS = {
            { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 }
    };

    public static int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int freshOranges = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[] { i, j });
                } else if (grid[i][j] == 1) {
                    freshOranges++;
                }
            }
        }

        if (freshOranges == 0)
            return 0;

        int minutes = -1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            minutes++;
            for (int k = 0; k < size; k++) {
                int[] cell = queue.poll();
                int row = cell[0];
                int col = cell[1];

                for (int[] dir : DIRECTIONS) {
                    int newRow = row + dir[0];
                    int newCol = col + dir[1];

                    if (isValid(newRow, newCol, m, n, grid)) {
                        grid[newRow][newCol] = 2;
                        freshOranges--;
                        queue.offer(new int[] { newRow, newCol });
                    }
                }
            }
        }

        return freshOranges == 0 ? minutes : -1;
    }

    private static boolean isValid(int i, int j, int m, int n, int[][] grid) {
        return i >= 0 && i < m && j >= 0 && j < n && grid[i][j] == 1;
    }

    public static void main(String[] args) {
        int[][] grid = {
                { 2, 1, 1 },
                { 1, 1, 0 },
                { 0, 1, 1 }
        };
        System.out.println(orangesRotting(grid)); // Output: 4
    }
}

// Complexity Analysis
// Time Complexity: O(M * N), where M is the number of rows and N is the number of columns in the grid.
// We visit each cell once. In the worst case, we might visit all cells in the grid.
// Space Complexity: O(M * N) in the worst case, where the grid is filled with fresh oranges ('1's).
// This space is used by the queue during the BFS traversal.