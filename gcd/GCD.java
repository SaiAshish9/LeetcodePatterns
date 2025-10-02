package gcd;

public class GCD {

    private static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
    // Complexity: O(log(min(a, b)))

    private static int gcdIterative(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
    // Complexity: O(log(min(a, b)))

    public static void main(String[] args) {
        System.out.println(gcd(48, 18)); // Output: 6
        System.out.println(gcdIterative(48, 18)); // Output: 6 
    }
    
}
