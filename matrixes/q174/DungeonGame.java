package matrixes.q174;

public class DungeonGame {

    private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}};
    
    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;
        Integer[][] memo = new Integer[m][n]; 
        return dfs(dungeon, 0, 0, m, n, memo);
    }
    
    private int dfs(int[][] dungeon, int i, int j, int m, int n, Integer[][] memo) {
        if (i == m - 1 && j == n - 1) {
            return Math.max(1, 1 - dungeon[i][j]);
        }
        
        if (memo[i][j] != null) {
            return memo[i][j];
        }
        
        int minHealth = Integer.MAX_VALUE;
        
        for (int[] dir : DIRECTIONS) {
            int ni = i + dir[0];
            int nj = j + dir[1];
            
            if (ni < m && nj < n) {
                minHealth = Math.min(minHealth, dfs(dungeon, ni, nj, m, n, memo));
            }
        }
        
        int healthRequired = Math.max(1, minHealth - dungeon[i][j]);        
        memo[i][j] = healthRequired;
        
        return healthRequired;
    }

    public static void main(String[] args) {
        DungeonGame game = new DungeonGame();
        int[][] dungeon = {
            {-2, -3, 3},
            {-5, -10, 1},
            {10, 30, -5}
        };
        int result = game.calculateMinimumHP(dungeon);
        System.out.println("Minimum initial health required: " + result); // Expected output: 7
    }
}

// Time Complexity: O(M * N), where M is the number of rows and N is the number of columns in the dungeon.
// Space Complexity: O(M * N) for the memoization table and recursion stack.
