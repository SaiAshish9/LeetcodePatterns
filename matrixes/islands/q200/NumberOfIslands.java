package matrixes.islands.q200;

public class NumberOfIslands {

    private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    private static int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;
        int numIslands = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    numIslands++;
                    dfs(grid, i, j);
                }
            }
        }

        return numIslands;
    }

    private static void dfs(char[][] grid, int i, int j) {
        int m = grid.length;
        int n = grid[0].length;

        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] != '1') {
            return;
        }
        grid[i][j] = '0';
        for (int[] direction : DIRECTIONS) {
            int newRow = i + direction[0];
            int newCol = j + direction[1];
            dfs(grid, newRow, newCol);
        }
    }

    public static void main(String[] args) {
        char[][] grid = {
            {'1', '1', '1', '1', '0'},
            {'1', '1', '0', '1', '0'},
            {'1', '1', '0', '0', '0'},
            {'0', '0', '0', '0', '0'}
        };

        int result = numIslands(grid);
        System.out.println("Number of Islands: " + result); // Output: 1
    }
}

// Complexity Analysis
// Time Complexity: O(M * N), where M is the number of rows and N is the number of columns in the grid.
// We visit each cell once. In the worst case, we might visit all cells in the grid.        
// Space Complexity: O(M * N) in the worst case, where the grid is filled with lands ('1's).
// This space is used by the recursion stack during the DFS traversal.