package dynamicProgramming.linear.q53;

import java.util.*;;

public class MaximumSubArray {
    // dp[i]: Maximum subarray sum ending at index i
    public static int maxSubArray(int[] a) {
        int dp[] = new int[a.length];
        dp[0] = a[0];
        for (int i = 1; i < a.length; i++) {
            dp[i] = Math.max(dp[i - 1] + a[i], a[i]);
        }
        System.out.println(Arrays.toString(dp));
        return Arrays.stream(dp).max().getAsInt();
    }
}