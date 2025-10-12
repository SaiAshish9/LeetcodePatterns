package backtracking.q51;

import java.util.*;

public class NQueens {

    private static boolean isSafe(char[][] board, int row, int col, int n) {
        for (int i = 0; i < n; i++) {
            if (board[row][i] == 'Q') return false;
        }

        for (int i = 0; i < n; i++) {
            if (board[i][col] == 'Q') return false;
        }

        // UL
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') return false;
        }

        // UR
        for (int i = row, j = col; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 'Q') return false;
        }

        return true;
    }

    private static void dfs(char[][] board, int row, int n, List<List<String>> res) {
        if (row == n) {
            res.add(construct(board, n));
            return;
        }

        for (int col = 0; col < n; col++) {
            if (isSafe(board, row, col, n)) {
                board[row][col] = 'Q';
                dfs(board, row + 1, n, res);
                board[row][col] = '.';
            }
        }
    }
    
    private static List<String> construct(char[][] board, int n) {
        List<String> res = new ArrayList<>();
        for (int i=0; i<n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j=0; j<n; j++) {
                if (board[i][j] == 'Q') {
                    sb.append("Q");
                } else {
                    sb.append(".");
                }
            }
            res.add(sb.toString());
        }
        return res;
    }

    public static void main(String... s) {
        int n = 4;
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }
        List<List<String>> res = new ArrayList<>();
        dfs(board, 0, n, res);
        System.out.println(res);
        // [[.Q.., ...Q, Q..., ..Q.], [..Q., Q..., ...Q, .Q..]]
    }
}

// Complexity Analysis
// Time Complexity: O(N!), where N is the number of queens. In the worst case, we
// have to explore all the configurations of the board. However, this is not the
// tight bound as many configurations will be pruned.
// Space Complexity: O(N^2), where N is the number of queens. We are using an
// auxiliary board of size N x N. The recursion stack can go as deep as N, but
// this is not the tight bound as well.