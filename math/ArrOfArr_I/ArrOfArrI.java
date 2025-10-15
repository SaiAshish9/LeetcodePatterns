package math.ArrOfArr_I;

public class ArrOfArrI {
    static void rearrange(int[] arr) {
        int n = arr.length;

        // First step: Increase all values
        // by (arr[arr[i]] % n) * n
        for (int i = 0; i < n; i++) {
            arr[i] += (arr[arr[i]] % n) * n;
        }

        // Second step: Divide all values by n
        for (int i = 0; i < n; i++) {
            arr[i] /= n;
        }
    }

    static void printArr(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {3, 2, 0, 1};
        rearrange(arr);
        printArr(arr); // Output should be: 1 0 3 2
    }   
}

// Time Complexity: O(n)
// Space Complexity: O(1)