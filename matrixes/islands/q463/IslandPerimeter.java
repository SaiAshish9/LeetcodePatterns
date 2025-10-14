package matrixes.islands.q463;

public class IslandPerimeter {
    private static final int[][] DIRECTIONS = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

    private static int islandPerimeter(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int perimeter = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    for (int[] direction : DIRECTIONS) {
                        int x = i + direction[0];
                        int y = j + direction[1];

                        if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] == 0) {
                            perimeter++;
                        }
                    }
                }
            }
        }

        return perimeter;
    }

    public static void main(String[] args) {
        int[][] grid = {
                { 0, 1, 0, 0 },
                { 1, 1, 1, 0 },
                { 1, 0, 0, 0 },
                { 0, 1, 0, 0 }
        };

        int result = islandPerimeter(grid);
        System.out.println("Island Perimeter: " + result); // Output: 12
    }
}

// Complexity Analysis
// Time Complexity: O(M * N), where M is the number of rows and N is the number of columns in the grid.
// We visit each cell once. In the worst case, we might visit all cells in the grid.
// Space Complexity: O(1) as we are using only a constant amount of extra space.
