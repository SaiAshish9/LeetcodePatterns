package matrixes.RotateCellAntiClockwiseBy1;

import java.util.Arrays;

public class RotateCellAntiClockwiseBy1 {

    private static void rotateByOneCellAnticlockwise(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return;

        int m = matrix.length;
        int n = matrix[0].length;

        // Temporary array to store the outer ring values
        int[] temp = new int[2 * (m + n - 2)];

        int index = 0;

        // Top row (left to right)
        for (int j = 0; j < n; j++) {
            temp[index++] = matrix[0][j];
        }
        // Right column (top to bottom)
        for (int i = 1; i < m; i++) {
            temp[index++] = matrix[i][n - 1];
        }
        // Bottom row (right to left)
        for (int j = n - 2; j >= 0; j--) {
            temp[index++] = matrix[m - 1][j];
        }
        // Left column (bottom to top)
        for (int i = m - 2; i > 0; i--) {
            temp[index++] = matrix[i][0];
        }

        // Store the first value to rotate anticlockwise
        int firstValue = temp[0];
        System.arraycopy(temp, 1, temp, 0, temp.length - 1);
        temp[temp.length - 1] = firstValue;

        index = 0;

        // Top row (left to right)
        for (int j = 0; j < n; j++) {
            matrix[0][j] = temp[index++];
        }
        // Right column (top to bottom)
        for (int i = 1; i < m; i++) {
            matrix[i][n - 1] = temp[index++];
        }
        // Bottom row (right to left)
        for (int j = n - 2; j >= 0; j--) {
            matrix[m - 1][j] = temp[index++];
        }
        // Left column (bottom to top)
        for (int i = m - 2; i > 0; i--) {
            matrix[i][0] = temp[index++];
        }

        System.out.println("\nRotated Matrix by one cell anticlockwise:");
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {
                { 1, 2, 3, 10 },
                { 4, 5, 6, 10 },
                { 7, 8, 9, 11 }
        };

        System.out.println("Original Matrix:");
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }

        rotateByOneCellAnticlockwise(matrix);
        // Expected Output:
        // [2, 3, 10, 10]
        // [1, 5, 6, 11]
        // [4, 7, 8, 9]
    }
}

// Time Complexity: O(m + n) where m is number of rows and n is number of columns
// Space Complexity: O(m + n) for the temporary array used to store the border elements