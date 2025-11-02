package dynamicProgramming.linear.jumpGame.q45;

import java.util.*;

public class JumpGame2 {

    public static int jump(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (j + nums[j] >= i) {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }
        System.out.println(Arrays.toString(dp));
        return dp[n - 1];
    }
};