package matrixes.CountSubMatrixesWithAll1;

public class CountSubmatrices {

    private static int numSubmat(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[][] rowOnes = new int[m][n];
        int count = 0;

        // Step 1: Build row-wise consecutive 1s
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 1) {
                    rowOnes[i][j] = (j == 0) ? 1 : rowOnes[i][j - 1] + 1;
                }
            }
        }

        // Step 2: For each cell, go upwards and find minimum width
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) {
                if (mat[i][j] == 1) {
                    int minWidth = rowOnes[i][j];
                    for (int k = i; k >= 0 && rowOnes[k][j] > 0; k--) {
                        minWidth = Math.min(minWidth, rowOnes[k][j]);
                        count += minWidth;
                    }
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        // Example test case
        int[][] mat = {
            {1, 0, 1},
            {1, 1, 0},
            {1, 1, 0}
        };

        System.out.println("Number of submatrices with all 1s: " + numSubmat(mat));

        // Another test
        int[][] mat2 = {
            {1, 1, 1},
            {1, 1, 1}
        };

        System.out.println("Number of submatrices with all 1s: " + numSubmat(mat2));
    }
}

// Number of submatrices with all 1s: 13
// Number of submatrices with all 1s: 18

// Complexity Analysis:
// Time Complexity: O(m * n * min(m, n)) in the worst case, where m is the number of rows and n is the number of columns.
// Space Complexity: O(m * n) for the rowOnes array.
