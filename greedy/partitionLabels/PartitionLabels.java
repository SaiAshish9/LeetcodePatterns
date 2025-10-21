package greedy.partitionLabels;

import java.util.*;

public class PartitionLabels {
    public static List<Integer> partitionLabels(String s) {
        Map<Character, Integer> lastOccurrence = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            lastOccurrence.put(s.charAt(i), i);
        }
        
        List<Integer> result = new ArrayList<>();
        int start = 0;
        int end = 0;
        
        for (int i = 0; i < s.length(); i++) {
            end = Math.max(end, lastOccurrence.get(s.charAt(i)));
            
            if (i == end) {
                result.add(i - start + 1);
                start = i + 1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String s = "abac";
        List<Integer> result = partitionLabels(s);
        System.out.println("Partition sizes: " + result);
        // Expected Output: [3, 1]
    }
}

// Complexity Analysis:
// Time Complexity: O(N), where N is the length of the string. We traverse the string twice: once to record the last occurrences and once to find partitions.
// Space Complexity: O(1) or O(26) which is effectively constant space, since we only store the last occurrences of lowercase English letters.