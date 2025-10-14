package backtracking.q2664;

import java.util.*;

public class TheKnightsTour {

    private static int[][] board;
    private static int rows, cols;
    private static boolean foundSolution = false;
    private static final int[][] knightMoves = {
            { -2, -1 }, { -1, -2 }, { 1, -2 }, { 2, -1 },
            { 2, 1 }, { 1, 2 }, { -1, 2 }, { -2, 1 }
    };

    private static List<List<Integer>> tourOfKnight(int rows, int cols, int startRow, int startCol) {
        TheKnightsTour.rows = rows;
        TheKnightsTour.cols = cols;
        board = new int[rows][cols];
        for (int[] row : board) {
            Arrays.fill(row, -1);
        }

        board[startRow][startCol] = 0;
        dfs(startRow, startCol, 0);

        List<List<Integer>> result = new ArrayList<>();
        for (int[] row : board) {
            List<Integer> list = new ArrayList<>();
            for (int val : row) {
                list.add(val);
            }
            result.add(list);
        }
        return result;
    }

    private static void dfs(int row, int col, int moveCount) {
        if (moveCount == rows * cols - 1) {
            foundSolution = true;
            return;
        }

        for (int[] move : knightMoves) {
            int newRow = row + move[0];
            int newCol = col + move[1];
            if (isSafe(newRow, newCol)) {
                board[newRow][newCol] = moveCount + 1;
                dfs(newRow, newCol, moveCount + 1);
                if (foundSolution)
                    return;
                board[newRow][newCol] = -1; // backtrack
            }
        }
    }

    private static boolean isSafe(int r, int c) {
        return r >= 0 && r < rows && c >= 0 && c < cols && board[r][c] == -1;
    }

    public static void main(String[] args) {
        List<List<Integer>> res = tourOfKnight(5, 5, 0, 0);
        for (List<Integer> row : res) {
            System.out.println(row);
        }
    }
}

/*
[0, 9, 14, 19, 22]
[15, 4, 21, 8, 13]
[10, 1, 18, 23, 20]
[5, 16, 3, 12, 7]
[2, 11, 6, 17, 24]
*/

// Complexity Analysis
// Time Complexity: O(8^(N*M)), where N is the number of rows and M is the number of columns. In the worst case, we may explore all possible moves for each cell.
// Space Complexity: O(N*M) for the board and the recursion stack.