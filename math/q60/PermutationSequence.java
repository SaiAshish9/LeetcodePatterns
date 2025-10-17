package math.q60;

import java.util.*;

public class PermutationSequence {

    private static String getPermutation(int n, int k) {
        List<Integer> nums = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            nums.add(i);
        }

        int[] factorial = new int[n];
        factorial[0] = 1;
        for (int i = 1; i < n; i++) {
            factorial[i] = factorial[i - 1] * i;
        }

        k--;
        StringBuilder result = new StringBuilder();

        for (int i = n; i > 0; i--) {
            int index = k / factorial[i - 1];
            result.append(nums.get(index));
            nums.remove(index);
            k %= factorial[i - 1];
        }

        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(getPermutation(4, 9)); // "2314"
    }

}

// Time Complexity: O(n^2) due to list removal
// Space Complexity: O(n) for the list and factorial array
