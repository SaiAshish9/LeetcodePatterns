package matrixes.q79;

public class WordSearch {
    private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    private static boolean exist(char[][] board, String word) {
        int rows = board.length;
        int cols = board[0].length;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (dfs(board, word, row, col, 0)) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean dfs(char[][] board, String word, int row, int col, int index) {
        if (index == word.length()) {
            return true;
        }
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length ||
                board[row][col] != word.charAt(index)) {
            return false;
        }
        char temp = board[row][col];
        board[row][col] = '#';
        for (int[] direction : DIRECTIONS) {
            int newRow = row + direction[0];
            int newCol = col + direction[1];
            if (dfs(board, word, newRow, newCol, index + 1)) {
                return true;
            }
        }
        board[row][col] = temp;
        return false;
    }

    public static void main(String[] args) {
        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        String word1 = "ABCCED";
        String word2 = "SEE";
        String word3 = "ABCB";  
        System.out.println(exist(board, word1)); // true
        System.out.println(exist(board, word2)); // true
        System.out.println(exist(board, word3)); // false
    }
}

// Time Complexity: O(N * 3^L), where N is the number of cells in the board and L is the length of the word.
// Space Complexity: O(L) for the recursion stack.