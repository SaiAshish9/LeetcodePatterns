package rejectionSampling.q470;

public class ImplementRand10UsingRand7 {

    private static int rand7() {
        return (int) (Math.random() * 7) + 1; // Simulates a rand7() function
    }

    private static int rand10() {
        while (true) {
            int num = (rand7() - 1) * 7 + rand7(); // Generate a number from 1 to 49
            if (num <= 40) { // Accept only if the number is in the range 1 to 40
                return 1 + (num - 1) % 10; // Map to the range 1 to 10
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Random number from 1 to 10: " + rand10()); // Example usage
        // You can call rand10() multiple times to see the distribution
        // of generated numbers.
    }

}

// Complexity Analysis
// Time Complexity: O(1) on average, since the expected number of iterations to
// get a valid
// number is constant.
// Space Complexity: O(1), as we are using a constant amount of space.