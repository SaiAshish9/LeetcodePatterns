package matrixes.q885;

import java.util.*;

public class SpiralMatrix3 {
    private static final int[][] DIRECTIONS = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

    private static int[][] spiralMatrixIII(int R, int C, int rStart, int cStart) {
        int[][] result = new int[R * C][2];
        int index = 0;
        result[index++] = new int[] { rStart, cStart };
        int steps = 1;
        int r = rStart, c = cStart;

        while (index < R * C) {
            // Loop over directions (right -> down -> left -> up)
            for (int[] direction : DIRECTIONS) {
                for (int i = 0; i < steps; i++) {
                    // Move in the current direction
                    r += direction[0];
                    c += direction[1];

                    // If the new cell is within bounds, add it to the result
                    if (r >= 0 && r < R && c >= 0 && c < C) {
                        result[index++] = new int[] { r, c };
                    }
                }
                // After moving right or left, increase the number of steps
                if (direction[0] != 0) {
                    steps++;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int R = 5, C = 6, rStart = 1, cStart = 4;
        int[][] result = spiralMatrixIII(R, C, rStart, cStart);

        for (int[] cell : result) {
            System.out.println("[" + cell[0] + ", " + cell[1] + "]");
        }
        // Expected Output:
        // [[1,4],[1,5],[2,5],[2,4],[2,3],[1,3],[0,3],[0,4],[0,5],[3,5],
        // [3,4],[3,3],[3,2],[2,2],[1,2],[0,2],[4,5],[4,4],
        // [4,3],[4,2],[4,1],[3,1],[2,1],[1,1],[0,1],[4,0],[3,0],[2,0],[1,0],[0,0]]

    }
}
