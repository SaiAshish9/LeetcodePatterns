package math.q172;

public class FactorialTrailingZeroes {

    private static int trailingZeroes(int n) {
        int count = 0;
        while (n >= 5) {
            n /= 5;
            count += n;
        }
        return count;
    }

    public static void main(String[] args) {
        int n = 100;
        System.out.println("Number of trailing zeroes in " + n + "! is: " + trailingZeroes(n));
        // Expected output: 24
    }

}

// Complexity Analysis
// Time Complexity: O(log n)
// Space Complexity: O(1)