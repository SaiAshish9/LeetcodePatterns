package backtracking.q37;

import java.util.*;

public class SudokuSolver {

    public static void main(String[] args) {

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