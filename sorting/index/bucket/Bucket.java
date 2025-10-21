package sorting.index.bucket;

import java.util.*;

public class Bucket {

    @SuppressWarnings("unchecked")
    private static void bucketSort(float[] arr) {
        int n = arr.length;
        if (n <= 0)
            return;

        List<Float>[] buckets = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            buckets[i] = new ArrayList<Float>();
        }
        
        for (float val : arr) {
            int bucketIndex = (int) (val * n);
            buckets[bucketIndex].add(val);
        }

        for (List<Float> bucket : buckets) {
            Collections.sort(bucket);
        }

        int index = 0;
        for (List<Float> bucket : buckets) {
            for (float val : bucket) {
                arr[index++] = val;
            }
        }
    }

    public static void main(String[] args) {
        float[] arr = { 3, 6, 4, 1, 2, 5 };
        bucketSort(arr);
        for (float num : arr) {
            System.out.print(num + " ");
        }
        // Output: 1 2 3 4 5 6
    }

}

// Complexity Analysis
// Time Complexity: O(n + k), where n is the number of elements in the input
// array and k is the number of buckets.
// Space Complexity: O(n + k) for storing the buckets and the output array.