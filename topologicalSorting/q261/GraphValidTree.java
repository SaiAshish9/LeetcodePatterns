package topologicalSorting.q261;

import java.util.*;

public class GraphValidTree {
    
    private static class Node {
        int data;
        List<Node> neighbors;
        
        Node(int data){
            this.data = data;
            this.neighbors = new ArrayList<>();
        }
    }
    
    private static boolean validTree(List<Node> nodes, int n, int edges){
        
        if(edges != n - 1){
            return false;
        }
        
        Queue<Node> queue = new ArrayDeque<>();
        int[] indegree = new int[n];
        
        for(Node node: nodes){
            List<Node> neighbors = node.neighbors;
            
            for (Node neighbor: neighbors) {
                indegree[neighbor.data]++;
            }
        }
        
        int count = 0;
        
        for (Node node: nodes){
            if(indegree[node.data] == 0){
                queue.add(node);
            }
        }
        
        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            count++;
            
            for(Node neighbor: curr.neighbors){
                if(--indegree[neighbor.data] == 0) {
                    queue.add(neighbor);
                }
            }
        }
        
        System.out.println(count); // 0
        return count != n;
    }
    
    public static void main(String ...s){
        Node node1 = new Node(0);
        Node node2 = new Node(1);
        Node node3 = new Node(2);
        node1.neighbors.add(node2);
        node2.neighbors.add(node3);
        node3.neighbors.add(node1);
        int edges = 3;
        System.out.println(validTree(Arrays.asList(node1, node2, node3), 3, edges)); // false
    }
    
// Complexity Analysis
// Time Complexity: O(V + E) where V is the number of vertices (nodes)
// and E is the number of edges. We process each node and edge once.
// Space Complexity: O(V) for storing the indegree array and the queue.

}
