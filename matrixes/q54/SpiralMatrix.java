package matrixes.q54;

import java.util.*;

public class SpiralMatrix {
    private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    private static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0) return result;

        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean[][] visited = new boolean[rows][cols];

        dfs(matrix, visited, result, 0, 0, 0);
        return result;
    }

    private static void dfs(int[][] matrix, boolean[][] visited, List<Integer> result, int row, int col, int direction) {
        if (row < 0 || row >= matrix.length || col < 0 || col >= matrix[0].length || visited[row][col]) {
            return;
        }

        visited[row][col] = true;
        result.add(matrix[row][col]);

        int nextRow = row + DIRECTIONS[direction][0];
        int nextCol = col + DIRECTIONS[direction][1];
        if (nextRow < 0 || nextRow >= matrix.length || nextCol < 0 || nextCol >= matrix[0].length || visited[nextRow][nextCol]) {
            direction = (direction + 1) % 4;
            nextRow = row + DIRECTIONS[direction][0];
            nextCol = col + DIRECTIONS[direction][1];
        }

        dfs(matrix, visited, result, nextRow, nextCol, direction);
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        };

        List<Integer> result = spiralOrder(matrix);
        System.out.println("Spiral Order: " + result);
    }
}
