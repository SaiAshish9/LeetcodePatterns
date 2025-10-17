package math.q2514;

public class CountAnagrams {
    
    private static final int MOD = 1000000007;

    public int countAnagrams(String s) {
        String[] words = s.split(" ");
        long[] fact = new long[s.length() + 1];
        long[] invFact = new long[s.length() + 1];
        computeFactAndInvFact(fact, invFact, s.length());

        long result = 1;
        for (String word : words) {
            result = result * fact[word.length()] % MOD;
            int[] count = new int[26];
            for (char c : word.toCharArray()) {
                count[c - 'a']++;
            }
            for (int freq : count) {
                if (freq > 1) {
                    result = result * invFact[freq] % MOD;
                }
            }
        }
        return (int) result;
    }

    private void computeFactAndInvFact(long[] fact, long[] invFact, int n) {
        fact[0] = invFact[0] = 1;
        for (int i = 1; i <= n; i++) {
            fact[i] = fact[i - 1] * i % MOD;
            invFact[i] = modInverse(fact[i], MOD);
        }
    }

    private long modInverse(long a, int mod) {
        return power(a, mod - 2, mod);
    }

    private long power(long x, int y, int mod) {
        long res = 1;
        x = x % mod;
        while (y > 0) {
            if (y % 2 == 1) {
                res = (res * x) % mod;
            }
            y = y >> 1;
            x = (x * x) % mod;
        }
        return res;
    }

    public static void main(String[] args) {
        CountAnagrams ca = new CountAnagrams();
        String s = "too hot";
        int result = ca.countAnagrams(s);
        System.out.println("Number of anagrams: " + result); // Expected output: 18
    }
}

// Complexity Analysis
// Time Complexity: O(n + m), where n is the length of the string s and m is the number of words in s.
// Space Complexity: O(1), since the space used for counting characters is constant (26 letters in the alphabet).