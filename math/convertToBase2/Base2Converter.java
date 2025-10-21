package math.convertToBase2;

public class Base2Converter {

    private static String toBinary(int n) {
        if (n == 0) return "0";

        StringBuilder sb = new StringBuilder();

        while (n > 0) {
            sb.append(n % 2);
            n /= 2;
        }

        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        int[] testCases = {0, 1, 2, 5, 10, 15, 32, 100};

        for (int num : testCases) {
            System.out.println("Binary of " + num + " = " + toBinary(num));
        }
    }
    // Complexity Analysis
    // Time Complexity: O(log n) where n is the input number.
    // Space Complexity: O(log n) for the output string.
}

// Example Outputs:
// Binary of 0 = 0
// Binary of 1 = 1
// Binary of 2 = 10
// Binary of 5 = 101
// Binary of 10 = 1010
// Binary of 15 = 1111
// Binary of 32 = 100000
// Binary of 100 = 1100100  
