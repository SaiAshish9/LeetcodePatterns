package matrixes.islands.q733;

public class FloodFill {
    // Directions: right, down, left, up
    private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int originalColor = image[sr][sc];
        
        // If the original color is the same as the new color, no need to proceed
        if (originalColor != newColor) {
            dfs(image, sr, sc, newColor, originalColor);
        }
        
        return image;
    }
    
    private void dfs(int[][] image, int i, int j, int newColor, int originalColor) {
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
}
