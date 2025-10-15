package math.countPairsThatCanBeRemovedWOChangingMean;

public class CountPairsThatCanBeRemovedWOChangingMean {

    private static int countPairs(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        if ((sum * 2) % n != 0) {
            return 0;
        }

        int target = (sum * 2) / n;
        
        // how is target sum * 2 / n derived?
        // let mean = sum / n
        // we need to find pairs (a, b) such that (sum - a - b) / (n - 2) = mean
        // => (sum - a - b) / (n - 2) = sum / n
        // => n * (sum - a - b) = sum * (n - 2)
        // => n * sum - n * a - n * b = sum * n - 2 * sum
        // => n * a + n * b = 2 * sum
        // => a + b = 2 * sum / n

        int count = 0;
        java.util.Map<Integer, Integer> freqMap = new java.util.HashMap<>();

        for (int num : nums) {
            int complement = target - num;
            if (freqMap.containsKey(complement)) {
                count += freqMap.get(complement);
            }
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        return count;
    }

    public static void main(String[] args) {
        int[] nums = { 2, 2, 2, 3, 4 };
        System.out.println(countPairs(nums));
    }

}

// Complexity Analysis
// Time Complexity: O(n), where n is the number of elements in the array. We
// traverse the array once to calculate the sum and again to count pairs.
// Space Complexity: O(n) in the worst case, for storing the frequency map.