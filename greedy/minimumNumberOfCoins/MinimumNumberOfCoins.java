package greedy.minimumNumberOfCoins;

import java.util.*;

public class MinimumNumberOfCoins {

    // Minimum Number Of Coins
    static void findMinCoins(int[] coins, int amount) {
        Arrays.sort(coins); // Sort ascending first
        int n = coins.length;

        List<Integer> result = new ArrayList<>();

        for (int i = n - 1; i >= 0; i--) {
            while (amount >= coins[i]) {
                amount -= coins[i];
                result.add(coins[i]);
            }
        }

        System.out.println("Minimum coins required:");
        for (int coin : result) {
            System.out.print(coin + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Test Minimum Number Of Coins
        int[] coins = { 1, 2, 5, 10, 20, 50, 100, 500, 2000 };
        int amount = 93;
        findMinCoins(coins, amount); // Expected output: 50 20 20 2 1

    }
}

// Complexity Analysis
// Time Complexity: O(n log n) due to sorting the coin denominations.
// Space Complexity: O(1) if we ignore the space used for the result list.