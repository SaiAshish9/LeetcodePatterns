package matrixes.islands.q1905;

public class CountSubIslands {
    private static int countSubIslands(int[][] grid1, int[][] grid2) {
        int m = grid1.length;
        int n = grid1[0].length;
        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid2[i][j] == 1) {
                    if (isSubIsland(grid1, grid2, i, j)) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    private static boolean isSubIsland(int[][] grid1, int[][] grid2, int i, int j) {
        boolean isSubIsland = true;
        isSubIsland = dfs(grid1, grid2, i, j, isSubIsland);
        return isSubIsland;
    }

    private static boolean dfs(int[][] grid1, int[][] grid2, int i, int j, boolean isSubIsland) {
        if (i < 0 || i >= grid1.length || j < 0 || j >= grid1[0].length || grid2[i][j] == 0) {
            return isSubIsland;
        }
        if (grid1[i][j] != 1) {
            isSubIsland = false;
        }
        grid2[i][j] = 0;
        isSubIsland = dfs(grid1, grid2, i + 1, j, isSubIsland);
        isSubIsland = dfs(grid1, grid2, i - 1, j, isSubIsland);
        isSubIsland = dfs(grid1, grid2, i, j + 1, isSubIsland);
        isSubIsland = dfs(grid1, grid2, i, j - 1, isSubIsland);
        return isSubIsland;
    }

    public static void main(String[] args) {
        int[][] grid1 = {
                { 1, 1, 0, 0, 0 },
                { 1, 1, 0, 0, 0 },
                { 0, 0, 1, 0, 0 },
                { 0, 0, 0, 1, 1 }
        };
        int[][] grid2 = {
                { 1, 1, 0, 0, 0 },
                { 0, 1, 0, 0, 0 },
                { 0, 0, 1, 0, 0 },
                { 0, 0, 0, 1, 0 }
        };

        int result = countSubIslands(grid1, grid2);
        System.out.println("Number of Sub-Islands: " + result); // Output will depend on grid1 and grid2
    }
}

// Complexity Analysis
// Time Complexity: O(M * N), where M is the number of rows and N is the number of columns in the grids.
// We visit each cell once. In the worst case, we might visit all cells in both grids.
// Space Complexity: O(M * N) in the worst case, where the grids are filled with lands ('1's).
// This space is used by the recursion stack during the DFS traversal.