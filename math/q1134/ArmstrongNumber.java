package math.q1134;

public class ArmstrongNumber {

    private static boolean isArmstrong(int n) {
        String s = Integer.toString(n);
        int k = s.length();
        int sum = 0, temp = n;
        while (temp > 0) {
            int digit = temp % 10;
            sum += Math.pow(digit, k);
            temp /= 10;
        }
        return sum == n;
    }

    public static void main(String[] args) {
      System.out.println(isArmstrong(0)); // true
      System.out.println(isArmstrong(153)); // true
      System.out.println(isArmstrong(9474)); // true
    }

}

// Complexity Analysis
// Time Complexity: O(d), where d is the number of digits in n.
// Space Complexity: O(1)
