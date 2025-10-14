package backtracking.q988;

public class SmallestStringStartingFromLeaf {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    private static String smallest = null;

    private static String smallestFromLeaf(TreeNode root) {
        dfs(root, new StringBuilder());
        return smallest;
    }

    private static void dfs(TreeNode node, StringBuilder sb) {
        if (node == null) return;

        sb.insert(0, (char) ('a' + node.val));

        if (node.left == null && node.right == null) {
            String current = sb.toString();
            if (smallest == null || current.compareTo(smallest) < 0) {
                smallest = current;
            }
        }

        dfs(node.left, sb);
        dfs(node.right, sb);
        sb.deleteCharAt(0);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(0,
                new TreeNode(1,
                        new TreeNode(3),
                        new TreeNode(4)),
                new TreeNode(2,
                        new TreeNode(3),
                        null)); 
        System.out.println(smallestFromLeaf(root)); // Output: "dba"
    }
    
}

// Complexity Analysis
// Time Complexity: O(N^2) in the worst case, where N is the number of nodes in the tree. This occurs because we may need to construct a string for each leaf node, and constructing the string takes O(N) time in the worst case (for a skewed tree).
// Space Complexity: O(H) for the recursion stack, where H is the height of the tree. In the worst case (a skewed tree), H can be O(N). Additionally, the space used for the StringBuilder is also O(H) in the worst case.
