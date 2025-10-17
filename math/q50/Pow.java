package math.q50;

public class Pow {

    private static double myPow(double x, int n) {
        long power = n;
        if (power < 0) {
            x = 1 / x;
            power = -power;
        }
        return fastPow(x, power);
    }

    private static double fastPow(double x, long n) {
        if (n == 0) return 1.0;
        double half = fastPow(x, n / 2);
        return (n % 2 == 0) ? half * half : half * half * x;
    }

    public static void main(String[] args) {
        System.out.println(myPow(2.00000, 10)); // 1024.00000
    }   
    
}

// Time Complexity: O(log n)
// Space Complexity: O(log n) due to recursion stack