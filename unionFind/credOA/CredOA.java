package unionFind.credOA;

/*
Sample input:
3 3 4
d 1 1
d 2 2
d 3 3

Sample output
3
*/

import java.util.*;

public class CredOA {
    static int rows, cols;
    static int[] parent;
    static boolean[] hasDrop;
    static int clusterCount = 0;

    public static void main(String[] args) {
        int[][] directions = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}}; // 4 neighbors

        Scanner sc = new Scanner(System.in);
        rows = sc.nextInt();
        cols = sc.nextInt();
        int queries = sc.nextInt();

        parent = new int[rows * cols];
        hasDrop = new boolean[rows * cols];

        for (int i = 0; i < rows * cols; i++) {
            parent[i] = i;
        }

        while (queries-- > 0) {
            sc.next(); 
            int row = sc.nextInt() - 1;
            int col = sc.nextInt() - 1;
            int cellId = row * cols + col;

            if (hasDrop[cellId]) {
                System.out.println(clusterCount);
                continue;
            }

            hasDrop[cellId] = true;
            clusterCount++;

            for (int[] dir : directions) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];
                if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols) {
                    int neighborId = newRow * cols + newCol;
                    if (hasDrop[neighborId]) {
                        if (union(cellId, neighborId)) {
                            clusterCount--; 
                        }
                    }
                }
            }

            System.out.println(clusterCount);
        }
    }

    static int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // path compression
        }
        return parent[x];
    }

    static boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA == rootB) return false;
        parent[rootA] = rootB;
        return true;
    }
}
