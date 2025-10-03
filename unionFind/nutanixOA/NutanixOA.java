package unionFind.nutanixOA;

import java.util.*;

/*
Sample input
1
3
2 ALICE BOB
2 ALEX ALEX
1 ALICE

Sample output
2

Explaination
the first and third share a common usernames ['ALICE']. 
Hence, they form a group.
*/

public class NutanixOA {

    private static class UF {
        int[] parent;

        UF(int n) {
            this.parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int u) {
            if (parent[u] != u) {
                parent[u] = find(parent[u]);
            }
            return parent[u];
        }

        public boolean union(int u, int v) {
            int x = find(u);
            int y = find(v);

            if (x == y) {
                return false;
            }

            parent[v] = u;

            return true;
        }
    }

    public static void main(String... s) {
        Scanner sc = new Scanner(System.in);
        int testCases = sc.nextInt();

        while (testCases-- > 0) {
            int count = sc.nextInt();
            List<List<String>> groups = new ArrayList<>();
            Map<String, List<Integer>> map = new HashMap<>();
            int index = 0;
            int temp = count;

            while (temp-- > 0) {
                int groupCount = sc.nextInt();
                List<String> group = new ArrayList<>();
                while (groupCount-- > 0) {
                    String username = sc.next();
                    group.add(username);
                    if (map.containsKey(username)) {
                        if (!map.get(username).contains(index)) {
                            map.get(username).add(index);
                        }
                    } else {
                        List<Integer> value = new ArrayList<>();
                        value.add(index);
                        map.put(username, value);
                    }
                }
                groups.add(group);
                index++;
            }

            System.out.println(map);
            UF uf = new UF(count);

            for (Map.Entry<String, List<Integer>> entry : map.entrySet()) {
                List<Integer> values = entry.getValue();
                int n = values.size();
                int first = values.get(0);

                if (n > 1) {
                    for (int i = 1; i < n; i++) {
                        int second = values.get(1);
                        if (uf.union(first, second)) {
                            count--;
                        }
                    }
                }
            }

            System.out.println(count);

        }
    }

}

/*
 * 1
 * 3
 * 2 ALICE BOB
 * 2 ALEX ALEX
 * 1 ALICE
 * {ALEX=[1], BOB=[0], ALICE=[0, 2]}
 * 2
 * Complexity Analysis
 * Time Complexity: O(N * K) where N is the number of groups and K is the
 * average number of usernames in each group.
 * Space Complexity: O(N * K) for storing the map and the Union-Find data
 * structure.
 */