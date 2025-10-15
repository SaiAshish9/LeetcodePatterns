package additional.twoPointers;

import java.util.Scanner;

public class WrongBS {

    public static void solve(Scanner sc) {
        int n = sc.nextInt();
        String s = sc.next();
        int[] permutation = new int[n];

        for (int i = 0; i < n; i++) {
            permutation[i] = i + 1;
        }

        int left = 0;

        while (left < n) {
            if (s.charAt(left) == '1') {
                left++;
                continue;
            }

            int right = left;
            while (right + 1 < n && s.charAt(right + 1) == '0') {
                right++;
            }

            if (right - left + 1 == 1) {
                System.out.println("NO");
                return;
            }

            for (int i = left; i < right; i++) {
                permutation[i] = i + 2;
            }
            permutation[right] = left + 1;

            left = right + 1;
        }

        System.out.println("YES");
        for (int i = 0; i < n; i++) {
            System.out.print(permutation[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        for (int i = 0; i < t; i++) {
            solve(sc);
        }

        sc.close();
    }
}

// 3
// 1 1 1
// YES

// 5
// 1 0 1 0 0
// NO


// Complexity Analysis:
// Time Complexity: O(n) for each test case, where n is the length of the string s.
// Space Complexity: O(n) for storing the permutation array.