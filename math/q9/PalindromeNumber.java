package math.q9;

public class PalindromeNumber {

    private static boolean isPalindrome(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int reversed = 0, original = x;

        while (x > 0) {
            int digit = x % 10;
            if (reversed > (Integer.MAX_VALUE - digit) / 10) {
                return false;
            }
            reversed = reversed * 10 + digit;
            x /= 10;
        }

        return original == reversed;
    }

    public static void main(String[] args) {
        int x = 121;
        System.out.println(isPalindrome(x)); // Output: true
    }
    
}
