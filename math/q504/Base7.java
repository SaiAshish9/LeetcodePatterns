package math.q504;

public class Base7 {

    private static String convertToBase7(int num) {
        if (num == 0)
            return "0";
        boolean isNegative = num < 0;
        num = Math.abs(num);

        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            sb.append(num % 7);
            num /= 7;
        }

        if (isNegative)
            sb.append('-');
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(convertToBase7(-7)); // Output: "-10"
        System.out.println(convertToBase7(100)); // Output: "202"
    }

}

// Complexity Analysis
// Time Complexity: O(log7(n)), where n is the absolute value of the input
// number. This is because we repeatedly divide the number by 7 until it becomes
// 0.
// Space Complexity: O(log7(n)) for storing the digits of the base 7
// representation in the StringBuilder.
