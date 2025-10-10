package backtracking.q37;

import java.util.*;
public class SudokuSolver {

    private static boolean isSafe(int[][] board, int row, int col, int val) {
        int n = board.length;

        for (int i = 0; i < n; i++) {
            if (board[row][i] == val || board[i][col] == val) {
                return false;
            }
        }

        int rowStart = row - row % 3;
        int colStart = col - col % 3;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[rowStart + i][colStart + j] == val) {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean solve(int[][] board, int row, int col) {
        int n = board.length;

        if (row == n) {
            return true;
        }

        if (col == n) {
            return solve(board, row + 1, 0);
        }

        if (board[row][col] != 0) {
            return solve(board, row, col + 1);
        }

        for (int val = 1; val <= 9; val++) {
            if (isSafe(board, row, col, val)) {
                board[row][col] = val;

                if (solve(board, row, col + 1)) {
                    return true;
                }

                board[row][col] = 0;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        String[][] grid = {
                { "5", "3", ".", ".", "7", ".", ".", ".", "." },
                { "6", ".", ".", "1", "9", "5", ".", ".", "." },
                { ".", "9", "8", ".", ".", ".", ".", "6", "." },
                { "8", ".", ".", ".", "6", ".", ".", ".", "3" },
                { "4", ".", ".", "8", ".", "3", ".", ".", "1" },
                { "7", ".", ".", ".", "2", ".", ".", ".", "6" },
                { ".", "6", ".", ".", ".", ".", "2", "8", "." },
                { ".", ".", ".", "4", "1", "9", ".", ".", "5" },
                { ".", ".", ".", ".", "8", ".", ".", "7", "9" }
        };

        int[][] board = new int[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = grid[i][j].equals(".") ? 0 : Integer.parseInt(grid[i][j]);
            }
        }

        if (solve(board, 0, 0)) {
            System.out.println("Solved Sudoku:");
            for (int[] row : board) {
                System.out.println(Arrays.toString(row));
            }
        } else {
            System.out.println("No solution exists.");
        }
    }
}

// Complexity Analysis:
// Time Complexity: O(9^(m)), where m is the number of empty cells.
// In the worst case, we might have to try all 9 digits for each empty cell.
// Space Complexity: O(1), since the board size is fixed (9x9).
// The recursion stack can go as deep as the number of empty cells, but since
// the board
// size is constant, we consider it O(1).
// The board itself is modified in place, so no additional space is used for it.