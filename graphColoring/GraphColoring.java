package graphColoring;

import java.util.*;

public class GraphColoring {
    
   private static enum Color {
       TRANSPARENT,
       VIOLET,
       INDIGO,
       BLUE,
       GREEN,
       YELLOW,
       ORANGE,
       RED
   };
 
   private static class Node {
        int data;
        Color color;
        List<Node> neighbors;
        
        public Node(int data){
            this.data = data;
            this.neighbors = new ArrayList<>();
            this.color = Color.TRANSPARENT;
        }
   }
   
   private static void color(Node node, int n){
       Queue<Node> queue = new ArrayDeque<>();
       queue.add(node);

       List<Color> colors = Arrays.asList(
           Color.VIOLET, Color.INDIGO, Color.BLUE, 
           Color.GREEN, Color.YELLOW, Color.ORANGE, Color.RED
       );
       
       node.color = colors.get(0);
       
       while(!queue.isEmpty()){
           Node curr = queue.poll();
           
           boolean[] usedColors = new boolean[n];
           for(Node neighbor: curr.neighbors){
               if (neighbor.color != Color.TRANSPARENT) {
                   usedColors[colors.indexOf(neighbor.color)] = true;
               } else {
                   queue.add(neighbor);
               }
           }
           
       
               for(int c = 0; c < n; c++){
                   if(!usedColors[c]){
                       curr.color = colors.get(c);
                       break;
                   }
               }
           
           if(curr.color == Color.TRANSPARENT){
               System.out.println("Cannot be colored");
               return;
           }
       }
   }
   
   private static void dfs(Node node, int n){
      Map<Node, Boolean> visited = new HashMap<>();
      dfsH(node, visited);
   }
   
   private static void dfsH(Node node, Map<Node, Boolean> visited){
      visited.put(node, true);
      System.out.println("Node " + node.data + " -> " + node.color);
      
      for(Node neighbor: node.neighbors){
         if(!visited.containsKey(neighbor)){
            dfsH(neighbor, visited);
         }
      }
   }
 
   public static void main(String ...s){
       Node node1 = new Node(1);
       Node node2 = new Node(2);
       Node node3 = new Node(3);
       Node node4 = new Node(4);
       Node node5 = new Node(5);
       Node node6 = new Node(6);
       int n = 7;
       
       node1.neighbors.addAll(Arrays.asList(node2, node6));
       node2.neighbors.addAll(Arrays.asList(node1, node3));
       node3.neighbors.addAll(Arrays.asList(node2, node4));
       node4.neighbors.addAll(Arrays.asList(node3, node5));
       node5.neighbors.addAll(Arrays.asList(node4, node6));
       node6.neighbors.addAll(Arrays.asList(node5, node1));
       
       color(node1, n);
       dfs(node1, n);
   }
}

