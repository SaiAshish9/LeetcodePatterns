package backtracking.q980;

public class UniquePaths3 {
    private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private static int emptyCells = 0;
    private static int uniquePaths = 0;

    private static int uniquePathsIII(int[][] grid) {
        int startRow = 0, startCol = 0;
        
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == 1) {  
                    startRow = r;
                    startCol = c;
                }
                if (grid[r][c] != -1) {  
                    emptyCells++;
                }
            }
        }
        
        dfs(grid, startRow, startCol, 1);  
        return uniquePaths;
    }

    private static void dfs(int[][] grid, int row, int col, int visitedCells) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || grid[row][col] == -1) {
            return;
        }
        
        if (grid[row][col] == 2) {
            if (visitedCells == emptyCells) {
                uniquePaths++;
            }
            return;
        }
        
        int temp = grid[row][col];
        grid[row][col] = -1;  

        for (int[] direction : DIRECTIONS) {
            int newRow = row + direction[0];
            int newCol = col + direction[1];
            dfs(grid, newRow, newCol, visitedCells + 1);
        }
        
        grid[row][col] = temp;
    }

    public static void main(String[] args) {
        int[][] grid = {
            {1, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 2, -1}
        };
        System.out.println(uniquePathsIII(grid)); // Output: 2
    }
}

// Complexity Analysis
// Time Complexity: O(4^(m*n)) in the worst case, where m and n are the dimensions of the grid. This is because from each cell, we can move in 4 directions, leading to an exponential number of paths.
// Space Complexity: O(m*n) for the recursion stack in the worst case when the path covers all cells in the grid.  The grid itself is modified in place, so no additional space is used for it.