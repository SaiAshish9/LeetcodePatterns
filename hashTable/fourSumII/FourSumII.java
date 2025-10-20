package hashTable.fourSumII;

import java.util.*;

public class FourSumII {
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer, Integer> sumMap = new HashMap<>();

        for (int a : nums1) {
            for (int b : nums2) {
                int sum = a + b;
                sumMap.put(sum, sumMap.getOrDefault(sum, 0) + 1);
            }
        }

        int count = 0;

        for (int c : nums3) {
            for (int d : nums4) {
                int target = -(c + d);
                count += sumMap.getOrDefault(target, 0);
            }
        }

        return count;
    }

    public static void main(String[] args) {
        FourSumII solution = new FourSumII();
        int[] nums1 = { 1, 2 };
        int[] nums2 = { -2, -1 };
        int[] nums3 = { -1, 2 };
        int[] nums4 = { 0, 2 };
        int result = solution.fourSumCount(nums1, nums2, nums3, nums4);
        System.out.println("Number of tuples: " + result); // 2
    }
}

class RandomizedCollection {
    private List<Integer> list;
    private Map<Integer, Set<Integer>> map;
    private Random random;

    public RandomizedCollection() {
        list = new ArrayList<>();
        map = new HashMap<>();
        random = new Random();
    }

    public boolean insert(int val) {
        boolean contains = map.containsKey(val);
        if (!contains) {
            map.put(val, new HashSet<>());
        }
        map.get(val).add(list.size());
        list.add(val);
        return !contains;
    }

    public boolean remove(int val) {
        if (!map.containsKey(val) || map.get(val).isEmpty()) {
            return false;
        }

        int removeIndex = map.get(val).iterator().next();
        map.get(val).remove(removeIndex);

        int lastElement = list.get(list.size() - 1);
        if (removeIndex != list.size() - 1) {
            list.set(removeIndex, lastElement);
            map.get(lastElement).remove(list.size() - 1);
            map.get(lastElement).add(removeIndex);
        }

        list.remove(list.size() - 1);

        if (map.get(val).isEmpty()) {
            map.remove(val);
        }

        return true;
    }

    public int getRandom() {
        return list.get(random.nextInt(list.size()));
    }

    public static void main(String[] args) {
        RandomizedCollection collection = new RandomizedCollection();
        System.out.println(collection.insert(1)); // true
        System.out.println(collection.insert(1)); // false
        System.out.println(collection.insert(2)); // true
        System.out.println(collection.getRandom()); // 1 or 2
        System.out.println(collection.remove(1)); // true
        System.out.println(collection.remove(1)); // false
        System.out.println(collection.getRandom()); // 2 (only remaining element)
    }
}

// Complexity Analysis
// Time Complexity:
// - Insertion: O(1) on average
// - Deletion: O(1) on average
// - Get Random: O(1)
// Space Complexity: O(n) where n is the number of elements in the collection   