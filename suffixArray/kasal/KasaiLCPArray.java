package suffixArray.kasal;

public class KasaiLCPArray {

    public static int[] buildLCPArray(String text, int[] suffixArr) {
        int n = text.length();
        int[] lcp = new int[n];
        int[] invSuffix = new int[n];
        // Build the inverse suffix array
        
        for (int i = 0; i < n; i++) {
            invSuffix[suffixArr[i]] = i;
        }
        
        int k = 0;
        for (int i = 0; i < n; i++) {
            if (invSuffix[i] == n - 1) {
                k = 0;
                continue;
            }
            
            int j = suffixArr[invSuffix[i] + 1];
            while (i + k < n && j + k < n && text.charAt(i + k) == text.charAt(j + k)) {
                k++;
            }
            
            lcp[invSuffix[i]] = k;
            if (k > 0) k--;
        }
        
        return lcp;
    }

    public static void main(String[] args) {
        String text = "banana";
        int[] suffixArr = {5, 3, 1, 0, 4, 2}; // Example suffix array for "banana"
        int[] lcp = buildLCPArray(text, suffixArr);
        
        System.out.println("LCP Array:");
        for (int value : lcp) {
            System.out.print(value + " ");
        }
        // Output should be: 1 3 0 0 2
    }
    
}

// Complexity Analysis:
// Time Complexity: O(n), where n is the length of the input string. The algorithm
// processes each character of the string a constant number of times.
// Space Complexity: O(n) for the LCP array and the inverse suffix array.