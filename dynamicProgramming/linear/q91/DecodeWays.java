package dynamicProgramming.linear.q91;

public class DecodeWays {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0 || s.charAt(0) == '0') return 0;
        
        int n = s.length();
        int[] dp = new int[n + 1];

        dp[0] = 1; // Empty string has 1 way
        dp[1] = 1; // First char is valid because we already checked != '0'

        for (int i = 2; i <= n; i++) {
            char one = s.charAt(i - 1);
            char two = s.charAt(i - 2);

            // One digit (non-zero)
            if (one != '0') {
                dp[i] += dp[i - 1];
            }

            // Two digits (between 10 and 26)
            int num = (two - '0') * 10 + (one - '0');
            if (num >= 10 && num <= 26) {
                dp[i] += dp[i - 2];
            }
        }

        return dp[n];
    }
}