package math.q1017;

public class ConvertToBaseMinus2 {

    private static String baseNeg2(int n) {
        if (n == 0)
            return "0";

        StringBuilder sb = new StringBuilder();
        while (n != 0) {
            int remainder = n % -2;
            n /= -2;
            if (remainder < 0) {
                remainder += 2;
                n++;
            }
            sb.append(remainder);
        }

        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(baseNeg2(2)); // Expected output: "110"
        System.out.println(baseNeg2(3)); // Expected output: "111"
        System.out.println(baseNeg2(4)); // Expected output: "100"
    }

}

// Complexity Analysis
// Time Complexity: O(log|n|), where n is the input integer. The number of
// digits
// in base -2 representation is proportional to log|n|.
// Space Complexity: O(log|n|) for the output string.
