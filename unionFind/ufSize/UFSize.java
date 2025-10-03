package unionFind.ufSize;

public class UFSize {
    static class UF {
        
        int[] parent;
        int[] size;
        
        UF(int n){
            parent = new int[n];
            size = new int[n];
            for(int i =0;i<n;i++){
                parent[i] = i;
                size[i] = 1;
            }
        }
        
        public int find(int u) {
            if (u != parent[u]) {
                parent[u] = find(parent[u]); // path compression
            }
            return parent[u];
        }
        
        public boolean union(int u, int v){
            int x = find(u);
            int y = find(v);
            
            if(x == y) return false;
            
            if(size[x] < size[y]){
                parent[x] = y;
                size[y] += size[x]; 
            } else {
                parent[y] = x;
                size[x] += size[y]; 
            } 
            
            return true;
        }
    }
        
        public static void main(String ...s){
            UF uf = new UF(5);
            System.out.println(uf.find(1));
            uf.union(1, 2);
            uf.union(2, 3);
            System.out.println(uf.find(1));
            System.out.println(uf.find(2));
            System.out.println(uf.find(3));
        }

        // Complexity Analysis
        // Time Complexity: O(α(N)) for both union and find operations, where α is the
        // inverse Ackermann function. This is nearly constant time for all practical
        // values of N.
        // Space Complexity: O(N) for storing the parent and size arrays.   
        
    }
