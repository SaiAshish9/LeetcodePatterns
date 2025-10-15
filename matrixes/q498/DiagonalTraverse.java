package matrixes.q498;

public class DiagonalTraverse {
    private static final int[][] DIAGONAL_DIRECTIONS = {{-1, 1}, {1, -1}};
    
    private static int[] findDiagonalOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new int[0]; 
        }
        
        int m = matrix.length;   
        int n = matrix[0].length;
        int[] result = new int[m * n]; 
        
        int r = 0, c = 0;
        int direction = 0;
        
        for (int i = 0; i < m * n; i++) {
            result[i] = matrix[r][c]; 
            int newR = r + DIAGONAL_DIRECTIONS[direction][0];
            int newC = c + DIAGONAL_DIRECTIONS[direction][1];
            
            if (newR < 0 || newR >= m || newC < 0 || newC >= n) {
                if (direction == 0) { 
                    if (c == n - 1) {
                        r++;
                    } else {
                        c++; 
                    }
                } else { 
                    if (r == m - 1) {
                        c++;
                    } else {
                        r++;
                    }
                }
                direction = 1 - direction;
            } else {
                r = newR;
                c = newC;
            }
        }
        
        return result;
    }
    public static void main(String[] args) {
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        
        int[] result = findDiagonalOrder(matrix);
        for (int num : result) {
            System.out.print(num + " ");
        }      
        // Output: 1 2 4 7 5 3 6 8 9
    }
}

// Time Complexity: O(m * n) where m is the number of rows and n is the number of columns in the matrix.
// Space Complexity: O(1) if we don't count the output array, otherwise O(m