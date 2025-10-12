package backtracking.q51;

import java.util.*;

public class NQueens {

    private static boolean isSafe(char[][] board, int row, int col, int n) {
        for (int i = 0; i < n; i++) {
            if (board[row][i] == 'Q')
                return false;
        }

        for (int i = 0; i < n; i++) {
            if (board[i][col] == 'Q')
                return false;
        }

        // UL
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q')
                return false;
        }

        // UR
        for (int i = row, j = col; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 'Q')
                return false;
        }

        return true;
    }

    private static void dfs(char[][] board, int row, int n, int[] count) {
        if (row == n) {
            count[0]++;
            return;
        }

        for (int col = 0; col < n; col++) {
            if (isSafe(board, row, col, n)) {
                board[row][col] = 'Q';
                dfs(board, row + 1, n, count);
                board[row][col] = '.';
            }
        }
    }

    public static void main(String... s) {
        int n = 4;
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }

        int[] count = new int[] { 0 };
        dfs(board, 0, n, count);
        System.out.println(count[0]);
    }
}

// Complexity Analysis
// Time Complexity: O(N!), where N is the number of queens. This is because, in the
// worst case, we have to explore all the configurations of the board. However,
// the actual time complexity is much less due to the pruning of invalid
// configurations using the isSafe function.
// Space Complexity: O(N^2) for the board and O(N) for the recursion stack, leading to a total
// space complexity of O(N^2).