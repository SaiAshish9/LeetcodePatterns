package matrixes.q48;

public class RotateImage {

    public void rotate(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return;
        
        int n = matrix.length;
        transpose(matrix, n);
        
        for (int i = 0; i < n; i++) {
            reverseRow(matrix[i], n);
        }
    }
    
    private void transpose(int[][] matrix, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                swap(matrix, i, j, j, i);
            }
        }
    }
    
    private void reverseRow(int[] row, int n) {
        int left = 0, right = n - 1;
        while (left < right) {
            int temp = row[left];
            row[left] = row[right];
            row[right] = temp;
            left++;
            right--;
        }
    }
    
    private void swap(int[][] matrix, int i, int j, int x, int y) {
        int temp = matrix[i][j];
        matrix[i][j] = matrix[x][y];
        matrix[x][y] = temp;
    }

    public static void main(String[] args) {
        RotateImage ri = new RotateImage();
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        
        ri.rotate(matrix);
        
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}

// Output:
// 7 4 1
// 8 5 2
// 9 6 3    
// Complexity Analysis
// Time Complexity: O(n^2), where n is the number of rows (or columns) in the matrix. We traverse each element of the matrix once to transpose it and then again to reverse each row.
// Space Complexity: O(1), since we are modifying the matrix in place and not using any additional data structures that scale with input size.
