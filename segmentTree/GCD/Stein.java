// Stein’s Algorithm (also called the Binary GCD algorithm) is an efficient way to compute the GCD using bitwise operations instead of division/modulo.

package segmentTree.GCD;

public class Stein {

    public static int gcd(int a, int b) {
        if (a == 0)
            return b;
        if (b == 0)
            return a;

        // Find common factors of 2
        int shift;
        for (shift = 0; ((a | b) & 1) == 0; ++shift) {
            a >>= 1;
            b >>= 1;
        }

        // Divide a by 2 until it becomes odd
        while ((a & 1) == 0)
            a >>= 1;

        do {
            // Remove factors of 2 in b
            while ((b & 1) == 0)
                b >>= 1;

            // Now both a and b are odd. Swap if necessary so a <= b
            if (a > b) {
                int temp = a;
                a = b;
                b = temp;
            }

            b = b - a; // Here b >= a
        } while (b != 0);

        // Restore common factors of 2
        return a << shift;
    }

    public static void main(String[] args) {
        System.out.println("gcd(48, 18) = " + gcd(48, 18)); // 6
        System.out.println("gcd(270, 192) = " + gcd(270, 192)); // 6
        System.out.println("gcd(4, 6) = " + gcd(4, 6)); // 2
    }

    // Complexity: O(log(min(a, b)))
    // More efficient than Euclidean algorithm for large numbers

}

/**
 * Stein's Algorithm (Binary GCD)
 *
 * Rules:
 1. gcd(0, b) = b and gcd(a, 0) = a
 2. If both a and b are even:
    gcd(a, b) = 2 * gcd(a/2, b/2)
 3. If a is even and b is odd:
    gcd(a, b) = gcd(a/2, b)
 4. If a is odd and b is even:
    gcd(a, b) = gcd(a, b/2)
 5. If both are odd:
    gcd(a, b) = gcd(|a-b|/2, min(a, b))
 */

 