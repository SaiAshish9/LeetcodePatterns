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
    }
    
}
