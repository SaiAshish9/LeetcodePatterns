package design.q909;

import java.util.*;

public class SnakeAndLadders {

    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        int[] flattenedBoard = new int[n * n];

        int index = 0;
        boolean leftToRight = true;
        for (int i = n - 1; i >= 0; i--) {
            if (leftToRight) {
                for (int j = 0; j < n; j++) {
                    flattenedBoard[index++] = board[i][j];
                }
            } else {
                for (int j = n - 1; j >= 0; j--) {
                    flattenedBoard[index++] = board[i][j];
                }
            }
            leftToRight = !leftToRight;
        }

        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[n * n];
        queue.offer(0);
        visited[0] = true;
        int moves = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int current = queue.poll();

                if (current == n * n - 1) {
                    return moves;
                }

                for (int dice = 1; dice <= 6; dice++) {
                    int next = current + dice;
                    if (next >= n * n)
                        break;
                    int destination = flattenedBoard[next] == -1 ? next : flattenedBoard[next] - 1;
                    if (!visited[destination]) {
                        visited[destination] = true;
                        queue.offer(destination);
                    }
                }
            }
            moves++;
        }

        return -1;
    }

    public static void main(String[] args) {
        SnakeAndLadders solution = new SnakeAndLadders();
        int[][] board = {
                {-1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, 35, -1, -1, 13, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, 15, -1, -1, -1, -1}
        };
        int result = solution.snakesAndLadders(board);
        System.out.println("Minimum moves to reach the end: " + result); // Output: 4
    }
}

// Complexity Analysis
// Time Complexity: O(N^2), where N is the size of the board (N x N). We may need to visit each cell once in the worst case.
// Space Complexity: O(N^2) for the queue and visited array.