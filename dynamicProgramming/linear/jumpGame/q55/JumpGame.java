package dynamicProgramming.linear.jumpGame.q55;

public class JumpGame {
    public boolean canJump(int[] nums) {
        int n = nums.length;
        boolean[] dp = new boolean[n];
        dp[0] = true; // You are always at the start

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                // If j is reachable and we can jump from j to i
                if (dp[j] && j + nums[j] >= i) {
                    dp[i] = true;
                    break; // No need to check further
                }
            }
        }

        return dp[n - 1]; // Can we reach the last index?
    }
}