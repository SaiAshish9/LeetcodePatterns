package matrixes.q240;


public class SearchA2DMatrix2 {
    private static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        
        int rows = matrix.length;
        int cols = matrix[0].length;
        
        int row = 0;
        int col = cols - 1;
        
        while (row < rows && col >= 0) {
            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] > target) {
                col--;  // Moving left (reduce column)
            } else {
                row++;  // Moving down (increase row)
            }
        }
        
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {1, 4, 7, 11, 15},
            {2, 5, 8, 12, 19},
            {3, 6, 9, 16, 22},
            {10, 13, 14, 17, 24},
            {18, 21, 23, 26, 30}
        };
        int target = 5;
        System.out.println(searchMatrix(matrix, target)); // Output: true

        target = 20;
        System.out.println(searchMatrix(matrix, target)); // Output: false
    }
}

// Time Complexity: O(m + n), where m is the number of rows and n is the number of columns in the matrix.
// Space Complexity: O(1) as we are using only a constant amount of extra space