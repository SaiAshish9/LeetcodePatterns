package matrixes.islands.q695;


public class MaxAreaOfIsland {
    private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    private static int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;
        int maxArea = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int area = dfs(grid, i, j);
                    maxArea = Math.max(maxArea, area);
                }
            }
        }

        return maxArea;
    }

    private static int dfs(int[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0) {
            return 0;
        }

        grid[i][j] = 0;
        int area = 1;

        for (int[] direction : DIRECTIONS) {
            int newI = i + direction[0];
            int newJ = j + direction[1];
            area += dfs(grid, newI, newJ);
        }

        return area;
    }

    public static void main(String[] args) {
        int[][] grid = {
            {0, 1, 0, 0},
            {1, 1, 1, 0},
            {0, 1, 0, 0},
            {0, 0, 1, 1}
        };

        int result = maxAreaOfIsland(grid);
        System.out.println("Max Area of Island: " + result); // Output: 6
    }
}

// Complexity Analysis
// Time Complexity: O(M * N), where M is the number of rows and N is the number of columns in the grid.
// We visit each cell once. In the worst case, we might visit all cells in the grid.
// Space Complexity: O(M * N) in the worst case, where the grid is filled with lands ('1's).
// This space is used by the recursion stack during the DFS traversal.