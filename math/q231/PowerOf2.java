package math.q231;

public class PowerOf2 {

    private static boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }

    public static void main(String[] args) {
      System.out.println(isPowerOfTwo(016)); // true
      System.out.println(isPowerOfTwo(218)); // false
    }

}


// Complexity Analysis  
// Time Complexity: O(1)  
// Space Complexity: O(1)