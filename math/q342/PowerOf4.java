package math.q342;

public class PowerOf4 {

    private static boolean isPowerOfFour(int n) {
        return n > 0 && (n & (n - 1)) == 0 && (n - 1) % 3 == 0;
    }

    public static void main(String[] args) {
        System.out.println(isPowerOfFour(016)); // true
        System.out.println(isPowerOfFour(32)); // false
    }

}
