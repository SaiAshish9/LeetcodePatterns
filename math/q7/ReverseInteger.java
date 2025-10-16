package math.q7;

public class ReverseInteger {

    private static int reverse(int x) {
        long reversed = 0;
        while (x != 0) {
            int digit = x % 10;
            reversed = reversed * 10 + digit;
            x /= 10;
        }
        if (reversed < Integer.MIN_VALUE || reversed > Integer.MAX_VALUE) {
            return 0;
        }
        return (int) reversed;
    }

    public static void main(String[] args) {
        int x = 123;
        System.out.println(reverse(x)); // Output: 321

        x = -123;
        System.out.println(reverse(x)); // Output: -321

        x = 120;
        System.out.println(reverse(x)); // Output: 21

        x = 0;
        System.out.println(reverse(x)); // Output: 0

        x = 1534236469;
        System.out.println(reverse(x)); // Output: 0 (overflow case)
    }

}

// Complexity Analysis
// Time Complexity: O(log|x|), where |x| is the absolute value of x. This is because we are processing each digit of the number once.
// Space Complexity: O(1), as we are using a constant amount of space.
