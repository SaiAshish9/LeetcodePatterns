package DeBruijnGraph.implementation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeBruijnGraph {
        private static Map<String, List<String>> buildDeBruijnGraph(List<String> kmers) {
        Map<String, List<String>> graph = new HashMap<>();

        for (String kmer : kmers) {
            String prefix = kmer.substring(0, kmer.length() - 1);
            String suffix = kmer.substring(1);

            graph.computeIfAbsent(prefix, k -> new ArrayList<>()).add(suffix);
        }

        return graph;
    }

    public static void main(String[] args) {
        List<String> kmers = Arrays.asList("000", "001", "010", "011", "100", "101", "110", "111");
        Map<String, List<String>> graph = buildDeBruijnGraph(kmers); // kmers: List of k-mers
        // k-mers means all binary strings of length 3

        for (Map.Entry<String, List<String>> entry : graph.entrySet()) {
            System.out.println(entry.getKey() + " -> " + String.join(",", entry.getValue()));
        }
    }
}

/*
11 -> 10,11
00 -> 00,01
01 -> 10,11
10 -> 00,01
 */
