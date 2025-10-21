package unionFind.nutanixOA;

import java.util.*;

public class NutanixOA2 {

    static class UnionFind {
        public int[] parent;
        public int[] rank;

        public UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
        }
    }

    private static List<Integer> countUserGroups(List<List<String>> testCases) {
        List<Integer> results = new ArrayList<>();

        for (List<String> users : testCases) {
            int n = Integer.parseInt(users.get(0));
            List<Set<String>> parsedUsers = new ArrayList<>();

            for (int i = 1; i <= n; i++) {
                String line = users.get(i);
                String[] names = line.substring(1).trim().split(" ");
                parsedUsers.add(new HashSet<>(Arrays.asList(names)));
            }

            UnionFind uf = new UnionFind(n);

            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    Set<String> set1 = parsedUsers.get(i);
                    Set<String> set2 = parsedUsers.get(j);

                    // Check if any common username exists
                    for (String name : set1) {
                        if (set2.contains(name)) {
                            uf.union(i, j);
                            break;
                        }
                    }
                }
            }

            Set<Integer> groups = new HashSet<>();
            for (int i = 0; i < n; i++) {
                groups.add(uf.find(i));
            }

            // Optional: Print for debugging
            System.out.println("Parent: " + Arrays.toString(uf.parent));
            System.out.println("Rank: " + Arrays.toString(uf.rank));

            results.add(groups.size());
        }

        return results;
    }

    public static void main(String[] args) {
        List<List<String>> testCases = new ArrayList<>();

        // Sample input: 1 test case with 3 entries
        List<String> testCase = new ArrayList<>();
        testCase.add("3"); // Number of usernames

        // Properly formatted user entries
        testCase.add("2ALICE BOB");
        testCase.add("2ALEX ALEX");
        testCase.add("1ALICE");

        testCases.add(testCase);

        List<Integer> output = countUserGroups(testCases);

        for (int result : output) {
            System.out.println(result); // Expected: 2
        }
    }
}