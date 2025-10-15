package math.q3190;

public class FindMinimumOperationsToMakeAllElementsDivisibleByThree {

    public static int minOperations(int[] nums) {
        int operations = 0;

        for (int num : nums) {
            int remainder = num % 3;
            operations += Math.min(remainder, 3 - remainder);
        }

        return operations;
    }

    public static void main(String[] args) {
        int[][] testCases = {
            {3, 6, 9},     // already divisible -> 0
            {4, 7, 10},    // all % 3 == 1 -> 1 op each -> 3
            {5, 8, 11},    // all % 3 == 2 -> 1 op each -> 3
            {1, 2, 3, 4, 5}, // mixed
            {0, 1, 2, 3, 4, 5, 6}
        };

        for (int[] test : testCases) {
            System.out.print("Array: ");
            for (int n : test) System.out.print(n + " ");
            System.out.println("-> Min Ops: " + minOperations(test));
        }
    }
}