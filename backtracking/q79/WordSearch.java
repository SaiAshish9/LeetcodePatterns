package backtracking.q79;

public class WordSearch {

    private static final int[][] DIRECTIONS = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

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
        System.out.println(exist(new char[][] { { 'A', 'B', 'C', 'E' }, { 'S', 'F', 'C', 'S' },
                { 'A', 'D', 'E', 'E' } }, "ABCCED")); // true
    }

}

// Complexity Analysis
// Time Complexity: O(N * 3^L), where N is the number of cells in the board and L is the length of the
// word to be matched. In the worst case, we might have to explore all 3 directions (up, down, left, right)
// for each character in the word, leading to 3^L possible paths. The initial N factor comes from the fact that we
// might have to start a DFS from each cell in the board