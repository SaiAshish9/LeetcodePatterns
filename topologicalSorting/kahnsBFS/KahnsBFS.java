package topologicalSorting.kahnsBFS;

import java.util.*;

class KahnsBFS {
    
    static class Node {
        private int data;
        private List<Node> neighbors;
        
        Node(int data) {
            this.data = data;
            this.neighbors = new ArrayList<>();
        }
    }
    
    private static void computeTopologicalSort(List<Node> nodes, int n){
        int[] indegree = new int[n];
        Arrays.fill(indegree, 0);
        
        for(Node node: nodes){
          List<Node> neighbors = node.neighbors;
        
           for(Node neighbor: neighbors){
               indegree[neighbor.data]++;
           }
        }
        
        Queue<Node> queue = new ArrayDeque<>();
        
        for(Node node: nodes){
            if(indegree[node.data]==0){
                queue.add(node);
            }
        }
        
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            System.out.println(node.data);
            List<Node> neighbors = node.neighbors;
            
            for (Node neighbor: neighbors){
                if(--indegree[neighbor.data]==0){
                    queue.add(neighbor);
                }
            }
        }

    }
    
    public static void main(String ...s){
        Node node1 = new Node(0);
        Node node2 = new Node(1);
        Node node3 = new Node(2);
        Node node4 = new Node(3);
        Node node5 = new Node(4);
        node1.neighbors.add(node2);
        node2.neighbors.add(node3);
        node3.neighbors.add(node4);
        node4.neighbors.add(node5);
        computeTopologicalSort(Arrays.asList(node1, node2, node3, node4, node5), 5);
    }
    
}