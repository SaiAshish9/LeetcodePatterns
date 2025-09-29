/*
Input:
  Graph:
     1
    / \
   2   3
   |
   3

Output:
  A deep-cloned graph with same structure:
     1'
    / \
   2'  3'
   |
   3'

Brute Force Approach:
- Try to copy nodes by iterating through all nodes, 
  and for each, manually clone its neighbors.
- But this risks revisiting nodes multiple times → infinite loop (due to cycles).
- Needs additional structure to prevent repeated work.

Complexity:
- Time: O(N^2) in worst case (since each neighbor list may be revisited multiple times).
- Space: O(N) for storing clones.

Optimal Approach (DFS/BFS with HashMap):
1. Use a HashMap<Node, Node> to store original → clone mapping.
2. Traverse graph (DFS or BFS).
3. For each node:
   - If not cloned, create new node and put in map.
   - Recursively clone all neighbors.
   - Attach cloned neighbors to the cloned node.
4. Return the clone of the starting node.

Complexity:
- Time: O(N + E) where N = number of nodes, E = number of edges.
- Space: O(N) for the map + recursion stack (DFS).

Dry Run:
Graph:
  1
 / \
2 - 3

Step 1: Start with node 1 → clone 1'
Step 2: Visit neighbors of 1 → nodes 2, 3
   - Clone 2' and 3'
   - Attach to 1'
Step 3: Visit neighbors of 2 → node 3
   - 3 already cloned, reuse 3'
   - Attach 3' to 2'
Step 4: Visit neighbors of 3 → node 2
   - Already cloned, reuse 2'
   - Attach 2' to 3'
Done → graph cloned.
*/

package graph.q133;

import java.util.*;

class CloneGraph {
    
    static class Node {
        int val;
        List<Node> neighbors;
        
        public Node (int val) {
            this.val = val;
            this.neighbors = new ArrayList<>();
        }
    }
    
    static Map<Node, Node> cloneMap;
    
    static {
        cloneMap = new HashMap<>();
    }

    private static Node dfs(Node node){
        if (cloneMap.containsKey(node)) {
            return cloneMap.get(node);
        }
        
        Node clone = new Node(node.val);
        cloneMap.put(clone, clone);
        
        for (Node neighbor: node.neighbors) {
            clone.neighbors.add(neighbor);
        }
        
        return clone;
    }
    
    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        node1.neighbors.addAll(Arrays.asList(node2, node3));
        node2.neighbors.add(node3);
        Node clone = dfs(node1);
        System.out.println(clone.val);
        for (Node neighbor: clone.neighbors) {
            System.out.println(neighbor.val);
        }
        // Output: 1 2 3
    }
    
}
