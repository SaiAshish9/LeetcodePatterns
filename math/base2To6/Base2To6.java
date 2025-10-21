package math.base2To6;

public class Base2To6 {

    private static String base2ToBase6(String binaryStr) {
        int decimal = Integer.parseInt(binaryStr, 2);
        if (decimal == 0) return "0";

        StringBuilder sb = new StringBuilder();
        while (decimal > 0) {
            sb.append(decimal % 6);
            decimal /= 6;
        }

        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        String[] binaryInputs = {"0", "1", "10", "11", "100", "1101", "100100"};

        for (String binary : binaryInputs) {
            System.out.println("Base 2: " + binary + " -> Base 6: " + base2ToBase6(binary));
        }
        // Expected Outputs:
        // Base 2: 0 -> Base 6: 0
        // Base 2: 1 -> Base 6: 1
        // Base 2: 10 -> Base 6: 2
        // Base 2: 11 -> Base 6: 3
        // Base 2: 100 -> Base 6: 4
        // Base 2: 1101 -> Base 6: 5
        // Base 2: 100100 -> Base 6: 20
    }
}

// Complexity Analysis
// Time Complexity: O(log n) where n is the decimal value of the binary input.
// Space Complexity: O(log n) for the output string in base 6.  