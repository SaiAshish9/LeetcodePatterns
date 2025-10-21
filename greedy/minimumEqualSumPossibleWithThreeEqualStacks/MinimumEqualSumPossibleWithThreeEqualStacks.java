package greedy.minimumEqualSumPossibleWithThreeEqualStacks;

import java.util.Arrays;
import java.util.Stack;

public class MinimumEqualSumPossibleWithThreeEqualStacks {
    static int maxEqualSum(int[] stack1, int[] stack2, int[] stack3) {
        Stack<Integer> s1 = buildStack(stack1);
        Stack<Integer> s2 = buildStack(stack2);
        Stack<Integer> s3 = buildStack(stack3);

        int sum1 = Arrays.stream(stack1).sum();
        int sum2 = Arrays.stream(stack2).sum();
        int sum3 = Arrays.stream(stack3).sum();

        while (true) {
            // If any stack is empty
            if (s1.isEmpty() || s2.isEmpty() || s3.isEmpty()) {
                return 0;
            }
            // If all sums are equal
            if (sum1 == sum2 && sum2 == sum3) {
                return sum1;
            }

            // Remove top from the stack with maximum sum
            if (sum1 >= sum2 && sum1 >= sum3) {
                sum1 -= s1.pop();
            } else if (sum2 >= sum1 && sum2 >= sum3) {
                sum2 -= s2.pop();
            } else {
                sum3 -= s3.pop();
            }
        }
    }

    private static Stack<Integer> buildStack(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        // Push elements from end to start so first element is at bottom
        for (int i = arr.length - 1; i >= 0; i--) {
            stack.push(arr[i]);
        }
        return stack;
    }

    public static void main(String[] args) {
        int[] stack1 = { 3, 2, 1, 1, 1 };
        int[] stack2 = { 4, 3, 2 };
        int[] stack3 = { 1, 1, 4, 1 };

        int result = maxEqualSum(stack1, stack2, stack3);
        System.out.println("Maximum possible equal sum: " + result); // 5
    }
}

// Complexity Analysis
// Time Complexity: O(n1 + n2 + n3), where n1, n2, and n3 are the sizes of the three stacks.
// In the worst case, we may have to remove all elements from all three stacks.
// Space Complexity: O(1), as we are using only a constant amount of extra space.
