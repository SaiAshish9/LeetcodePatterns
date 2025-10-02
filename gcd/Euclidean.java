package gcd;

public class Euclidean {

    private static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private static int lcm(int a, int b) {
        if (a == 0 || b == 0)
            return 0;
        return Math.abs(a / gcd(a, b) * b);
    }

    public static void main(String[] args) {
        System.out.println(gcd(48, 18)); // Output: 6
        System.out.println(lcm(4, 5)); // Output: 20
        // Complexity: O(log(min(a, b)))
    }

}
