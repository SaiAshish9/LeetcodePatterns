package math.q326;

public class PowerOf3 {

    private static boolean isPowerOfThree(int n) {
        if (n <= 0) return false;
        while (n % 3 == 0) {
            n /= 3;
        }
        return n == 1;
    }

    public static void main(String[] args) {
       System.out.println(isPowerOfThree(027)); // true
       System.out.println(isPowerOfThree(45)); // false
    }
    
}

// Complexity Analysis  
// Time Complexity: O(log3(n))  
// Space Complexity: O(1)
