package matrixes.q1275;

public class FindWinnerOnATicTacToeGame {

    private static String tictactoe(int[][] moves) {
        int[][] board = new int[3][3];

        for (int i = 0; i < moves.length; i++) {
            int player = (i % 2 == 0) ? 1 : -1;
            int row = moves[i][0];
            int col = moves[i][1];
            board[row][col] = player;
        }

        if (checkForWin(board, 1)) {
            return "A";
        } else if (checkForWin(board, -1)) {
            return "B";
        } else if (moves.length == 9) {
            return "Draw";
        } else {
            return "Pending";
        }
    }

    private static boolean checkForWin(int[][] board, int player) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true;
            }
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
                return true;
            }
        }

        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        int[][] moves = { { 0, 0 }, { 2, 0 }, { 1, 1 }, { 2, 1 }, { 2, 2 } };
        System.out.println(tictactoe(moves)); // Output: "A"
    }
    
}

// Time Complexity: O(1) - The board size is fixed (3x3), so the operations are constant time.
// Space Complexity: O(1) - We are using a fixed amount of space for the board.
