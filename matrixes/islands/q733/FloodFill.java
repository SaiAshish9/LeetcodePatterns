package matrixes.islands.q733;

public class FloodFill {
    // Directions: right, down, left, up
    private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    
    private static int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int originalColor = image[sr][sc];
        
        // If the original color is the same as the new color, no need to proceed
        if (originalColor != newColor) {
            dfs(image, sr, sc, newColor, originalColor);
        }
        
        return image;
    }
    
    private static void dfs(int[][] image, int i, int j, int newColor, int originalColor) {
        if (i < 0 || i >= image.length || j < 0 || j >= image[0].length || image[i][j] != originalColor) {
            return;
        }
        
        image[i][j] = newColor;

        for (int[] direction : DIRECTIONS) {
            int newRow = i + direction[0];
            int newCol = j + direction[1];
            dfs(image, newRow, newCol, newColor, originalColor);
        }
    }

    public static void main(String[] args) {
        int[][] image = {
            {1, 1, 1},
            {1, 1, 0},
            {1, 0, 1}
        };
        int sr = 1, sc = 1, newColor = 2;
        
        int[][] result = floodFill(image, sr, sc, newColor);
        
        for (int[] row : result) {
            for (int pixel : row) {
                System.out.print(pixel + " ");
            }
            System.out.println();
        }
    }
}

// Output:
// 2 2 2
// 2 2 0
// 2 0 1

// Complexity Analysis
// Time Complexity: O(N), where N is the number of pixels in the image. In the worst case, we might need to visit every pixel.
// Space Complexity: O(N) in the worst case due to the recursion stack. In the best case (when the area to be filled is small), the space complexity can be O(1).