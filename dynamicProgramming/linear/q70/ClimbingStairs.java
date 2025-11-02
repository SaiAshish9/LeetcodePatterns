import java.util.Arrays;

public class ClimbingStairs {
    public static void main(String[] args) {
        int n = 2;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, 1);
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        System.out.println(dp[n]);
    }
}

// Complexity Analysis:
// Time Complexity: O(n)
// Space Complexity: O(n)
