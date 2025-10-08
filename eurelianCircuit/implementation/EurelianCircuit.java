package eurelianCircuit.implementation;
import java.util.*;


public class EurelianCircuit {
    private static Map<String, List<String>> buildDeBruijnGraph(List<String> kmers) {
        Map<String, List<String>> graph = new HashMap<>();

        for (String kmer : kmers) {
            String prefix = kmer.substring(0, kmer.length() - 1);
            String suffix = kmer.substring(1);

            graph.computeIfAbsent(prefix, k -> new ArrayList<>()).add(suffix);
        }

        return graph;
    }

    private static List<String> findEulerianCircuit(Map<String, List<String>> graph, String start) {
        Stack<String> stack = new Stack<>();
        List<String> circuit = new ArrayList<>();
        stack.push(start);

        while (!stack.isEmpty()) {
            String v = stack.peek();
            List<String> neighbors = graph.getOrDefault(v, new ArrayList<>());

            if (neighbors.isEmpty()) {
                circuit.add(stack.pop());
            } else {
                String w = neighbors.remove(0);
                stack.push(w);
            }
        }

        Collections.reverse(circuit);
        return circuit;
    }

    public static void main(String[] args) {
        List<String> kmers = Arrays.asList("000", "001", "010", "011", "100", "101", "110", "111");

        Map<String, List<String>> graph = buildDeBruijnGraph(kmers);
        String startNode = kmers.get(0).substring(0, kmers.get(0).length() - 1);
        List<String> circuit = findEulerianCircuit(graph, startNode);

        StringBuilder reconstructed = new StringBuilder(circuit.get(0));
        for (int i = 1; i < circuit.size(); i++) {
            reconstructed.append(circuit.get(i).charAt(circuit.get(i).length() - 1));
        }

        System.out.println("Eulerian Circuit (nodes): " + circuit);
        System.out.println("Reconstructed String from Eulerian Circuit: " + reconstructed.toString());
    }
}

/*
 * Complexity Analysis:
 * Time Complexity: O(N * K) where N is the number of k-mers and K is the length of each k-mer.
 * Space Complexity: O(N * K) for storing the De Bruijn graph and the Eulerian circuit.
 */