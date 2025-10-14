package matrixes.q59;

public class SpiralMatrix2 {

    private static final int[][] DIRECTIONS = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

    private static int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        boolean[][] visited = new boolean[n][n];
        int row = 0, col = 0, dir = 0;
        for (int i = 1; i <= n * n; i++) {
            matrix[row][col] = i;
            visited[row][col] = true;

            int nextRow = row + DIRECTIONS[dir][0];
            int nextCol = col + DIRECTIONS[dir][1];

            if (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= n || visited[nextRow][nextCol]) {
                dir = (dir + 1) % 4;
                nextRow = row + DIRECTIONS[dir][0];
                nextCol = col + DIRECTIONS[dir][1];
            }

            row = nextRow;
            col = nextCol;
        }
        return matrix;
    }

    public static void main(String[] args) {
        int n = 3;
        int[][] result = generateMatrix(n);
        System.out.println("Generated Spiral Matrix:");
        for (int[] row : result) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }

}

// Generated Spiral Matrix:
// 1 2 3
// 8 9 4    
// 7 6 5

// Time Complexity: O(n^2) where n is the size of the matrix (n x n).
// Space Complexity: O(n^2) for the matrix and the visited array.   
