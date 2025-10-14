package matrixes.islands.q1254;

public class NumberOfClosedIslands {
    private static int closedIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int closedIslands = 0;

        // Mark all `0`s connected to the boundary as `1`s
        for (int i = 0; i < m; i++) {
            if (grid[i][0] == 0)
                dfs(grid, i, 0);
            if (grid[i][n - 1] == 0)
                dfs(grid, i, n - 1);
        }
        for (int j = 0; j < n; j++) {
            if (grid[0][j] == 0)
                dfs(grid, 0, j);
            if (grid[m - 1][j] == 0)
                dfs(grid, m - 1, j);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    dfs(grid, i, j);
                    closedIslands++;
                }
            }
        }

        return closedIslands;
    }

    private static void dfs(int[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 1) {
            return;
        }

        grid[i][j] = 1;
        dfs(grid, i + 1, j);
        dfs(grid, i - 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i, j - 1);
    }

    public static void main(String[] args) {
        int[][] grid = {
                { 1, 1, 1, 1, 0, 1 },
                { 1, 0, 1, 0, 1, 1 },
                { 1, 0, 1, 0, 0, 1 },
                { 1, 1, 1, 1, 1, 1 }
        };

        int result = closedIsland(grid);
        System.out.println("Number of Closed Islands: " + result); // Output: 1
    }
}

// Complexity Analysis
// Time Complexity: O(M * N), where M is the number of rows and N is the number of columns in the grid.
// We visit each cell once. In the worst case, we might visit all cells in the grid.
// Space Complexity: O(M * N) in the worst case, where the grid is filled with lands ('0's).
// This space is used by the recursion stack during the DFS traversal.